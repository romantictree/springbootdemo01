package com.example.springbootdemo01;

import com.example.springbootdemo01.common.QiniuStorage;
import com.example.springbootdemo01.utils.CommonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Springbootdemo01ApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testQiniu(){
        byte[] buff = CommonUtil.getFileBytes(new File("D://2.jpg"));
        String key = QiniuStorage.uploadImage(buff);
    }

}
