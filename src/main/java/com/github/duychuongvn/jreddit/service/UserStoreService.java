package com.github.duychuongvn.jreddit.service;

import com.github.duychuongvn.jreddit.UserSourceNotInitializedException;
import com.github.duychuongvn.jreddit.dto.UserSource;

import java.util.List;
import java.util.Set;

public interface UserStoreService {

    void storeUsers(List<UserSource> userSources);
    Set<UserSource> loadUsers() throws UserSourceNotInitializedException;
}
