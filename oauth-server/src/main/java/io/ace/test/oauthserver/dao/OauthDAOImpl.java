package io.ace.test.oauthserver.dao;

import io.ace.test.oauthserver.model.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OauthDAOImpl implements OauthDAO {
    public UserEntity getUserDetails(String email) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("kailash@gmail.com");
        userEntity.setName("Kailash Shahi");
        userEntity.setPassword("$2a$10$LoZ.rl60buqHXduMXrag8.V033/FTU1hc4Kf8UpM/..g6jg.fE4MC");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_CREATE_NOTE"));
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_DELETE_NOTE"));
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_VIEW_NOTE"));
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_EDIT_NOTE"));
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_VIEW_ALL_NOTE"));
        userEntity.setGrantedAuthorities(grantedAuthorities);
        return userEntity;
    }
}
