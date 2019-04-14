package com.github.duychuongvn.jreddit.dto;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RedditCredentials {

    private StringProperty username;
    private StringProperty password;
    private StringProperty clientId;
    private StringProperty secret;
    private BooleanProperty verified;

    public RedditCredentials() {
        this(new UserSource());
    }

    public RedditCredentials(UserSource userSource) {
        this(userSource.getUsername(), userSource.getPassword(), userSource.getClientId(), userSource.getSecret(), userSource.isVerified());
    }

    public RedditCredentials(String username, String password, String clientId, String secret, Boolean verified) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.clientId = new SimpleStringProperty(clientId);
        this.secret = new SimpleStringProperty(secret);
        this.verified = new SimpleBooleanProperty(verified);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getClientId() {
        return clientId.get();
    }

    public StringProperty clientIdProperty() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId.set(clientId);
    }

    public String getSecret() {
        return secret.get();
    }

    public StringProperty secretProperty() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret.set(secret);
    }

    public boolean isVerified() {
        return verified.get();
    }

    public BooleanProperty verifiedProperty() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified.set(verified);
    }
}
