package com.zhangzhihao.SpringMVCSeedProject.Controller;

import com.zhangzhihao.SpringMVCSeedProject.Service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Log")
public class LogController {
    @Autowired
    private LogService logService;

    /**
     * 日志统计界面
     *
     * @return 日志统计界面
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String LogPage() {
        return "Log/Log";
    }
}
