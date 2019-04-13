package com.github.duychuongvn.jreddit.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserStore implements Serializable {

    private static final long serialVersionUID = -4877943614281298254L;
    private Set<UserSource> userSources = new HashSet<>();

    public void addUserSource(UserSource userSource) {
        this.userSources.add(userSource);
    }

    public void addUserSources(Collection<UserSource> userSources) {
        this.userSources.addAll(userSources);
    }

    public Set<UserSource> getUserSources() {
        return userSources;
    }
}
