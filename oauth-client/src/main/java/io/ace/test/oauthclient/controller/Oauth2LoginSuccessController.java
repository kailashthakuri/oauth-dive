package io.ace.test.oauthclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oauth_success")
public class Oauth2LoginSuccessController {

    @Autowired
    private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @RequestMapping
    public String loginSuccessHandler(OAuth2AuthenticationToken authentication) {
        OAuth2AuthorizedClient oAuth2AuthorizedClient = oAuth2AuthorizedClientService.
                loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());
        System.out.println(oAuth2AuthorizedClient);
        return "user";
    }
}
