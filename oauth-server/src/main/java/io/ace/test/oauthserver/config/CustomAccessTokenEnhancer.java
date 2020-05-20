package io.ace.test.oauthserver.config;

import io.ace.test.oauthserver.model.CustomUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

public class CustomAccessTokenEnhancer extends JwtAccessTokenConverter {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        Map<String, Object> additionalInformation = accessToken.getAdditionalInformation();
        if (null != customUser.getId()) {
            additionalInformation.put("id", customUser.getId());
            additionalInformation.put("name", customUser.getName());
        }
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        defaultOAuth2AccessToken.setAdditionalInformation(additionalInformation);
        return super.enhance(defaultOAuth2AccessToken, authentication);
    }
}
