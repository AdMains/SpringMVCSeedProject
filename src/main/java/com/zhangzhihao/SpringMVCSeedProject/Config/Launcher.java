/*
package com.zhangzhihao.SpringMVCSeedProject.Config;




public class Launcher {
    public static void main(String[] args) throws Exception
    {
        // The Jetty Server.
        Server server = new Server();

        // Common HTTP configuration.
        HttpConfiguration config = new HttpConfiguration();

        // HTTP/1.1 support.
        HttpConnectionFactory http1 = new HttpConnectionFactory(config);

        // HTTP/2 cleartext support.
        HTTP2CServerConnectionFactory http2c = new HTTP2CServerConnectionFactory(config);

        ServerConnector connector = new ServerConnector(server, http1, http2c);
        connector.setPort(8080);
        server.addConnector(connector);

        // Here configure contexts / servlets / etc.

        // 设置在JVM退出时关闭Jetty的钩子。
        server.setStopAtShutdown(true);

        WebAppContext webContext = new WebAppContext(DEFAULT_WEBAPP_PATH, contextPath);
        //webContext.setContextPath("/");
        webContext.setDescriptor("src/main/webapp/WEB-INF/web.xml");
        // 设置webapp的位置
        webContext.setResourceBase(DEFAULT_WEBAPP_PATH);
        webContext.setClassLoader(Thread.currentThread().getContextClassLoader());
        server.setHandler(webContext);

        server.start();
    }
}
*/
