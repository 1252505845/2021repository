package com.atguigu.controller;

import com.atguigu.bean.Person;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }


    @ResponseBody
    @PostMapping("/stringParameter")
    public Person stringParameter(String  stringParameterJson){
        Person person=new Person();
        person.setName("lisi");
        person.setAge(12);
        return person;
    }






}
