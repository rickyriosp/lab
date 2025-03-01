package com.riosr;

import com.riosr.context.ApplicationConfiguration;
import com.riosr.models.Comment;
import com.riosr.services.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        Comment comment = new Comment();
        comment.setAuthor("Riosr");
        comment.setText("Demo comment");

        CommentService commentService = context.getBean(CommentService.class);
        commentService.publishComment(comment);
    }
}
