package com.zhangzhihao.SpringMVCSeedProject.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

@ControllerAdvice
public class HandlerExceptionController {
    private Logger logger = LoggerFactory.getLogger(HandlerExceptionController.class);

    @ExceptionHandler({Exception.class})
    public ModelAndView HandlerMethod(Exception ex) {
        logger.error(ex.toString());
        ModelAndView modelAndView = new ModelAndView("/Error/Exception");
        modelAndView.addObject("MSG", ex.toString());
        Writer writer = new StringWriter();
        ex.printStackTrace(new PrintWriter(writer));
        modelAndView.addObject("detailed", writer.toString());
        return modelAndView;
    }
}
