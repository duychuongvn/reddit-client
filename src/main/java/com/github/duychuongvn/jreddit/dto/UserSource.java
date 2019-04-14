package com.github.duychuongvn.jreddit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSource implements Serializable {


    private static final long serialVersionUID = -2812802602177382626L;
    private String username;
    private String password;
    private String clientId;
    private String secret;
    private boolean verified;

    public UserSource(RedditCredentials account) {
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.clientId = account.getClientId();
        this.secret = account.getSecret();
        this.verified = account.isVerified();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSource that = (UserSource) o;
        return verified == that.verified &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(secret, that.secret);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, password, clientId, secret, verified);
    }

    @Override
    public String toString() {
        return "UserSource{" +
                "username='" + username + '\'' +
                ", clientId='" + clientId + '\'' +
                ", verified=" + verified +
                '}';
    }
}
