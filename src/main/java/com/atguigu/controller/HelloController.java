package com.atguigu.controller;

import com.atguigu.Service.HelloService;
import com.atguigu.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@Slf4j
public class HelloController {


    @Autowired
    HelloService helloService;

    @ResponseBody
    @GetMapping("/hello")
    public String hello( String name,int age){
        Future<String> future = helloService.hello(name);
        String s="";
        try {
             s = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        log.info("str"+s);
        return s;
    }


    @ResponseBody
    @PostMapping("/stringParameter")
    public Person stringParameter(@RequestBody String  stringParameterJson){
        Person person=new Person();
        person.setName("lisi");
        person.setAge(12);
        return person;
    }






}
