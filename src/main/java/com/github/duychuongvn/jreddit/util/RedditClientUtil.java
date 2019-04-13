package com.github.duychuongvn.jreddit.util;

import com.github.duychuongvn.jreddit.service.RedditService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class RedditClientUtil {

    private static RedditClientUtil INSTANCE;

    @Autowired
    private RedditService redditService;

    @PostConstruct
    public void init() {
        INSTANCE = new RedditClientUtil();
        INSTANCE.redditService = redditService;
    }

    public static RedditClientUtil getInstance() {
        return INSTANCE;
    }

    public RedditService getRedditService() {
        return redditService;
    }
}
