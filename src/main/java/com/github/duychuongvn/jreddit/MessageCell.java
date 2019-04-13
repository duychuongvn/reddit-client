package com.github.duychuongvn.jreddit;

import com.github.duychuongvn.jreddit.dto.MessageDto;
import com.github.duychuongvn.jreddit.util.RedditClientUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.jacpfx.rcp.util.FXUtil;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class MessageCell extends ListCell<MessageDto> {

    @FXML
    private Label lblFrom;

    @FXML
    private TextArea lblContent;

    @FXML
    private Label lblReceiptUser;

    @FXML
    private Label lblSentDate;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Button btnReply;
    @FXML
    private VBox vbReply;
    @FXML
    private TextArea txtReply;
    @FXML
    private Hyperlink hlReply;

    public MessageCell() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/message_cell.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(MessageDto item, boolean empty) {
        super.updateItem(item, empty);
        hlReply.setOnAction(hl -> {
            if (vbReply.isVisible()) {
                vbReply.setVisible(false);
            } else {
                vbReply.setVisible(true);
            }
        });
        btnReply.setOnAction(r -> {
            if(!StringUtils.isEmpty(txtReply.getText())) {
                RedditClientUtil.getInstance().getRedditService().reply(item, txtReply.getText());
                vbReply.setVisible(false);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Input Error");

                // Header Text: null
                alert.setHeaderText(null);
                alert.setContentText("Reply content is required!");

                alert.showAndWait();
            }


        });
        if (empty) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else {
            lblSentDate.setText("sent " + item.getMessage().getCreated().toString());
            lblFrom.setText(item.getMessage().getAuthor());
            lblContent.setText(item.getMessage().getBody());
            lblReceiptUser.setText(item.getReceiptUsername());
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}