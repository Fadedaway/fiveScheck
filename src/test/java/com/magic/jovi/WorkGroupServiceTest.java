package com.magic.jovi;

import com.magic.jovi.entities.WorkGroup;
import com.magic.jovi.entities.vo.PageVO;
import com.magic.jovi.entities.vo.WorkGroupVO;
import com.magic.jovi.repositories.WorkGroupRepo;
import com.magic.jovi.services.WorkGroupService;
import com.magic.jovi.utils.DeleteStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by fanjiawei on 2018/4/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkGroupServiceTest {

    @Autowired
    private WorkGroupService workGroupService;

    @Autowired
    private WorkGroupRepo workGroupRepo;

    @Test
    public void addTest() {
        WorkGroupVO workGroupVO = new WorkGroupVO();
        workGroupVO.setName("我是A班");
        workGroupService.add(workGroupVO);
    }

    @Test
    public void findAll() {
        PageVO pageVO = new PageVO();
        pageVO.setPage(0);
        Page<WorkGroup> page = workGroupService.findAll(pageVO);
        System.out.println("size : " + page.getContent().size());
        page.getContent().forEach(workGroup ->
                System.out.println("name: " + workGroup.getName())
        );
    }

    @Test
    public void findAllTest() {
        List<WorkGroup> workGroups = workGroupRepo.findAllByIsDeleted(DeleteStatus.enable.ordinal());

        workGroups.forEach(workGroup -> System.out.println(workGroup.getName()));
    }
}
