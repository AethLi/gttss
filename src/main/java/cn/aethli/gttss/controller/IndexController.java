package cn.aethli.gttss.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @ResponseBody
    @RequestMapping(value = "/")
    public Object IndexCtrl(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("gttsss/index.html");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/t")
    public Object IndexTCtrl(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("gttsst/index.html");
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping(value = "/a")
    public Object IndexACtrl(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("gttssa/index.html");
        return modelAndView;
    }
}
