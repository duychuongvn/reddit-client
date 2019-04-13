//package com.github.duychuongvn.jreddit;
//
//import com.github.duychuongvn.jreddit.config.AppConfig;
//import javafx.event.Event;
//import javafx.geometry.Insets;
//import javafx.scene.Node;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextArea;
//import javafx.scene.layout.GridPane;
//import org.jacpfx.api.annotations.Resource;
//import org.jacpfx.api.annotations.component.View;
//import org.jacpfx.api.annotations.lifecycle.PostConstruct;
//import org.jacpfx.api.annotations.lifecycle.PreDestroy;
//import org.jacpfx.api.message.Message;
//import org.jacpfx.rcp.component.FXComponent;
//import org.jacpfx.rcp.componentLayout.FXComponentLayout;
//import org.jacpfx.rcp.context.Context;
//import org.jacpfx.rcp.util.FXUtil;
//
//import java.util.ResourceBundle;
//import java.util.logging.Logger;
//
//@View(id = AppConfig.COMPONENT_LEFT,
//        name = "SimpleView",
//        active = true,
//        resourceBundleLocation = "bundles.languageBundle",
//        initialTargetLayoutId = AppConfig.TARGET_CONTAINER_LEFT)
//public class ComponentLeft implements FXComponent {
//    private Node pane;
//    private TextArea name;
//    private Logger log = Logger.getLogger(ComponentLeft.class.getName());
//    @Resource
//    private Context context;
//
//    @Override
//    /**
//     * The handle method always runs outside the main application thread. You can create new nodes, execute long running tasks but you are not allowed to manipulate existing nodes here.
//     */
//    public Node handle(final Message<Event, Object> message) {
//        // runs in worker thread
//
//        return null;
//    }
//
//    @Override
//    /**
//     * The postHandle method runs always in the main application thread.
//     */
//    public Node postHandle(final Node arg0,
//                           final Message<Event, Object> message) {
//        // runs in FX application thread
//        if (!message.messageBodyEquals(FXUtil.MessageUtil.INIT)) {
//            name.setText(message.getTypedMessageBody(String.class));
//        }
//        return this.pane;
//    }
//
//    @PostConstruct
//    /**
//     * The @PostConstruct annotation labels methods executed when the component switch from inactive to active state
//     * @param arg0
//     * @param resourceBundle
//     */
//    public void onPostConstructComponent(final FXComponentLayout arg0,
//                                         final ResourceBundle resourceBundle) {
//        this.pane = createUI();
//        this.log.info("run on start of ComponentLeft ");
//    }
//
//    @PreDestroy
//    /**
//     * The @PreDestroy annotations labels methods executed when the component is set to inactive
//     * @param arg0
//     */
//    public void onPreDestroyComponent(final FXComponentLayout arg0) {
//        this.log.info("run on tear down of ComponentLeft ");
//
//    }
//
//    /**
//     * create the UI on first call
//     *
//     * @return
//     */
//    private Node createUI() {
//        final GridPane main = new GridPane();
//        main.setPadding(new Insets(0, 20, 10, 20));
//
//        name = new TextArea();
//        Button sayHello = new Button("hello");
//        sayHello.setOnAction((event)->sayHello());
//
//        main.getChildren().addAll(name,sayHello);
//        return main;
//    }
//
//    private void sayHello() {
//        context.send(AppConfig.COMPONENT_RIGHT,"hello");
//    }
//}