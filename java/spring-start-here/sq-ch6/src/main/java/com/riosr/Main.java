package com.riosr;

import com.riosr.context.ProjectConfig;
import com.riosr.models.Comment;
import com.riosr.services.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Comment comment = new Comment();
        comment.setAuthor("Ricky");
        comment.setText("Demo Comment");

        CommentService service = context.getBean(CommentService.class);

        String value = service.publishComment(comment);
        //service.deleteComment(comment);

        logger.info(value);
    }
}