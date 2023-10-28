package com.nakul.socialmedia.demo;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE) /** If DIFFERENT instances are required */
public class TestJava {

    void run(){
        System.out.println("asdvkjbsdjkbvjidsb");
    }
}
