package com.github.duychuongvn.jreddit.dto;


import lombok.Builder;
import lombok.Getter;
import net.dean.jraw.models.Message;

@Builder
@Getter
public class MessageDto {

    private String receiptUsername;
    private Message message;
}
