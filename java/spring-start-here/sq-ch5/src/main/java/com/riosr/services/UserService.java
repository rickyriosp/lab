package com.riosr.services;

import com.riosr.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final CommentRepository commentRepository;

    public UserService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
        System.out.println("UserService instance created");
    }

    public CommentRepository getCommentRepository() {
        return commentRepository;
    }
}
