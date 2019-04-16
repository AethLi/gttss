package cn.aethli.gttss.dao;

import cn.aethli.gttss.domain.Student;
import org.springframework.stereotype.Component;

@Component
public interface StudentMapper {
    public void insertWithAll(Student student);
}
