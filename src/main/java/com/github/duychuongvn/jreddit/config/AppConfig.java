package com.github.duychuongvn.jreddit.config;

import com.github.duychuongvn.jreddit.service.RedditService;
import com.github.duychuongvn.jreddit.service.UserStoreService;
import com.github.duychuongvn.jreddit.service.impl.RedditServiceImpl;
import com.github.duychuongvn.jreddit.service.impl.UserStoreServiceImpl;
import com.github.duychuongvn.jreddit.util.RedditClientUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfig {
    public static final String PERSPECTIVE_ONE = "idPone";

    public static final String COMPONENT_LEFT = "id002";
    public static final String COMPONENT_RIGHT = "id003";
    public static final String COMPONENT_USER_SOURCE = "id004";

    public static final String FRAGMENT_MESSAGE = "idf001";
    public static final String FRAGMENT_USER = "idf002";

    public static final String TARGET_CONTAINER_LEFT = "PLeft";
    public static final String TARGET_CONTAINER_MAIN = "PMain";

    @Bean
    public UserStoreService userStoreService() {
        return new UserStoreServiceImpl();
    }

    @Bean
    public RedditService redditService() {
        return new RedditServiceImpl();
    }

    @Bean
    public RedditClientUtil redditClientUtil() {
        return new RedditClientUtil();
    }

}
