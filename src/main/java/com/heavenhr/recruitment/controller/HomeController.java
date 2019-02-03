package com.heavenhr.recruitment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by shridhar on 02/02/19.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    @ApiIgnore
    public String home() {
        return "redirect:swagger-ui.html";
    }
}
