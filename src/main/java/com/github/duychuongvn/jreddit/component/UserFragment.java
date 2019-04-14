package com.github.duychuongvn.jreddit.component;

import com.github.duychuongvn.jreddit.UserSourceNotInitializedException;
import com.github.duychuongvn.jreddit.annotation.EventHandler;
import com.github.duychuongvn.jreddit.config.AppConfig;
import com.github.duychuongvn.jreddit.dto.RedditCredentials;
import com.github.duychuongvn.jreddit.dto.UserSource;
import com.github.duychuongvn.jreddit.service.UserStoreService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Fragment(id = AppConfig.FRAGMENT_USER,
        viewLocation = "/fxml/UserFragment.fxml",
        resourceBundleLocation = "bundles.languageBundle",
        localeID = "en_US",
        scope = Scope.SINGLETON)
public class UserFragment extends BaseFragment {

    @FXML
    private TableView<RedditCredentials> tvUsers;
    @FXML
    private VBox vbUserData;
    @Autowired
    private UserStoreService userStoreService;
    @Resource
    private Context context;

    private List<RedditCredentials> redditCredentialsList = new ArrayList<>();
    @Override
    protected void initialized() {

        try { List<RedditCredentials> users = userStoreService.loadUsers().stream()
                    .map(RedditCredentials::new)
                    .collect(Collectors.toList());

            users.forEach(user -> {
                UserRow userRow = new UserRow(context, vbUserData, user);
            });

        } catch (UserSourceNotInitializedException e) {
            e.printStackTrace();
        }

//        loadUserData();
    }

    private void loadUserData() {
        TableColumn<RedditCredentials, String> colUsername = new TableColumn("Username");
        TableColumn<RedditCredentials, String> colPassword = new TableColumn("Password");
        TableColumn<RedditCredentials, String> colClientId = new TableColumn("ClientId");
        TableColumn<RedditCredentials, String> colSecret = new TableColumn("Secret");
        TableColumn<RedditCredentials, Boolean> colVerified = new TableColumn("Verified");
        colVerified.setEditable(false);
        colVerified.setCellValueFactory(new PropertyValueFactory<>("verified"));
        colVerified.setCellFactory(CheckBoxTableCell.<RedditCredentials>forTableColumn(colVerified));


        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colUsername.setCellFactory(TextFieldTableCell.<RedditCredentials>forTableColumn());
//        colUsername.setMinWidth(200);

//        colUsername.setOnEditCommit((TableColumn.CellEditEvent<UserSource, String> event) -> {
//            TablePosition<UserSource, String> pos = event.getTablePosition();
//            String newFullName = event.getNewValue();
//            int row = pos.getRow();
//            UserSource person = event.getTableView().getItems().get(row);
//            person.setUsername(newFullName);
//        });
//
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colPassword.setCellFactory(TextFieldTableCell.<RedditCredentials>forTableColumn());
//        colPassword.setMinWidth(200);
//
//        colPassword.setOnEditCommit((TableColumn.CellEditEvent<UserSource, String> event) -> {
//            TablePosition<UserSource, String> pos = event.getTablePosition();
//            String newValue = event.getNewValue();
//            int row = pos.getRow();
//            UserSource person = event.getTableView().getItems().get(row);
//            person.setPassword(newValue);
//        });

        colClientId.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        colClientId.setCellFactory(TextFieldTableCell.<RedditCredentials>forTableColumn());
//        colClientId.setMinWidth(200);
//
//        colClientId.setOnEditCommit((TableColumn.CellEditEvent<UserSource, String> event) -> {
//            TablePosition<UserSource, String> pos = event.getTablePosition();
//            String newValue = event.getNewValue();
//            int row = pos.getRow();
//            UserSource person = event.getTableView().getItems().get(row);
//            person.setClientId(newValue);
//        });
        colSecret.setCellValueFactory(new PropertyValueFactory<>("secret"));
        colSecret.setCellFactory(TextFieldTableCell.<RedditCredentials>forTableColumn());
//        colSecret.setMinWidth(200);
//
//        colSecret.setOnEditCommit((TableColumn.CellEditEvent<UserSource, String> event) -> {
//            TablePosition<UserSource, String> pos = event.getTablePosition();
//            String newValue = event.getNewValue();
//            int row = pos.getRow();
//            UserSource person = event.getTableView().getItems().get(row);
//            person.setSecret(newValue);
//        });

        tvUsers.getColumns().addAll(colUsername, colPassword, colClientId, colSecret, colVerified);

        tvUsers.getSelectionModel().setCellSelectionEnabled(true);
        tvUsers.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


        try {
            tvUsers.setItems(FXCollections.observableList(userStoreService.loadUsers().stream()
                    .map(RedditCredentials::new)
                    .collect(Collectors.toList())));
        } catch (UserSourceNotInitializedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void save() {
        userStoreService.storeUsers(vbUserData.getChildren()
                .stream()
                .map(x->new UserSource(((UserRow)x).getRedditCredentials()))
                .collect(Collectors.toList()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Save successful");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Store user data successfuls!");

        alert.showAndWait();
    }

    @FXML
    public void addUser() {
        RedditCredentials redditCredentials = new RedditCredentials();
        UserRow userRow = new UserRow(context, vbUserData, redditCredentials);
//        tvUsers.getItems().add(redditCredentials);
//        tvUsers.getSelectionModel().select(redditCredentials);
    }

    @EventHandler
    public void handleRemoveUser(UserRow.RemoveUserMessage message) {

    }


}
