package com.riosr.context;

import com.riosr.Main;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackageClasses = Main.class)
@EnableAspectJAutoProxy
public class ProjectConfig {
}
