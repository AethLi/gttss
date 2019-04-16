package cn.aethli.gttss.dao;

import cn.aethli.gttss.domain.StudentBatchGroup;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentBatchGroupMapper {
    public void insertWithAllByList(List<StudentBatchGroup> groups);

    public void insertWithAll(StudentBatchGroup group);
}
