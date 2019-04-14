package com.github.duychuongvn.jreddit.component;

import com.github.duychuongvn.jreddit.dto.RedditCredentials;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lombok.Builder;
import lombok.Getter;
import org.jacpfx.rcp.context.Context;

import java.io.IOException;

public class UserRow extends VBox {

    @FXML
    private TextField lblUsername;
    @FXML
    private TextField lblPassword;
    @FXML
    private TextField lblClientId;
    @FXML
    private TextField lblSecret;
    @FXML
    private CheckBox chbVerified;
    @FXML
    private Button btnRemove;

    private RedditCredentials redditCredentials;
    private Context context;
    private VBox parent;
    public UserRow(Context context, VBox parent, RedditCredentials redditCredentials) {
        this.context = context;
        this.redditCredentials = redditCredentials;
        this.parent = parent;
        loadFXML();
        updateUI(redditCredentials);

    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UserRow.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void updateUI(RedditCredentials redditCredentials) {
        this.redditCredentials = redditCredentials;
        lblUsername.textProperty().bindBidirectional(redditCredentials.usernameProperty());
        lblPassword.textProperty().bindBidirectional(redditCredentials.passwordProperty());
        lblClientId.textProperty().bindBidirectional(redditCredentials.clientIdProperty());
        lblSecret.textProperty().bindBidirectional(redditCredentials.secretProperty());
        chbVerified.selectedProperty().bind(redditCredentials.verifiedProperty());
        this.parent.getChildren().add(this);
    }

    @FXML
    public void remove() {
        this.parent.getChildren().remove(this);
        this.parent = null;
    }

    @Builder
    @Getter
    public static class RemoveUserMessage{
        private RedditCredentials redditCredentials;
    }

    public RedditCredentials getRedditCredentials() {
        return redditCredentials;
    }
}
