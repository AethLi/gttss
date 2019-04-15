package cn.aethli.gttss.service;

import cn.aethli.gttss.dao.SysUserMapper;
import cn.aethli.gttss.domain.SysUser;
import cn.aethli.gttss.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public SysUser login(SysUser sysUser, String acaptcha) throws Exception {
        SysUser user = sysUserMapper.selectByAccount(sysUser);
        if ((StringUtils.toMD5(user.getPassword() + acaptcha)).equals(sysUser.getPassword())) {
            user.setPassword("已去除返回密码值");
            return user;
        } else throw new Exception("账号或密码错误");
    }
}
