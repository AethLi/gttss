package cn.aethli.gttss.controller;

import cn.aethli.gttss.domain.ResponseMessage;
import cn.aethli.gttss.domain.SysUser;
import cn.aethli.gttss.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

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

    @RequestMapping(value = "/customizeTopicTeacherVerify")
    public Object customizeTopicTeacherVerify(Model model, @RequestBody Map<String, Object> params) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, verifyService.customizeTopicTeacherVerify(getSysUser(model), params));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }
    }
    @RequestMapping(value = "/customizeTopicAdminVerify")
    public Object customizeTopicAdminVerify(Model model, @RequestBody Map<String, Object> params) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, verifyService.customizeTopicAdminVerify(getSysUser(model), params));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }
    }
    @RequestMapping(value = "/saveOpeningReportVerify")
    public Object saveOpeningReportVerify(Model model, @RequestBody Map<String, Object> params) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, verifyService.saveOpeningReportVerify(getSysUser(model), params));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }
    }
    @RequestMapping(value = "/saveOpeningReportVerifyA")
    public Object saveOpeningReportVerifyA(Model model, @RequestBody Map<String, Object> params) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, verifyService.saveOpeningReportVerifyA(getSysUser(model), params));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }
    }

    @RequestMapping(value = "/getOpeningReportVerifyById")
    public Object getOpeningReportVerifyById(@RequestBody Map<String, Object> params) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, verifyService.getOpeningReportVerifyById(params));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }
    }

    @RequestMapping(value = "/getVerifyById")
    public Object getVerifyById(@RequestBody Map<String, Object> params) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, verifyService.getVerifyById(params));
        } catch (Exception e) {
//            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }
    }

    @RequestMapping(value = "/saveDefenseVerify")
    public Object saveDefenseVerify(Model model,@RequestBody Map<String, Object> params) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, verifyService.saveDefenseVerify(getSysUser(model),params));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }
    }

    @RequestMapping(value = "/saveTopicBookVerify")
    public Object saveTopicBookVerify(Model model,@RequestBody Map<String, Object> params) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, verifyService.saveTopicBookVerify(getSysUser(model),params));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }
    }
}
