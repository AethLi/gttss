package cn.aethli.gttss.controller;

import cn.aethli.gttss.domain.ResponseMessage;
import cn.aethli.gttss.domain.SysUser;
import cn.aethli.gttss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SessionAttributes(value = {"identifyingCode", "currentUser", "verifyT"}, types = {String.class, SysUser.class, String.class})
//@SessionAttributes(value = {"currentUser"}, types = {SysUser.class})
@RestController
@RequestMapping(value = "/user")
public class UserCtrl extends BaseCtrl {

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param model           添加currentUser进session
     * @param identifyingCode 验证码
     * @param params          前台参数需要：account,password,ACAPTCHA
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(Model model, @ModelAttribute("identifyingCode") String identifyingCode,
                        @ModelAttribute("verifyT") String verifyT, @RequestBody Map<String, Object> params) {
        model.addAttribute("currentUser", null);//清空登录
        try {
            SysUser sysUser = new SysUser();
            if (params.get("ACAPTCHA").equals(identifyingCode)) {
                sysUser.setAccount((String) params.get("account"));
                sysUser.setPassword((String) params.get("password"));
                try {
                    if (verifyT.equals("sdiofyhasdiofyhqweohf9o")) {
                        sysUser = userService.loginT(sysUser, (String) params.get("ACAPTCHA"));
                    } else {
                        sysUser = userService.login(sysUser, (String) params.get("ACAPTCHA"));
                    }
                    if (verifyT.equals("sdiofyhasdiofyhqweohf9o")) {
                        userService.checkTeacher(sysUser);
                    } else {
                        userService.checkStudent(sysUser);
                    }
                    model.addAttribute("currentUser", sysUser);
                    model.addAttribute("identifyingCode", "aethli.cn");//失效化验证码
                    return new ResponseMessage(ResponseMessage.STATUS_OK);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ResponseMessage(ResponseMessage.STATUS_FAIL, e.getMessage());
                }
            } else {
                return new ResponseMessage(ResponseMessage.STATUS_FAIL, "验证码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR, "请求错误", e.getMessage());
        } finally {
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
        return new ResponseMessage(ResponseMessage.STATUS_OK);
    }

    @RequestMapping(value = "getMyself")
    public Object getMyself(HttpServletRequest request, Model model, @ModelAttribute("verifyT") String verifyT) {
        try {
            if (verifyT.equals("sdiofyhasdiofyhqweohf9o")) {
                return new ResponseMessage(ResponseMessage.STATUS_OK, userService.getMyselfT(getSysUser(model)));
            } else {
                return new ResponseMessage(ResponseMessage.STATUS_OK, userService.getMyself(getSysUser(model)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR);
        }
    }

    @RequestMapping(value = "getMyOverview")
    public Object getMyOverview(Model model, @ModelAttribute("verifyT") String verifyT) {
        try {
            if (verifyT.equals("sdiofyhasdiofyhqweohf9o")) {
                return new ResponseMessage(ResponseMessage.STATUS_OK, userService.getMyOverviewT(getSysUser(model)));
            } else {
                return new ResponseMessage(ResponseMessage.STATUS_OK, userService.getMyOverview(getSysUser(model)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR);
        }
    }

    @RequestMapping(value = "saveMyOverview")
    public Object saveMyOverview(Model model, @RequestBody Map<String, Object> params, @ModelAttribute("verifyT") String verifyT) {
        try {
            if (verifyT.equals("sdiofyhasdiofyhqweohf9o")) {
                return new ResponseMessage(ResponseMessage.STATUS_OK, userService.saveMyOverviewT(getSysUser(model), params));
            } else {
                return new ResponseMessage(ResponseMessage.STATUS_OK, userService.saveMyOverview(getSysUser(model), params));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage(ResponseMessage.STATUS_ERROR);
        }
    }
}
