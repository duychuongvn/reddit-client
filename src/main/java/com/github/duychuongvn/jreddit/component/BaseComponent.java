package com.github.duychuongvn.jreddit.component;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseComponent implements FXComponent {
    @FXML
    protected VBox rootPane;
    @Resource
    protected Context context;

    private BaseFragment selectedFragment;
    private List<BaseFragment> fragments = new ArrayList<>();

    protected <T extends BaseFragment> Pair<Node, T> registerFragment(Class<T> fragmentClass) {
        ManagedFragmentHandler<T> fragment = context.getManagedFragmentHandler(fragmentClass);
        this.fragments.add(fragment.getController());
        rootPane.getChildren().clear();
        rootPane.getChildren().addAll(fragment.getFragmentNode());
        return new Pair<>(fragment.getFragmentNode(), fragment.getController());
    }
}
