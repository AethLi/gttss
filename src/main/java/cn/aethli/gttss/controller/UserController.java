package cn.aethli.gttss.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, Model model, @ModelAttribute String identifyingCode, @RequestBody Map<String, Object> params) {
        String account = (String) params.get("account");
        String password = (String) params.get("password");
        String CAPTCHA = (String) params.get("ACAPTCHA");

        return null;
    }

    @RequestMapping(value = "/logout")
    public Object logoutCtrl(HttpServletRequest request) {
        request.getSession().invalidate();
        return null;
    }
}
