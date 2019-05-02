package cn.aethli.gttss.controller;

import cn.aethli.gttss.domain.ResponseMessage;
import cn.aethli.gttss.domain.SysUser;
import cn.aethli.gttss.service.MediumTermRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

@SessionAttributes(value = {"identifyingCode", "currentUser", "verifyT"}, types = {String.class, SysUser.class, String.class})

@RequestMapping("/mediumTermRecord")
@RestController
public class MediumTermRecordCtrl extends BaseCtrl {
    @Autowired
    MediumTermRecordService mediumTermRecordService;

    @RequestMapping("/saveMediumTermRecord")
    public Object saveMediumTermRecord(Model model, @RequestBody Map<String, Object> params) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, mediumTermRecordService.saveMediumTermRecord(getSysUser(model), params));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }
    }

    @RequestMapping("/getMyMediumTermRecord")
    public Object getMyMediumTermRecord(Model model) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, mediumTermRecordService.getMyMediumTermRecord(getSysUser(model)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }
    }
}
