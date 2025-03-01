package com.riosr.repositories;

import com.riosr.models.Comment;

public interface CommentRepository {

    void storeComment(Comment comment);
}
