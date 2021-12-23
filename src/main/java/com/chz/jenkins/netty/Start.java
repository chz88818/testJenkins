package com.chz.jenkins.netty;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/12/23/10:23
 * @Description:
 */
public class Start {
    public static void main(String[] args) throws Exception {
        TCPServer tcpServer=new TCPServer();
        tcpServer.init();

    }
}
