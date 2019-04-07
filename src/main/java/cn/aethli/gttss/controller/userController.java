package cn.aethli.gttss.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/user")
public class userController {
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, Model model){

        return null;
    }
}
