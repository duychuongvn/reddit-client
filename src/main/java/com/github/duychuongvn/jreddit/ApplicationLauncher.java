package com.github.duychuongvn.jreddit;

import com.github.duychuongvn.jreddit.config.AppConfig;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.jacpfx.rcp.workbench.FXWorkbench;
import org.jacpfx.spring.launcher.AFXSpringJavaConfigLauncher;

public class ApplicationLauncher extends AFXSpringJavaConfigLauncher {

    @Override
    protected Class<? extends FXWorkbench> getWorkbenchClass() {
        return JacpFXWorkbench.class;
    }

    @Override
    protected String[] getBasePackages() {
        return new String[]{"com.github.duychuongvn"};
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void postInit(Stage stage) {
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0)                                      ;
        });
    }

    @Override
    protected Class<?>[] getConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }
}