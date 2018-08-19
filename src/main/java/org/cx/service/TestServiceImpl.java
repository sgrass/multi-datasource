package org.cx.service;

import org.cx.dao.TestMapper;
import org.cx.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author grass
 * @date 2018/8/12
 */
@Service("testService")
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Test test) {
        return 0;
    }

    @Override
    public List<Test> selectByParam(Test test) {
        return null;
    }

    @Cacheable(value="common",key="'id_'+#id")
    @Override
    public Test selectByPrimaryKey(Integer id) {
        return testMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(Test test) throws Exception {
        test = this.selectByPrimaryKey(1);
        System.out.println(test);
        testMapper.updateById(test);

        Test t = new Test();
        t.setNums("3333");
        t.setName("DDDDDD");
        testMapper.insert(t);

//        Integer.parseInt("a");
        return 0;
    }
}
