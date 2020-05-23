package io.ace.test.authresourcesever.config;

import io.ace.test.authresourcesever.model.AccessTokenMapper;
import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JWTConverter extends DefaultAccessTokenConverter implements JwtAccessTokenConverterConfigurer {


    @Override
    public void configure(JwtAccessTokenConverter jwtAccessTokenConverter) {
        jwtAccessTokenConverter.setAccessTokenConverter(this);
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication oAuth2Authentication = super.extractAuthentication(map);
        AccessTokenMapper accessTokenMapper = new AccessTokenMapper();
        if (null != map.get("id")) {
            accessTokenMapper.setId((String) map.get("id"));
        }
        if (null != map.get("name")) {
            accessTokenMapper.setName((String) map.get("namae"));
        }
        oAuth2Authentication.setDetails(accessTokenMapper);
        return oAuth2Authentication;
    }
}
