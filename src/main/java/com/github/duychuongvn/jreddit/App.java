package com.github.duychuongvn.jreddit;

import com.github.duychuongvn.jreddit.dto.UserSource;
import com.github.duychuongvn.jreddit.dto.UserStore;
import net.dean.jraw.JrawUtils;
import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.*;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.oauth.StatefulAuthHelper;
import net.dean.jraw.oauth.TokenStore;
import net.dean.jraw.pagination.BarebonesPaginator;
import net.dean.jraw.pagination.DefaultPaginator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        String dataDir="reddit.bin";
        UserSource userSource = new UserSource("caokyhuynh","Diemhang@21786","D3faF_1RQu6SOQ", "_E3mJYWioU7b2CTEbn7QgSzOWJ8");
        UserSource userSource2 = new UserSource("chuonghuynhtest","aBc123!@#","roRYhmT_nCGltA", "i8GCBtdNkX_EXVk2TO5KAf5Rg2g");
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(dataDir))) {
            UserStore userStore = new UserStore();
            userStore.addUserSource(userSource);
            userStore.addUserSource(userSource2);
            outputStream.writeObject(userStore);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
////        String clientId = "D3faF_1RQu6SOQ";
//        String clientId = "roRYhmT_nCGltA";
////        String secret = "_E3mJYWioU7b2CTEbn7QgSzOWJ8";
//        String secret = "i8GCBtdNkX_EXVk2TO5KAf5Rg2g";
////        String reditUsername = "chuonghuynhtest";
////        String password = "aBc123!@#";
//        String reditUsername = "caokyhuynh";
//        String password = "Diemhang@21786";
//        UserAgent userAgent = new UserAgent("desktop", "com.github.duychuongvn.usefulbot", "v0.1", reditUsername);
//        net.dean.jraw.oauth.Credentials credentials = net.dean.jraw.oauth.Credentials.script(reditUsername, password,
//                clientId, secret);
//        // This is what really sends HTTP requests
//        NetworkAdapter adapter = new OkHttpNetworkAdapter(userAgent);
//
//// Authenticate and get a RedditClient instance
//        RedditClient redditClient = OAuthHelper.automatic(adapter, credentials);
//
//
//        BarebonesPaginator<Message> unread = redditClient.me().inbox().iterate("unread")
//                .build();
//
//        Listing<Message> firstPage = unread.next();
//
//        for (Message message : firstPage) {
//            System.out.println(message.getBody());
//        }
//        DefaultPaginator<Submission> frontPage = redditClient.frontPage()
//                .sorting(SubredditSort.TOP)
//                .timePeriod(TimePeriod.DAY)
//                .limit(30)
//                .build();
//
//        Listing<Submission> submissions = frontPage.next();
//        for (Submission s : submissions) {
//            System.out.println(s.getTitle());
//        }

    }
}
