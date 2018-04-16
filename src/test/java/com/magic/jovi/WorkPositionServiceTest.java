package com.magic.jovi;

import com.magic.jovi.entities.vo.WorkPositionVO;
import com.magic.jovi.services.WorkPositionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by fanjiawei on 2018/4/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkPositionServiceTest {

    @Autowired
    private WorkPositionService workPositionService;

    @Test
    public void addTest() {
        WorkPositionVO workPositionVO = new WorkPositionVO();

        workPositionVO.setName("机器3");
        workPositionService.add(workPositionVO);
    }
}
