package com.zhangzhihao.SpringMVCSeedProject.Test.ControllerTest;


import com.zhangzhihao.SpringMVCSeedProject.Controller.MustLoginController;
import com.zhangzhihao.SpringMVCSeedProject.Test.TestUtils.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class MustLoginControllerTest extends BaseTest {

    @Autowired
    private MustLoginController mustLoginController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(mustLoginController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void testLoginSuccess() throws Exception {
        mockMvc.perform(get("/MustLogin"))
                .andExpect(status().is(200))
                .andExpect(view().name("/MustLogin/MustLogin"));
    }
}
