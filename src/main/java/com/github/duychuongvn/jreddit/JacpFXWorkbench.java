package com.github.duychuongvn.jreddit;

import com.github.duychuongvn.jreddit.config.AppConfig;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.workbench.Workbench;
import org.jacpfx.api.componentLayout.WorkbenchLayout;
import org.jacpfx.api.message.Message;
import org.jacpfx.api.util.ToolbarPosition;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.components.menuBar.JACPMenuBar;
import org.jacpfx.rcp.context.Context;
import org.jacpfx.rcp.workbench.FXWorkbench;

@Workbench(id = "id1", name = "workbench",
        perspectives = {
                AppConfig.PERSPECTIVE_ONE
        })
public class JacpFXWorkbench implements FXWorkbench {

    @Resource
    private Context context;

    @Override
    public void handleInitialLayout(final Message<Event, Object> action,
                                    final WorkbenchLayout<Node> layout, final Stage stage) {
        layout.setWorkbenchXYSize(1024, 768);
        layout.registerToolBar(ToolbarPosition.NORTH);
        layout.setStyle(StageStyle.DECORATED);
        layout.setMenuEnabled(false);

    }

    @Override
    public void postHandle(final FXComponentLayout layout) {
//        final JACPMenuBar menu = layout.getMenu();
//        menu.getMenus().addAll(new Menu(""));
    }



}