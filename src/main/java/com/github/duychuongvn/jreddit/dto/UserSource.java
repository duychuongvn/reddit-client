package com.github.duychuongvn.jreddit.dto;

import lombok.*;

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

    @Override
    public String toString() {
        return "UserSource{" +
                "username='" + username + '\'' +
                ", password='" + "******" + '\'' +
                ", clientId='" + clientId + '\'' +
                ", secret='" + "******" + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSource that = (UserSource) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(secret, that.secret);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, password, clientId, secret);
    }
}
