package com.example.jakubkalinowski.contractfoxandroid.Model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Map;

/**
 * Created by MD on 11/16/2016.
 */

@IgnoreExtraProperties

public class ChatMessage {

    public ChatMessage(){}


    public ChatMessage(String senderUserId, String recieverUserId,
                       Map<String, String> chatMessageCreatedAtFirebaseTimestamp,
                       String base64ImageString, String chatMessagePushID,
                       String referencedChatSessionPushId) {
        this.senderUserId = senderUserId;
        this.recieverUserId = recieverUserId;
        this.chatMessageCreatedAtFirebaseTimestamp = chatMessageCreatedAtFirebaseTimestamp;
        this.base64ImageString = base64ImageString;
        this.chatMessagePushID = chatMessagePushID;
        this.referencedChatSessionPushId = referencedChatSessionPushId;
    }

    public String senderUserId;
    public String recieverUserId;
    public Map<String, String> chatMessageCreatedAtFirebaseTimestamp;
    public String base64ImageString;
    public String chatMessagePushID;
    public String referencedChatSessionPushId;

    public String getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getRecieverUserId() {
        return recieverUserId;
    }

    public void setRecieverUserId(String recieverUserId) {
        this.recieverUserId = recieverUserId;
    }

    public Map<String, String> getChatMessageCreatedAtFirebaseTimestamp() {
        return chatMessageCreatedAtFirebaseTimestamp;
    }

    public void setChatMessageCreatedAtFirebaseTimestamp
            (Map<String, String> chatMessageCreatedAtFirebaseTimestamp) {
        this.chatMessageCreatedAtFirebaseTimestamp = chatMessageCreatedAtFirebaseTimestamp;
    }

    public String getBase64ImageString() {
        return base64ImageString;
    }

    public void setBase64ImageString(String base64ImageString) {
        this.base64ImageString = base64ImageString;
    }

    public String getChatMessagePushID() {
        return chatMessagePushID;
    }

    public void setChatMessagePushID(String chatMessagePushID) {
        this.chatMessagePushID = chatMessagePushID;
    }

    public String getReferencedChatSessionPushId() {
        return referencedChatSessionPushId;
    }

    public void setReferencedChatSessionPushId(String referencedChatSessionPushId) {
        this.referencedChatSessionPushId = referencedChatSessionPushId;
    }


}