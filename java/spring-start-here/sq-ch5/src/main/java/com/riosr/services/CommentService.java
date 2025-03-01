package com.riosr.services;

import com.riosr.model.Comment;
import com.riosr.repositories.CommentRepository;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Lazy
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CommentService {

    private final CommentRepository commentRepository;
    private final ApplicationContext context;

    public CommentService(CommentRepository commentRepository, ApplicationContext context) {
        this.commentRepository = commentRepository;
        this.context = context;
        System.out.println("CommentService instance created");
    }

    public CommentRepository getCommentRepository() {
        return commentRepository;
    }

    public void sendComment(Comment c) {
        CommentProcessor p = context.getBean(CommentProcessor.class);

        p.setComment(c);
        p.processComment(c);
        p.validateComment(c);
        c = p.getComment();
    }
}
