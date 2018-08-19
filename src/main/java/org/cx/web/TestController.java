package org.cx.web;

import org.cx.dao.TestMapper;
import org.cx.model.Test;
import org.cx.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author grass
 * @date 2018/8/11
 */
@RestController
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private TestService testService;

    @GetMapping("/aa")
    public List<Test> aa() {
        List<Test> list = testMapper.selectByParam(null);
        System.out.println(list.toString());
        return list;
    }

    @GetMapping("/bb")
    public Test bb() {
        Test test = new Test();
        test.setNums("222");
        test.setName("BBB");
        int i = testMapper.insert(test);
        System.out.println(i);
        return test;
    }

    @GetMapping("/cc")
    public Test cc() {
        Test test = testService.selectByPrimaryKey(1);
        return test;
    }

    @GetMapping("/dd")
    public void dd() {
        try {
            testService.updateById(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
