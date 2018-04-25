package com.magic.jovi;

import com.magic.jovi.entities.vo.QueryVO;
import com.magic.jovi.services.ProblemCollectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;

/**
 * Created by fanjiawei on 2018/4/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProblemCollectServiceTest {

    @Autowired
    private ProblemCollectService problemCollectService;

    @Test
    public void pageTest() throws ParseException {
        QueryVO queryVO = new QueryVO();

        queryVO.setPageNum(0);
        queryVO.setPageSize(10);

        problemCollectService.queryInfo(queryVO);
    }
}
