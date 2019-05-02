package cn.aethli.gttss.controller;

import cn.aethli.gttss.domain.ResponseMessage;
import cn.aethli.gttss.domain.SysUser;
import cn.aethli.gttss.service.OpeningReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;
@SessionAttributes(value = {"identifyingCode", "currentUser", "verifyT"}, types = {String.class, SysUser.class, String.class})

@RestController
@RequestMapping("/openingReport")
public class OpeningReportCtrl extends BaseCtrl {

    @Autowired
    OpeningReportService openingReportService;

    @RequestMapping("/getMyOpeningReport")
    public Object getMyOpeningReport(Model model) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, openingReportService.getMyOpeningReport(getSysUser(model)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }
    }

    @RequestMapping("/saveMyOpeningReport")
    public Object saveMyOpeningReport(Model model, @RequestBody Map<String, Object> params) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, openingReportService.saveMyOpeningReport(getSysUser(model), params));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }

    }

    @RequestMapping("/getOpeningReportById")
    public Object getOpeningReportById(@RequestBody Map<String, Object> params) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, openingReportService.getOpeningReportById(params));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }

    }

    @RequestMapping("/getMyOpenAble")
    public Object getMyOpenAble(Model model) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, openingReportService.getMyOpenAble(getSysUser(model)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }

    }
}
