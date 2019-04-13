package com.github.duychuongvn.jreddit;

import com.github.duychuongvn.jreddit.component.MessageFragment;
import com.github.duychuongvn.jreddit.component.UserFragment;
import com.github.duychuongvn.jreddit.config.AppConfig;
import javafx.event.Event;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.lifecycle.OnHide;
import org.jacpfx.api.annotations.lifecycle.OnShow;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.annotations.perspective.Perspective;
import org.jacpfx.api.message.Message;
import org.jacpfx.api.util.ToolbarPosition;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.componentLayout.PerspectiveLayout;
import org.jacpfx.rcp.components.toolBar.JACPToolBar;
import org.jacpfx.rcp.context.Context;
import org.jacpfx.rcp.perspective.FXPerspective;

import java.util.ResourceBundle;
import java.util.logging.Logger;

@Perspective(id = AppConfig.PERSPECTIVE_ONE,
        name = "contactPerspective",
        components = {
                AppConfig.COMPONENT_RIGHT},
        resourceBundleLocation = "bundles.languageBundle")
public class PerspectiveOne implements FXPerspective {
    private Logger log = Logger.getLogger(PerspectiveOne.class.getName());
    @Resource
    public Context context;

    private VBox mainLayout;

    @Override
    public void handlePerspective(final Message<Event, Object> action,
                                  final PerspectiveLayout perspectiveLayout) {

        System.out.println("sss");
    }


    @OnShow
    /**
     * This method will be executed when the perspective gets the focus and switches to foreground
     * @param layout, the component layout contains references to the toolbar and the menu
     */
    public void onShow(final FXComponentLayout layout) {
        log.info("on show of PerspectiveOne");

    }

    @OnHide
    /**
     * will be executed when an active perspective looses the focus and moved to the background.
     * @param layout, the component layout contains references to the toolbar and the menu
     */
    public void onHide(final FXComponentLayout layout) {
        log.info("on hide of PerspectiveOne");
    }

    @PostConstruct
    /**
     * @PostConstruct annotated method will be executed when component is activated.
     * @param layout
     * @param resourceBundle
     */
    public void onStartPerspective(final PerspectiveLayout perspectiveLayout, final FXComponentLayout layout,
                                   final ResourceBundle resourceBundle) {
        // define toolbars and menu entries
        JACPToolBar toolbar = layout.getRegisteredToolBar(ToolbarPosition.NORTH);
        // toolbar.add(new Label(resourceBundle.getString("button.message")));
        //toolbar.add(new Label(resourceBundle.getString("button.userSource")));

        // Creating a ToggleGroup
        // Tạo ToggleGroup
        ToggleGroup group = new ToggleGroup();

        // Creating new Toggle buttons.
        ToggleButton messagesButton = new ToggleButton("Messages");
        ToggleButton usersButton = new ToggleButton("Users");
        group.setUserData(AppConfig.COMPONENT_RIGHT);

        messagesButton.setToggleGroup(group);
        usersButton.setToggleGroup(group);
        messagesButton.setSelected(true);

        messagesButton.setUserData(MessageFragment.class);
        usersButton.setUserData(UserFragment.class);
        toolbar.add(messagesButton);
        toolbar.add(usersButton);

        group.selectedToggleProperty().addListener(c -> {
            if (group.getSelectedToggle() != null) {
                context.send(AppConfig.COMPONENT_RIGHT, group.getSelectedToggle().getUserData());
            }
        });

        mainLayout = new VBox();

        // create left button menu
//        GridPane leftMenu = new GridPane();
        // create main content Top
        StackPane mainContent = new StackPane();

        mainLayout.getChildren().addAll(mainContent);
        // Register root component
        perspectiveLayout.registerRootComponent(mainLayout);
        // register left menu
//        perspectiveLayout.registerTargetLayoutComponent(AppConfig.TARGET_CONTAINER_LEFT, leftMenu);
        // register main content

        perspectiveLayout.registerTargetLayoutComponent(AppConfig.TARGET_CONTAINER_MAIN, mainContent);
        log.info("on PostConstruct of PerspectiveOne");
    }


    @PreDestroy
    /**
     * @PreDestroy annotated method will be executed when component is deactivated.
     * @param layout, the component layout contains references to the toolbar and the menu
     */
    public void onTearDownPerspective(final FXComponentLayout layout) {
        log.info("on PreDestroy of PerspectiveOne");

    }

}