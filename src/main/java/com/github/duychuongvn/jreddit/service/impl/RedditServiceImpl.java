package com.github.duychuongvn.jreddit.service.impl;

import com.github.duychuongvn.jreddit.UserSourceNotInitializedException;
import com.github.duychuongvn.jreddit.dto.MessageDto;
import com.github.duychuongvn.jreddit.dto.UserSource;
import com.github.duychuongvn.jreddit.service.RedditService;
import com.github.duychuongvn.jreddit.service.UserStoreService;
import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.Message;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.pagination.BarebonesPaginator;
import net.dean.jraw.references.InboxReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class RedditServiceImpl implements RedditService {

    private Map<String, RedditClient> redditClientMap = Collections.synchronizedMap(new HashMap<>());

    @Autowired
    private UserStoreService userStoreService;

    @Override
    public List<MessageDto> loadMessages() {
        List<MessageDto> messageDtos = new ArrayList<>();
        redditClientMap.entrySet().forEach(entry -> {
            BarebonesPaginator<Message> unread = entry.getValue().me().inbox().iterate("unread")
                    .build();
            unread.next().forEach(message -> {
                messageDtos.add(MessageDto.builder()
                        .receiptUsername(entry.getKey())
                        .message(message)
                        .build());
            });
        });
        return messageDtos;
    }

    @Override
    public void reply(MessageDto messageDto, String content) {

        RedditClient redditClient = redditClientMap.get(messageDto.getReceiptUsername());
        InboxReference inbox = redditClient.me().inbox();
        inbox.replyTo(messageDto.getMessage().getFullName(), content);
        redditClient.me().inbox().markRead(true, messageDto.getMessage().getFullName());
    }

    @PostConstruct
    public void initClients() {
        try {
            Set<UserSource> userSources = userStoreService.loadUsers();
            userSources.forEach(userSource -> {
                try {
                    UserAgent userAgent = new UserAgent("desktop", "com.github.duychuongvn.redditclient", "v0.1", userSource.getUsername());
                    net.dean.jraw.oauth.Credentials credentials = net.dean.jraw.oauth.Credentials.script(userSource.getUsername(), userSource.getPassword(),
                            userSource.getClientId(), userSource.getSecret());
                    NetworkAdapter adapter = new OkHttpNetworkAdapter(userAgent);
                    RedditClient redditClient = OAuthHelper.automatic(adapter, credentials);

                    redditClientMap.put(userSource.getUsername(), redditClient);
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        } catch (UserSourceNotInitializedException e) {
            e.printStackTrace();
        }
    }
}
