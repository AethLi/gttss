package cn.aethli.gttss.controller;

import cn.aethli.gttss.domain.Batch;
import cn.aethli.gttss.domain.SysUser;
import cn.aethli.gttss.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes(value = {"currentUser"}, types = {SysUser.class})
@RestController(value = "/")
public class BaseController {

    @Autowired
    BaseService baseService;

    public SysUser sysUser = null;

    public SysUser getSysUser(Model model) {
        return (SysUser) model.asMap().get("currentUser");
    }

    public Batch getCurrentBatch() {
        return baseService.getCurrentBatch();
    }
}