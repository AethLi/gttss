package cn.aethli.gttss.controller;

import cn.aethli.gttss.domain.ResponseMessage;
import cn.aethli.gttss.domain.SysUser;
import cn.aethli.gttss.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes(value = {"currentUser"}, types = {SysUser.class})

@RequestMapping("/verify")
@RestController
public class VerifyCtrl extends BaseCtrl {
    @Autowired
    VerifyService verifyService;

    @RequestMapping(value = "/getOpeningReportVerify")
    public Object getOpeningReportVerify(Model model) {
        try {
            return verifyService.getOpeningReportVerify(getSysUser(model));
        } catch (Exception e) {
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }
    }
}
