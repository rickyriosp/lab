package com.riosr;

import com.riosr.context.ProjectConfig;
import com.riosr.services.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        System.out.println("Before retrieving the CommentService");
        var cs1 = context.getBean(CommentService.class);
        var cs2 = context.getBean(CommentService.class);
        System.out.println("After retrieving the CommentService");

        System.out.println(cs1.getCommentRepository() == cs2.getCommentRepository());
    }
}
