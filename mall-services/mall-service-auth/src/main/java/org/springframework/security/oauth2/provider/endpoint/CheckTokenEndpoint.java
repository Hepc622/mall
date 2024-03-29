/*******************************************************************************
 *     Cloud Foundry 
 *     Copyright (c) [2009-2014] Pivotal Software, Inc. All Rights Reserved.
 *
 *     This product is licensed to you under the Apache License, Version 2.0 (the "License").
 *     You may not use this product except in compliance with the License.
 *
 *     This product includes a number of subcomponents with
 *     separate copyright notices and license terms. Your use of these
 *     subcomponents is subject to the terms and conditions of the
 *     subcomponent's license, as noted in the LICENSE file.
 *******************************************************************************/
package org.springframework.security.oauth2.provider.endpoint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller which decodes access tokens for clients who are not able to do so (or where opaque token values are used).
 *
 * @author Luke Taylor
 * @author Joel D'sa
 */
@FrameworkEndpoint
public class CheckTokenEndpoint {

    private ResourceServerTokenServices resourceServerTokenServices;

    private AccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();

    protected final Log logger = LogFactory.getLog(getClass());

    private WebResponseExceptionTranslator exceptionTranslator = new DefaultWebResponseExceptionTranslator();

    public CheckTokenEndpoint(ResourceServerTokenServices resourceServerTokenServices) {
        this.resourceServerTokenServices = resourceServerTokenServices;
    }

    /**
     * @param exceptionTranslator the exception translator to set
     */
    public void setExceptionTranslator(WebResponseExceptionTranslator exceptionTranslator) {
        this.exceptionTranslator = exceptionTranslator;
    }

    /**
     * @param accessTokenConverter the accessTokenConverter to set
     */
    public void setAccessTokenConverter(AccessTokenConverter accessTokenConverter) {
        this.accessTokenConverter = accessTokenConverter;
    }

    @RequestMapping(value = "/oauth/check_token")
    @ResponseBody
    public Map<String, ?> checkToken(@RequestParam("token") String value) {

        OAuth2AccessToken token = resourceServerTokenServices.readAccessToken(value);


        Map<String, Object> response = new HashMap<>(1);

        // gh-1070
        if (token == null) {
            response.put("active", false);
            return response;
        }

        if (token.isExpired()) {
            response.put("active", false);
            return response;
        }

        OAuth2Authentication authentication = resourceServerTokenServices.loadAuthentication(token.getValue());
        response = (Map<String, Object>) accessTokenConverter.convertAccessToken(token, authentication);
        response.put("active", true);

        return response;
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<OAuth2Exception> handleException(Exception e) throws Exception {
        logger.info("Handling error: " + e.getClass().getSimpleName() + ", " + e.getMessage());
        // This isn't an oauth resource, so we don't want to send an
        // unauthorized code here. The client has already authenticated
        // successfully with basic auth and should just
        // get back the invalid token error.
        @SuppressWarnings("serial")
        InvalidTokenException e400 = new InvalidTokenException(e.getMessage()) {
            @Override
            public int getHttpErrorCode() {
                return 400;
            }
        };
        return exceptionTranslator.translate(e400);
    }

}
