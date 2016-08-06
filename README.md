# SpringMVCSeedProject [![Build Status](http://210.31.198.175:8080/jenkins/job/SpringMVCSeedProject/badge/icon)](https://github.com/izhangzhihao/SpringMVCSeedProject)

    Seed Project For SpringMVC

# 主要的后端架构：
    Spring + Spring MVC + JPA  + Hibernate + Apache Shiro
    
# 所需的环境和正确打开项目的姿势
    见根目录下'开发环境搭建及导入项目的方式.doc'
    导入前 视情况修改maven仓库地址(build.gradle)和数据库地址(/src/main/resources/db.properties)
    
# 配置文件说明
    '/src/main/resources/'路径下面的 
    MyBatis.xml generatorConfig.xml Hibernate.cfg.xml logback.xml 均未使用，只是作为配置文件模板，以备以后查询

# 不想写XML？试试SpringMVCWithJavaConfig [![Build Status](https://travis-ci.org/izhangzhihao/SpringMVCWithJavaConfig.svg?branch=master)](https://github.com/izhangzhihao/SpringMVCWithJavaConfig)
    没有Spring.xml,没有SpringMVC.xml,连web.xml也没有，通过类型安全的JavaConfig配置SpringMVC应用

