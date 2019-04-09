package cn.aethli.gttss.controller;

import cn.aethli.gttss.domain.SysUser;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController(value = "/")
public class BaseController {
    public SysUser sysUser = null;

    public SysUser getSysUser(@ModelAttribute SysUser sysUser) {
        return sysUser;
    }
}
