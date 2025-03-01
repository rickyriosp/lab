package com.riosr.proxies;

import com.riosr.models.Comment;

public interface CommentNotificationProxy {

    public void sendComment(Comment comment);
}
