package cn.aethli.gttss.controller;

import cn.aethli.gttss.domain.ResponseMessage;
import cn.aethli.gttss.domain.SysUser;
import cn.aethli.gttss.service.DefenseService;
import cn.aethli.gttss.service.ResultSubmitService;
import cn.aethli.gttss.service.SysFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@SessionAttributes(value = {"identifyingCode", "currentUser", "verifyT"}, types = {String.class, SysUser.class, String.class})

@RestController
@RequestMapping("/file")
public class FileCtrl extends BaseCtrl {

    @Autowired
    DefenseService defenseService;
    @Autowired
    ResultSubmitService resultSubmitService;
    @Autowired
    SysFileService sysFileService;

    @RequestMapping("/defenseDraftUpload")
    public Object defenseDraftUpload(@RequestParam(value = "file", required = true) MultipartFile file, Model model) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, defenseService.defenseDraftUpload(file, getSysUser(model)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }
    }

    @RequestMapping("/resultSubmitUpload")
    public Object resultSubmitUpload(@RequestParam(value = "file", required = true) MultipartFile file, Model model) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, resultSubmitService.resultSubmitUpload(file, getSysUser(model)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }
    }

    @RequestMapping("/getMyDefenseDraft")
    public Object getMyDefenseDraft(Model model) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, defenseService.getMyDefenseDraft(getSysUser(model)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }
    }

    @RequestMapping("/getDefenseDraftById")
    public Object getDefenseDraftById(Model model, @RequestBody Map<String,Object> params) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, defenseService.getDefenseDraftById(getSysUser(model),params));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }
    }


    @RequestMapping("/getMyResultSubmit")
    public Object getMyResultSubmit(Model model) {
        try {
            return new ResponseMessage(ResponseMessage.STATUS_OK, resultSubmitService.getMyResultSubmit(getSysUser(model)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }
    }

    @RequestMapping("/downloadById")
    public Object downloadById(HttpServletRequest request, HttpServletResponse response) {
        try {
            sysFileService.downloadById(request, response);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, e.getMessage());
        }
    }
}
