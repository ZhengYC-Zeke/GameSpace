package com.zyc.test;

import com.zyc.GameBbsApplication;
import com.zyc.entity.Picture;
import com.zyc.service.PictureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.test
 * @Description: 类的描述
 * @author: Zeke
 * @date: 2020/2/1 12:15
 */
@SpringBootTest(classes = GameBbsApplication.class)
@RunWith(value = SpringRunner.class)
public class PictureTest {

    @Autowired
    private PictureService pictureService;

    @Test
    public void test01(){
        List<Picture> pictures = pictureService.selectFivePictureWhereStateIsNormal();
        for (Picture picture : pictures) {
            System.out.println(picture);
        }
    }

}
