package com.example.shardingtable.mapper;

import com.example.shardingtable.ShardingTableApplication;
import com.example.shardingtable.dal.dataobject.StudentDO;
import com.example.shardingtable.dal.mapper.StudentDOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuzz on 2018/06/05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ShardingTableApplication.class)
public class StudentMapperTest {

    private Logger log = LoggerFactory.getLogger(StudentMapperTest.class);

    private static final int STUDENT_COUNT = 1000;

    @Autowired
    private StudentDOMapper studentDOMapper;

    @Test
    public void testAddStudent() {
        long time = System.currentTimeMillis();
        for (int i = 0; i < STUDENT_COUNT; i++) {
            StudentDO studentDO = new StudentDO();
            studentDO.setName("name" + i);
            Long result = studentDOMapper.insert(studentDO);
            assetResult(result);
        }
        log.info("插入{}条学员记录，耗时{}", STUDENT_COUNT, System.currentTimeMillis() - time);
    }

    private void assetResult(Long result) {
        assert result != null && result == 1;
    }
}
