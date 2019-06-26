package com.github.mall.auth.service;

import com.github.mall.auth.config.OauthClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

/**
 * @author ：HPC
 * @date ：Created in 2019-05-28
 * @description： 权限验证client获取service
 */
@Service
@Primary
public class MallClientDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    private OauthClientConfig oauthClientConfig;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        if (clientId == null) {
            throw new NoSuchClientException("No client with requested id: null");
        }
        return oauthClientConfig.getClientWithId(clientId);
    }
}
