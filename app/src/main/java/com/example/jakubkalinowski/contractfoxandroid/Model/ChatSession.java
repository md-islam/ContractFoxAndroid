package com.example.jakubkalinowski.contractfoxandroid.Model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Map;

/**
 * Created by MD on 11/16/2016.
 */
@IgnoreExtraProperties
public class ChatSession {

    public Map<String, String> createdAtFirebaseTimestamp;
    public String firebasePushId;
    public Map<String, Map<String, Boolean>> usersInChat; //reference to the two


    public ChatSession() {
    }

    public ChatSession(Map<String, String> createdAtFirebaseTimestamp, String firebasePushId,
                       Map<String, Map<String, Boolean>> usersInChat) {
        this.createdAtFirebaseTimestamp = createdAtFirebaseTimestamp;
        this.firebasePushId = firebasePushId;
        this.usersInChat = usersInChat;
    }


    public Map<String, Map<String, Boolean>> getUsersInChat() {
        return usersInChat;
    }

    public void setUsersInChat(Map<String, Map<String, Boolean>> usersInChat) {
        this.usersInChat = usersInChat;
    }

    public String getFirebasePushId() {
        return firebasePushId;
    }

    public void setFirebasePushId(String firebasePushId) {
        this.firebasePushId = firebasePushId;
    }

    public Map<String, String> getCreatedAtFirebaseTimestamp() {
        return createdAtFirebaseTimestamp;
    }

    public void setCreatedAtFirebaseTimestamp(Map<String, String> createdAtFirebaseTimestamp) {
        this.createdAtFirebaseTimestamp = createdAtFirebaseTimestamp;
    }


}