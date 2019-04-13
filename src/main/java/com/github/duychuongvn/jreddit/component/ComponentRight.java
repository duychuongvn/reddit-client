package com.github.duychuongvn.jreddit.component;

import com.github.duychuongvn.jreddit.config.AppConfig;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.DeclarativeView;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@DeclarativeView(id = AppConfig.COMPONENT_RIGHT,
        name = "SimpleView",
        viewLocation = "/fxml/ComponentRight.fxml",
        active = true,
        resourceBundleLocation = "bundles.languageBundle",
        localeID = "en_US",
        initialTargetLayoutId = AppConfig.TARGET_CONTAINER_MAIN)
public class ComponentRight implements FXComponent {
    private Logger log = Logger.getLogger(ComponentRight.class.getName());
    @FXML
    protected VBox rootPane;
    @Resource
    protected Context context;

    private Map<Class, ManagedFragmentHandler> managedFragmentMap = Collections.synchronizedMap(new HashMap<>());
    private ManagedFragmentHandler<? extends BaseFragment> managedFragmentHandler;

    protected <T extends BaseFragment> ManagedFragmentHandler<? extends BaseFragment> registerFragment(Class<T> fragmentClass) {
        ManagedFragmentHandler<? extends BaseFragment> fragmentHandler = context.getManagedFragmentHandler(fragmentClass);

        fragmentHandler.getController().initialized();
        return fragmentHandler;
    }

    @Override
    /**
     * The handle method always runs outside the main application thread. You can create new nodes,
     * execute long running tasks but you are not allowed to manipulate existing nodes here.
     */
    public Node handle(final Message<Event, Object> message) {
        return null;
    }


    @Override
    /**
     * The postHandle method runs always in the main application thread.
     */
    public Node postHandle(final Node arg0,
                           final Message<Event, Object> message) {
        // runs in FX application thread

        if (message.getMessageBody() != null
                && message.isMessageBodyTypeOf(Class.class)) {
            Class<BaseFragment> baseFragmentClass = (Class<BaseFragment>) message.getMessageBody();
            managedFragmentHandler = managedFragmentMap.get(baseFragmentClass);
            if (managedFragmentHandler == null) {
                managedFragmentHandler = registerFragment((Class<? extends BaseFragment>) message.getMessageBody());
                managedFragmentMap.put(baseFragmentClass, managedFragmentHandler);
            }

            rootPane.getChildren().clear();
            rootPane.getChildren().add(managedFragmentHandler.getFragmentNode());

        }
        return null;
    }

}