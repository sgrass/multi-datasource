package org.cx.service;

import org.cx.model.Test;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author grass
 * @date 2018/8/12
 */
public interface TestService {

    int deleteByPrimaryKey(Integer id);

    int insert(Test test);

    List<Test> selectByParam(Test test);

    Test selectByPrimaryKey(Integer id);

    int updateById(Test test) throws Exception;
}
