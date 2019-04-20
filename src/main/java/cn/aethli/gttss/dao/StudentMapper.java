package cn.aethli.gttss.dao;

import cn.aethli.gttss.domain.Student;
import org.springframework.stereotype.Component;

@Component
public interface StudentMapper {
    void insertWithAll(Student student);

    Student selectById(Student student);

    void updateWithNameById(Student student);
}
