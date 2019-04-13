package com.github.duychuongvn.jreddit;

import com.github.duychuongvn.jreddit.dto.MessageDto;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;


public class MessageCellFactory implements Callback<javafx.scene.control.ListView<MessageDto>, ListCell<MessageDto>> {

    @Override
    public ListCell<MessageDto> call(ListView<MessageDto> param) {
        return new MessageCell();
    }
}
