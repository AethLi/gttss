package cn.aethli.gttss.service;

import cn.aethli.gttss.dao.*;
import cn.aethli.gttss.domain.*;
import cn.aethli.gttss.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService extends BaseService {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    ClassNameMapper classNameMapper;
    @Autowired
    UnitMapper unitMapper;
    @Autowired
    MajorMapper majorMapper;
    @Autowired
    FacultyMapper facultyMapper;
    @Autowired
    StudentBatchGroupMapper studentBatchGroupMapper;

    public SysUser login(SysUser sysUser, String acaptcha) throws Exception {
        SysUser user = sysUserMapper.selectByAccount(sysUser);
        if (user == null) {
            throw new Exception("密码或账号错误");
        }
        if ((StringUtils.toMD5(user.getPassword() + acaptcha)).equals(sysUser.getPassword())) {
            user.setPassword("已去除返回密码值");
            if (user.getPermission() != 0) {
                throw new Exception("用户身份不正确");
            }
            return user;
        } else throw new Exception("账号或密码错误");
    }

    public SysUser loginT(SysUser sysUser, String acaptcha) throws Exception {
        SysUser user = sysUserMapper.selectByAccount(sysUser);
        if (user == null) {
            throw new Exception("密码或账号错误");
        }
        if (sysUser.getPassword().equals(user.getPassword())) {
            user.setPassword("已去除返回密码值");
            if (user.getPermission() != 1) {
                throw new Exception("用户身份不正确");
            }
            return user;
        } else throw new Exception("账号或密码错误");
    }

    public void checkStudent(SysUser sysUser) throws Exception {
        Student student = new Student();
        student.setUserId(sysUser.getUserId());
        if (studentMapper.selectById(student) == null) {
            throw new Exception("用户身份信息错误");
        }
        StudentBatchGroup studentBatchGroup = new StudentBatchGroup();
        studentBatchGroup.setStudentId(student.getUserId());
        List<StudentBatchGroup> studentBatchGroups = studentBatchGroupMapper.selectByStudentId(studentBatchGroup);
        if (studentBatchGroups.size() == 0) {
            throw new Exception("用户不属于任何批次");
        } else {
            List<StudentBatchGroup> collect = studentBatchGroups.stream().filter(x -> x.getBatchId().equals(getCurrentBatch().getBatchId())).collect(Collectors.toList());
            if (collect.size() == 0) {
                throw new Exception("用户不在此批次");
            }
        }
    }

    public void checkTeacher(SysUser sysUser) throws Exception {
        Teacher teacher = new Teacher();
        teacher.setUserId(sysUser.getUserId());
        if (teacherMapper.selectById(teacher) == null) {
            throw new Exception("用户身份信息错误");
        }
    }

    public Object getMyself(SysUser sysUser) throws Exception {
        Student student = new Student();
        student.setUserId(sysUser.getUserId());
        Student s = studentMapper.selectById(student);
        if (s == null) {
            throw new Exception("用户身份信息错误");
        }
        Map<String, Object> result = new HashMap<>();
        result.put("userName", s.getName());
        ClassName className = new ClassName();
        className.setId(s.getClassId());
        ClassName c = classNameMapper.selectById(className);
        result.put("classGrade", s.getGradeName() + c.getName() + c.getRepeatIndex() + "班");
        result.put("phoneNum", sysUser.getPhoneNum());
        return result;
    }

    public Object getMyselfT(SysUser sysUser) throws Exception {
        Teacher teacher = new Teacher();
        teacher.setUserId(sysUser.getUserId());
        Teacher t = teacherMapper.selectById(teacher);
        if (t == null) {
            throw new Exception("用户身份信息错误");
        }
        Map<String, Object> result = new HashMap<>();
        result.put("userName", t.getName());
        result.put("phoneNum", sysUser.getPhoneNum());
        Unit unit = new Unit();
        unit.setId(t.getUnitId());
        Unit u = unitMapper.selectById(unit);
        result.put("unitName", u.getName());
        return result;
    }

    public Object getMyOverviewT(SysUser sysUser) throws Exception {
        return getMyselfT(sysUser);
    }

    public Object getMyOverview(SysUser sysUser) {
        Map<String, Object> result = new HashMap<>();
        Student student = new Student();
        student.setUserId(sysUser.getUserId());
        Student s = studentMapper.selectById(student);
        result.put("name", s.getName());
        result.put("studentNum", s.getStudentNum());
        ClassName className = new ClassName();
        className.setId(s.getClassId());
        ClassName c = classNameMapper.selectById(className);
        result.put("className", c.getName());
        result.put("grade", s.getGradeName());
        Faculty faculty = new Faculty();
        faculty.setId(s.getFacultyId());
        faculty = facultyMapper.selectById(faculty);
        if (faculty != null) {
            result.put("faculty", faculty.getName());
        } else {
            result.put("faculty", "");
        }
        Major major = new Major();
        major.setId(s.getMajorId());
        major = majorMapper.selectById(major);
        if (major != null) {
            result.put("major", major.getName());
        } else {
            result.put("major", "");
        }
        return result;
    }

    public Object saveMyOverviewT(SysUser sysUser, Map<String, Object> params) {
        return null;
    }

    public Object saveMyOverview(SysUser sysUser, Map<String, Object> params) throws Exception {
        Student student = new Student();
        student.setUserId(sysUser.getUserId());
        student.setName((String) params.get("name"));
        studentMapper.updateWithNameById(student);
        return "修改成功";
    }

    public Object loginA(Map<String, Object> params) throws Exception {
        SysUser sysUser = new SysUser();
        sysUser.setAccount((String) params.get("account"));
        sysUser = sysUserMapper.selectByAccount(sysUser);
        if (sysUser == null) {
            throw new Exception("账号或密码错误");
        }
        if (sysUser.getPermission() != 2) {
            throw new Exception("用户身份不正确");
        }
        sysUser.setPassword("已去除密码返回值");
        return sysUser;
    }

    public String changePassword(SysUser sysUser, Map<String, Object> params) throws Exception {
        sysUser = sysUserMapper.selectById(sysUser);
        if (sysUser.getPassword().equals(StringUtils.toMD5((String) params.get("old")))) {
            sysUser.setPassword(StringUtils.toMD5((String) params.get("new")));
            try {
                sysUserMapper.updateWithPasswordById(sysUser);
            } catch (Exception e) {
                throw new Exception("修改失败");
            }
            return "修改成功";
        }
        return "原密码输入错误";
    }

    public String changeConnectionNum(SysUser sysUser, Map<String, Object> params) throws Exception {
        sysUser = sysUserMapper.selectById(sysUser);
        try {
            sysUser.setPhoneNum((String) params.get("phoneNum"));
        } catch (Exception e) {
            throw e;
        }
        sysUserMapper.updateWithPhoneNumById(sysUser);
        return "操作成功";
    }
}
