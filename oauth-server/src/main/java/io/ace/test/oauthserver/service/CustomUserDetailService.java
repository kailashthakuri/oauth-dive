package io.ace.test.oauthserver.service;

import io.ace.test.oauthserver.dao.OauthDAO;
import io.ace.test.oauthserver.model.CustomUser;
import io.ace.test.oauthserver.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    OauthDAO oauthDAO;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity test = oauthDAO.getUserDetails("test");
        CustomUser customUser = new CustomUser(test.getEmail(), test.getPassword(), test.getGrantedAuthorities());
        customUser.setId(test.getId());
        customUser.setName(test.getName());
        return customUser;
    }
}
