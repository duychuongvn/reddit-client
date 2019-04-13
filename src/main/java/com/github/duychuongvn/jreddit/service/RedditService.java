package com.github.duychuongvn.jreddit.service;

import com.github.duychuongvn.jreddit.dto.MessageDto;

import java.util.List;

public interface RedditService {

    List<MessageDto> loadMessages();
    void reply(MessageDto messageDto, String content);
}
