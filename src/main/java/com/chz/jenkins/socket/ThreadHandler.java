package com.chz.jenkins.socket;


import lombok.extern.slf4j.Slf4j;


import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/11/25/9:36
 * @Description: 处理wrf
 */
@Slf4j
public class ThreadHandler implements Runnable {


    private final String ip;
    private final Integer port;
    private Socket socket;
    private DataInputStream dataInputStream;


    public ThreadHandler(String ip,Integer port) {

        this.ip = ip;
        this.port = port;

    }

    @Override
    public void run() {

        while (true) {
            try {
                socket = new Socket(ip, port);
                dataInputStream = new DataInputStream(socket.getInputStream());
                log.info("socket连接成功..........");
                byte[] bytes = new byte[10240];
                int len = 0;
                String allMsg = "";
                while ((len = dataInputStream.read(bytes)) != -1) {
                    String message = new String(bytes, 0, len);
                    allMsg = allMsg + message;
                    System.out.println(allMsg);
                }
                System.out.println(allMsg);
            } catch (IOException e) {
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
