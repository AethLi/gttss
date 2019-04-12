package cn.aethli.gttss.controller;

import cn.aethli.gttss.domain.SysUser;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;


@RestController(value = "/")
public class BaseController {
    public SysUser sysUser = null;

    public SysUser getSysUser(@ModelAttribute("currentUser") SysUser sysUser) {
        return sysUser;
    }
}
