package io.ace.test.oauthclient.config;

import io.ace.test.oauthclient.user.entity.User;
import io.ace.test.oauthclient.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class CustomOidcUserService extends OidcUserService {

    @Autowired
    public UserService userService;

    public CustomOidcUserService() {
        super();
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        GoogleUserInfo googleUserInfo = new GoogleUserInfo(oidcUser.getAttributes());
        if (null == userService.findByEmail(googleUserInfo.getEmail())) {
            User user = new User();
            user.setEmail(googleUserInfo.getEmail());
            user.setName(googleUserInfo.getName());
            userService.saveUser(user);
        }
        return oidcUser;
    }
}
