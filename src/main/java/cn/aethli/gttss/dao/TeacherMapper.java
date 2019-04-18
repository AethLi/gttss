package cn.aethli.gttss.dao;

import cn.aethli.gttss.domain.Teacher;
import org.springframework.stereotype.Component;

@Component
public interface TeacherMapper {
    public void insertWithAll(Teacher teacher);

    Teacher selectById(Teacher teacher);
}
