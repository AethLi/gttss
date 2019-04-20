package cn.aethli.gttss.controller;

import cn.aethli.gttss.domain.SysUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;


@RestController(value = "/")
public class BaseController {
    public SysUser sysUser = null;

    public SysUser getSysUser(Model model) {
        return (SysUser) model.asMap().get("currentUser");
    }
}
