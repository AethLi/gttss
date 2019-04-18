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
    public Object login(Model model, @ModelAttribute("identifyingCode") String identifyingCode, @ModelAttribute("verifyT") String verifyT, @RequestBody Map<String, Object> params, SessionStatus sessionStatus) {
        try {
            SysUser sysUser = new SysUser();
            if (params.get("ACAPTCHA").equals(identifyingCode)) {
                sysUser.setAccount((String) params.get("account"));
                sysUser.setPassword((String) params.get("password"));
                try {
                    sysUser = userService.login(sysUser, (String) params.get("ACAPTCHA"));
                    if (verifyT.equals("sdiofyhasdiofyhqweohf9o")){
                        userService.checkTeacher(sysUser);
                    }
                    userService.checkStudent(sysUser);
                    return new ResponseMessage(ResponseMessage.STATUS_OK);
                } catch (Exception e) {
//                e.printStackTrace();
                    return new ResponseMessage(ResponseMessage.STATUS_FAIL, e.getMessage());
                }
            } else {
                return new ResponseMessage(ResponseMessage.STATUS_FAIL, "验证码错误");
            }
        } catch (Exception e) {
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, "请求错误", e.getMessage());
        } finally {
            model.addAttribute("identifyingCode", "aethli.cn");//失效化验证码
            model.addAttribute("currentUser", null);//清空登录
        }
    }

    /**
     * 登出，清空session
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout")
    public Object logoutCtrl(HttpServletRequest request) {
        request.getSession().invalidate();
        return null;
    }
}
