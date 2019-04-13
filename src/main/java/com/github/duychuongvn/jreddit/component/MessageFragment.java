package com.github.duychuongvn.jreddit.component;

import com.github.duychuongvn.jreddit.MessageCellFactory;
import com.github.duychuongvn.jreddit.config.AppConfig;
import com.github.duychuongvn.jreddit.dto.MessageDto;
import com.github.duychuongvn.jreddit.service.RedditService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

@Fragment(id = AppConfig.FRAGMENT_MESSAGE,
        viewLocation = "/fxml/MessageFragment.fxml",
        resourceBundleLocation = "bundles.languageBundle",
        localeID = "en_US",

        scope = Scope.SINGLETON)
public class MessageFragment extends BaseFragment {
    @FXML
    private ListView<MessageDto> lvMessages;
    @Autowired
    private RedditService redditService;

    @Override
    protected void initialized() {
        lvMessages.setCellFactory(new MessageCellFactory());
        loadMessages();
    }

    private void loadMessages() {

        ReloadMessageTask reloadMessageTask = new ReloadMessageTask(lvMessages);
        Timer timer = new java.util.Timer();
        timer.schedule(reloadMessageTask, 0, 60000);
    }

    class ReloadMessageTask extends TimerTask {

        ListView<MessageDto> lvMessages;

        public ReloadMessageTask(ListView<MessageDto> lvMessages) {
            this.lvMessages = lvMessages;
        }

        @Override
        public void run() {
            Platform.runLater(() -> {
                if (rootPane.getParent() != null) {
                    List<MessageDto> newMessages = redditService.loadMessages();
                    List<MessageDto> addMessages = FXCollections.observableList(newMessages.stream()
                            .filter(x -> lvMessages.getItems().stream().noneMatch(y ->
                                    y.getMessage().getId().equals(x.getMessage().getId())))
                            .collect(Collectors.toList()));
                    lvMessages.getItems().addAll(addMessages);
                }

            });
        }

    }
}
