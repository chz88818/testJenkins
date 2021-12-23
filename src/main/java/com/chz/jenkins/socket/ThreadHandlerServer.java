package com.chz.jenkins.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/12/22/22:34
 * @Description:
 */
@Slf4j
public class ThreadHandlerServer implements Runnable{

private ServerSocket serverSocket;
public static  Socket socket;




    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(8001);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
//                serverSocket = new ServerSocket(8001);

                System.out.println("----------------服务端执行，開始监听请求----------------");
                socket = serverSocket.accept();//開始监听
                OutputStream outputStream = socket.getOutputStream();
                for (int i=0;i<5;i++) {
                    outputStream.write("客户端你好，我是服务端".getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                }

            }catch (Exception e){
                log.info("socket连接异常,5秒后重连..........");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e1) {
                    log.info("线程休眠异常..........");
                }
            }
        }

    }
}
