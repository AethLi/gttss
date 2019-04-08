package cn.aethli.gttss.dao;

import cn.aethli.gttss.domain.StudentBatchGroup;

import java.util.List;

public interface StudentBatchGroupMapper {
    public void insertWithAllByList(List<StudentBatchGroup> groups);

    public void insertWithAll(StudentBatchGroup group);
}
