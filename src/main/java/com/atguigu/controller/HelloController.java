package com.atguigu.controller;

import com.atguigu.bean.Person;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;

@RestController
public class HelloController {

    @ResponseBody
    @GetMapping("/hello")
    public String hello( String name,int age){

        return name;
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
