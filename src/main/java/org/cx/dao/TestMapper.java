package org.cx.dao;

import org.cx.model.Test;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface TestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Test test);

    List<Test> selectByParam(Test test);

    Test selectByPrimaryKey(Integer id);

    int updateById(Test test);
}