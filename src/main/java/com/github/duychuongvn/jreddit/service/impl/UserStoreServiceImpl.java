package com.github.duychuongvn.jreddit.service.impl;

import com.github.duychuongvn.jreddit.UserSourceNotInitializedException;
import com.github.duychuongvn.jreddit.dto.UserSource;
import com.github.duychuongvn.jreddit.dto.UserStore;
import com.github.duychuongvn.jreddit.service.UserStoreService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Set;

@Service
public class UserStoreServiceImpl implements UserStoreService {
//    @Value("${dataDir}")
    private String dataDir="reddit.bin";

    @Override
    public void storeUsers(List<UserSource> userSources) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(dataDir))) {
            UserStore userStore = new UserStore();
            userStore.addUserSources(userSources);
            outputStream.writeObject(userStore);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Set<UserSource> loadUsers() throws UserSourceNotInitializedException {
        try (FileInputStream fileIn = new FileInputStream(dataDir);
             ObjectInputStream in = new ObjectInputStream(fileIn);) {
            UserStore userStore = (UserStore) in.readObject();
            System.out.println(userStore.getUserSources());
            return userStore.getUserSources();

        } catch (IOException | ClassNotFoundException ex) {
            throw new UserSourceNotInitializedException(ex);
        }
    }
}
