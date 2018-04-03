package com.magic.jovi;

import com.magic.jovi.entities.vo.WorkGroupVO;
import com.magic.jovi.services.WorkGroupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by fanjiawei on 2018/4/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkGroupServiceTest {

    @Autowired
    private WorkGroupService workGroupService;

    @Test
    public void addTest() {
        WorkGroupVO workGroupVO = new WorkGroupVO();
        workGroupVO.setName("UnitTest");
        workGroupService.add(workGroupVO);
    }
}
