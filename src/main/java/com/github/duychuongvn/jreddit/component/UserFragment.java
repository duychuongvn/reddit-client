package com.github.duychuongvn.jreddit.component;

import com.github.duychuongvn.jreddit.UserSourceNotInitializedException;
import com.github.duychuongvn.jreddit.config.AppConfig;
import com.github.duychuongvn.jreddit.dto.UserSource;
import com.github.duychuongvn.jreddit.service.UserStoreService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Fragment(id = AppConfig.FRAGMENT_USER,
        viewLocation = "/fxml/UserFragment.fxml",
        resourceBundleLocation = "bundles.languageBundle",
        localeID = "en_US",
        scope = Scope.SINGLETON)
public class UserFragment extends BaseFragment {

    @FXML
    private TableView<UserSource> tvUsers;
    @Autowired
    private UserStoreService userStoreService;
    @Resource
    private Context context;

    @Override
    protected void initialized() {

        TableColumn<UserSource, String> colUsername = new TableColumn("Username");
        TableColumn<UserSource, String> colPassword = new TableColumn("Password");
        TableColumn<UserSource, String> colClientId = new TableColumn("ClientId");
        TableColumn<UserSource, String> colSecret = new TableColumn("Secret");

        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colUsername.setCellFactory(TextFieldTableCell.<UserSource>forTableColumn());
        colUsername.setMinWidth(200);

        colUsername.setOnEditCommit((TableColumn.CellEditEvent<UserSource, String> event) -> {
            TablePosition<UserSource, String> pos = event.getTablePosition();
            String newFullName = event.getNewValue();
            int row = pos.getRow();
            UserSource person = event.getTableView().getItems().get(row);
            person.setUsername(newFullName);
        });

        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colPassword.setCellFactory(TextFieldTableCell.<UserSource>forTableColumn());
        colPassword.setMinWidth(200);

        colPassword.setOnEditCommit((TableColumn.CellEditEvent<UserSource, String> event) -> {
            TablePosition<UserSource, String> pos = event.getTablePosition();
            String newValue = event.getNewValue();
            int row = pos.getRow();
            UserSource person = event.getTableView().getItems().get(row);
            person.setPassword(newValue);
        });

        colClientId.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        colClientId.setCellFactory(TextFieldTableCell.<UserSource>forTableColumn());
        colClientId.setMinWidth(200);

        colClientId.setOnEditCommit((TableColumn.CellEditEvent<UserSource, String> event) -> {
            TablePosition<UserSource, String> pos = event.getTablePosition();
            String newValue = event.getNewValue();
            int row = pos.getRow();
            UserSource person = event.getTableView().getItems().get(row);
            person.setClientId(newValue);
        });
        colSecret.setCellValueFactory(new PropertyValueFactory<>("secret"));
        colSecret.setCellFactory(TextFieldTableCell.<UserSource>forTableColumn());
        colSecret.setMinWidth(200);

        colSecret.setOnEditCommit((TableColumn.CellEditEvent<UserSource, String> event) -> {
            TablePosition<UserSource, String> pos = event.getTablePosition();
            String newValue = event.getNewValue();
            int row = pos.getRow();
            UserSource person = event.getTableView().getItems().get(row);
            person.setSecret(newValue);
        });

        tvUsers.getColumns().addAll(colUsername, colPassword, colClientId, colSecret);

        try {
            tvUsers.setItems(FXCollections.observableList(new ArrayList<>(userStoreService.loadUsers())));
        } catch (UserSourceNotInitializedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void save() {
        userStoreService.storeUsers(tvUsers.getItems());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Save successful");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Store user data successfuls!");

        alert.showAndWait();
    }

    @FXML
    public void addUser() {
        tvUsers.getItems().add(new UserSource());
    }
}
