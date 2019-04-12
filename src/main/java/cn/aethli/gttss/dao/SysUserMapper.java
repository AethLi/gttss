package cn.aethli.gttss.dao;

import cn.aethli.gttss.domain.SysUser;
import org.springframework.stereotype.Component;

public interface SysUserMapper {
    public void insertWithAll(SysUser user);

    public SysUser selectByAccount(SysUser user);
}
