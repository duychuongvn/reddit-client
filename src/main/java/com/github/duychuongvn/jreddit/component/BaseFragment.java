package com.github.duychuongvn.jreddit.component;

import javafx.fxml.FXML;
import javafx.scene.Node;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.rcp.context.Context;

import java.util.ResourceBundle;

public abstract class BaseFragment {
    @FXML
    protected Node rootPane;
    @Resource
    protected Context context;
    @Resource
    protected ResourceBundle bundle;

    protected abstract void initialized();
}
