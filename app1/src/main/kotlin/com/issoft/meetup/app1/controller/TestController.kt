package com.issoft.meetup.app1.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController @Autowired constructor() {

    @RequestMapping("/test1/{id}")
    fun test1(@PathVariable id: String): String {
        return "app1 " + id + " "+ System.currentTimeMillis()
    }

    @RequestMapping("/test")
    fun test(): String {
        return "ok"
    }

}