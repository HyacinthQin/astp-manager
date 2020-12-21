package com.qa.test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Demo {

    @Test
    public void testCase1(){
        // 添加日志
        Reporter.log("判断1是否等于1");
        Assert.assertEquals(1, 1);
    }

    @Test
    public void testCase2(){
        // 添加日志
        Reporter.log("判断1是否等于2");
        Assert.assertEquals(1, 2);
    }

    @Test
    public void logDemo(){
        // 抛出个异常看看啥样式
        throw new RuntimeException("超时啦，超时啦！！！");
    }

    @Test
    public void testCase3(){
        // 添加日志
        Reporter.log("判断name是否等于name");
        Assert.assertEquals("name", "name");
    }
}
