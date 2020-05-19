package io.ace.test.oauthclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    private static String authorizationRequestBaseUri = "oauth2/authorization";

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @GetMapping()
    public String getLoginPage(Model model) {
        ClientRegistration google = clientRegistrationRepository.findByRegistrationId("google");
        String oauth2AuthorizationUrl = authorizationRequestBaseUri + "/" + google.getRegistrationId();
        model.addAttribute("authUri", oauth2AuthorizationUrl);
        return "home";
    }
}
