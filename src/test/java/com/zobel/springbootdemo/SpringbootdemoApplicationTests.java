package com.zobel.springbootdemo;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringbootdemoApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void contextLoads() {
        System.out.println("test hello");
        TestCase.assertEquals(1,1);
    }

    @Before
    public void testBefore(){
        System.out.println("before");
    }

    @After
    public void testAfter(){
        System.out.println("after");
    }

    @Test
    public void apiTest() throws Exception {
        MvcResult mvcResule = mockMvc.perform(MockMvcRequestBuilders.get("/test/home"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        int status = mvcResule.getResponse().getStatus();
        System.out.println(status);
    }


}
