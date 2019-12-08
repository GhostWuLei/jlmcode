/**  
 * @Project: jxoa
 * @Title: TestJbpm.java
 * @Package com.oa.test
 * @date 2013-7-2 下午5:07:42
 * @Copyright: 2013 
 */
package com.oa.test;


import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * 
 * 类名：TestJbpm
 * 功能：JBPM 服务
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-7-2 下午5:07:42
 *
 */

public class TestJbpm {

    //总结：isNotBlank()一定要里面有东西 null,""," ",返回的都是false
    @Test
    public void testDemo1(){
        String str = " ";
        System.out.println(StringUtils.isNotBlank(str));//true
    }

    //总结：isNotEmpty()是将空格也算在里面，null,"",返回的是fasle," "返回的是true
    @Test
    public void testDemo2(){
        String str = "";
        System.out.println(StringUtils.isNotEmpty(str));
    }

    @Test
    public void testDome3(){
        StringBuffer sb = new StringBuffer("from user");
        System.out.println("select count(*) "+sb.toString());
    }


	
}
