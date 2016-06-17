package com.zhangzhihao.SpringMVCSeedProject.Test.ControllerTest;


import com.zhangzhihao.SpringMVCSeedProject.Controller.MustLoginController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class MustLoginControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(new MustLoginController()).setViewResolvers(viewResolver).build();
    }

    @Test
    public void testLoginSuccess() throws Exception{
        mockMvc.perform(get("/MustLogin"))
                .andExpect(status().is(200))
                .andExpect(view().name("/MustLogin/MustLogin"));
    }
}
