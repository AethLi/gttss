package cn.aethli.gttss.service;

import cn.aethli.gttss.dao.StudentMapper;
import cn.aethli.gttss.dao.SysUserMapper;
import cn.aethli.gttss.dao.TeacherMapper;
import cn.aethli.gttss.domain.Student;
import cn.aethli.gttss.domain.SysUser;
import cn.aethli.gttss.domain.Teacher;
import cn.aethli.gttss.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    public SysUser login(SysUser sysUser, String acaptcha) throws Exception {
        SysUser user = sysUserMapper.selectByAccount(sysUser);
        if ((StringUtils.toMD5(user.getPassword() + acaptcha)).equals(sysUser.getPassword())) {
            user.setPassword("已去除返回密码值");
            return user;
        } else throw new Exception("账号或密码错误");
    }

    public void checkStudent(SysUser sysUser) throws Exception {
        Student student = new Student();
        student.setUserId(sysUser.getUserId());
        if (studentMapper.selectById(student) == null) {
            throw new Exception("用户身份信息错误");
        }
    }

    public void checkTeacher(SysUser sysUser) throws Exception {
        Teacher teacher = new Teacher();
        teacher.setUserId(sysUser.getUserId());
        if (teacherMapper.selectById(teacher) == null) {
            throw new Exception("用户身份信息错误");
        }
    }
}
