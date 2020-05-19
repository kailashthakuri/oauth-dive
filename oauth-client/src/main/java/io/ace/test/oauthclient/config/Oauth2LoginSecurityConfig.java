package io.ace.test.oauthclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;

@Configuration
public class Oauth2LoginSecurityConfig extends WebSecurityConfigurerAdapter {
    private static String CLIENT_PROPERTY_KEY = "spring.security.oauth2.client.registration.";

    @Resource
    public Environment environment;

    @Autowired
    public CustomOidcUserService customOidcUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizedRequests -> {
                    authorizedRequests
                            .antMatchers("/", "/resources/**").permitAll()
                            .anyRequest().authenticated();
                })
                .oauth2Login(oAuth2LoginConfigurer -> {
                    oAuth2LoginConfigurer
                            .clientRegistrationRepository(clientRegistrationRepository())
                            .authorizedClientService(oAuth2AuthorizedClientService())
                            .loginPage("/")
                            .defaultSuccessUrl("/oauth_success", true)
                            .authorizationEndpoint(authorizationEndpointConfig -> {
                                authorizationEndpointConfig
                                        .baseUri("/oauth2/authorization")
                                        .authorizationRequestRepository(authorizationRequestRepository());
                            })
                            .tokenEndpoint(tokenEndpointConfig -> {
                                tokenEndpointConfig
                                        .accessTokenResponseClient(accessTokenResponseClient());
                            })
                            .userInfoEndpoint(userInfoEndpointConfig -> {
                                userInfoEndpointConfig
                                        .oidcUserService(customOidcUserService);
                            });
                })
                .logout(logoutConfig -> {
                    logoutConfig
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                            .logoutSuccessHandler(logoutSuccessHandler());
                });
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler logoutSuccessHandler =
                new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository());
        logoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8080");
        return logoutSuccessHandler;
    }

    @Bean
    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
        return new DefaultAuthorizationCodeTokenResponseClient();
    }

    @Bean
    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
        return new HttpSessionOAuth2AuthorizationRequestRepository();
    }


    @Bean
    public OAuth2AuthorizedClientService oAuth2AuthorizedClientService() {
        InMemoryOAuth2AuthorizedClientService service =
                new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
        return service;
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        InMemoryClientRegistrationRepository repository =
                new InMemoryClientRegistrationRepository(clientRegistration("google"));
        return repository;
    }

    public ClientRegistration clientRegistration(String client) {
        String clientId = environment.getProperty(
                CLIENT_PROPERTY_KEY + client + ".client-id");
        String clientSecret = environment.getProperty(
                CLIENT_PROPERTY_KEY + client + ".client-secret");
        ClientRegistration google = CommonOAuth2Provider.GOOGLE.getBuilder(client)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
        return google;
    }

}
