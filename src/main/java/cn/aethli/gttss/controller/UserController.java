package cn.aethli.gttss.controller;

import cn.aethli.gttss.domain.ResponseMessage;
import cn.aethli.gttss.domain.SysUser;
import cn.aethli.gttss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SessionAttributes(value = {"identifyingCode", "currentUser"}, types = {String.class, SysUser.class})

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param model           添加currentUser进session
     * @param identifyingCode 验证码
     * @param params          前台参数需要：account,password,ACAPTCHA
     * @param sessionStatus
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(Model model, @ModelAttribute("identifyingCode") String identifyingCode, @RequestBody Map<String, Object> params, SessionStatus sessionStatus) {
        SysUser sysUser = new SysUser();
        if (params.get("ACAPTCHA").equals(identifyingCode)) {
            sysUser.setAccount((String) params.get("account"));
            sysUser.setPassword((String) params.get("password"));
            model.addAttribute("identifyingCode", "aethli.cn");//失效化验证码
            model.addAttribute("currentUser", null);//清空登录
            try {
                sysUser = userService.login(sysUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return new ResponseMessage(ResponseMessage.STATUS_FAIL, "验证码错误");
        }
        return new ResponseMessage(ResponseMessage.STATUS_ERROR, "请求错误");
    }

    @RequestMapping(value = "/logout")
    public Object logoutCtrl(HttpServletRequest request) {
        request.getSession().invalidate();
        return null;
    }
}
