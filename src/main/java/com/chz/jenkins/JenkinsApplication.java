package com.chz.jenkins;

import com.chz.jenkins.netty.TCPServer;
import com.chz.jenkins.socket.ThreadHandler;
import com.chz.jenkins.socket.ThreadHandlerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
@RestController
public class JenkinsApplication {

    @Value("${spring.application.name}")
    private String applicationName;

    public static void main(String[] args) {
        SpringApplication.run(JenkinsApplication.class, args);
        TCPServer tcpServer = new TCPServer();
        try {
            tcpServer.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/get")
    public String testJenkins() {
        return applicationName;
    }

    @GetMapping("/getIds")
    public String[] getIds(@RequestParam("ids") String[] ids) {
        return ids;
    }

    @GetMapping("/sendMessage")
    public void sendMessage() {
        try {
//            OutputStream outputStream = ThreadHandlerServer.socket.getOutputStream();
            OutputStream outputStream = ThreadHandler.socket.getOutputStream();
            String s = "";
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("shabi");

            s = stringBuilder.toString();
            outputStream.write(s.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
