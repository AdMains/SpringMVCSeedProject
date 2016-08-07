package com.zhangzhihao.SpringMVCSeedProject.Test.ControllerTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangzhihao.SpringMVCSeedProject.Controller.LogController;
import com.zhangzhihao.SpringMVCSeedProject.Test.TestUtils.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class LogControllerTest extends BaseTest {
    @Autowired
    private LogController logController;

    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(logController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void logPageTest() throws Exception {
        mockMvc.perform(get("/Log/"))
                .andExpect(status().is(200))
                .andExpect(view().name("Log/Log"));
    }

    @Test
    public void getLogInfoTest() throws Exception {
        String contentAsString = mockMvc.perform(get("/Log/getLogInfo"))
                .andExpect(status().is(200))
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(contentAsString);
        //将string转为json
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.readValue(contentAsString, Map.class);
        System.out.println(map);
        //断言相等
        assertEquals(Integer.parseInt(map.get("LogUtilsCount").toString())
                        + Integer.parseInt(map.get("LogAspectCount").toString())
                        + Integer.parseInt(map.get("otherCount").toString())
                , map.get("totalCount"));
    }

    @Test
    public void getLogByPageTest() throws Exception {
        String contentAsString = mockMvc
                .perform(get("/Log/getLogByPage")
                        .param("pageNumber", "2")
                        .param("pageSize", "10")
                )
                .andExpect(status().is(200))
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(contentAsString);
        //将string转为json
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.readValue(contentAsString, Map.class);
        System.out.println(map);
        int previousPage = Integer.parseInt(map.get("previousPage").toString());
        int currentPage = Integer.parseInt(map.get("currentPage").toString());
        int nextPage = Integer.parseInt(map.get("nextPage").toString());
        int pageSize = Integer.parseInt(map.get("pageSize").toString());
        int totalCount = Integer.parseInt(map.get("totalCount").toString());
        int pageCount = Integer.parseInt(map.get("pageCount").toString());
        assertTrue(previousPage <= currentPage);
        assertTrue(currentPage <= nextPage);
        assertTrue(pageSize * pageCount >= totalCount);

        int i = totalCount % pageSize;
        if (i == 0) {
            assertEquals(totalCount / pageSize , pageCount);
        } else {
            assertEquals(totalCount / pageSize + 1, pageCount);
        }
    }
}
