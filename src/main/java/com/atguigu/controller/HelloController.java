package com.atguigu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
    @PostMapping("/hello")
    public String hello( @RequestBody String jsonSr){
        log.info("jsonSr---"+jsonSr);
        String name = JSONObject.parseObject(jsonSr).getString("username");
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
    @GetMapping("/say")
    public String say( ){
        String name ="sasas";int age=90;
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
    @GetMapping("/say2")
    public String say2( ){
        String name ="sasas";int age=90;
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


    /**
     * 测试HttpClient
     * @param stringParameterJson
     * @return
     */
    @ResponseBody
    @PostMapping("/stringParameter")
    public Person stringParameter(@RequestBody String  stringParameterJson){
        JSONObject jsonObject = JSON.parseObject(stringParameterJson);
        String name = jsonObject.getString("name");
        Integer age = jsonObject.getInteger("age");

        Person person=new Person();
        person.setName(name);
        person.setAge(age);
        log.info("person--"+person);
        return person;
    }






}
