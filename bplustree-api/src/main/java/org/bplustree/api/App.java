package org.bplustree.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("org.bplustree.*")
public class App 
{
	private static ApplicationContext applicationContext;
	 
    public static void main( String[] args ){
        System.setProperty("server.servlet.context-path", "/btree-server");
        SpringApplication.run(App.class, args);
    }
    
    
}
