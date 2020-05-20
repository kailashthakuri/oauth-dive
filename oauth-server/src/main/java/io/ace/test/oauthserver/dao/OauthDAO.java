package io.ace.test.oauthserver.dao;

import io.ace.test.oauthserver.model.UserEntity;

public interface OauthDAO {
    UserEntity getUserDetails(String email);
}
