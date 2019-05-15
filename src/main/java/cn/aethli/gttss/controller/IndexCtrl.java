package cn.aethli.gttss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SessionAttributes(value = {"verifyT", "identifyingCode"}, types = {String.class, String.class})

@Controller
public class IndexCtrl {

    @ResponseBody
    @RequestMapping(value = "/")
    public Object IndexCtrl(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("verifyT", "sdfjsadffla");//用作教师登录判断
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("gttsss/index.html");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/t")
    public Object IndexTCtrl(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("verifyT", "sdiofyhasdiofyhqweohf9o");//用作教师登录判断
        model.addAttribute("identifyingCode", "sdfddasfhasfhasi");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("gttsst/index.html");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/a")
    public Object IndexACtrl(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("verifyT", "sahfohoqhwefqfp");//用作教师登录判断
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("gttssa/index.html");
        return modelAndView;
    }
}
