package com.chz.jenkins.netty;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/12/29/16:38
 * @Description:
 */

public class MessageHandler {
    private  String message;
    private final StringBuilder sb=new StringBuilder();

    public String getMessage() {
        return message;
    }

    public String setMessage(String message) {
        return this.message=message;
    }

    public void messageHandler() throws IOException {
        boolean begin=true;


            if (message.startsWith("@{")&&message.endsWith("}@")) {
                String replace = message.replace("@", "");
                File file = new File("C:\\Users\\user\\Desktop\\json1.txt");
                if(!file.exists()){
                    file.createNewFile();
                }
                FileWriter fileWriter=null;
                try {
                    fileWriter = new FileWriter(file);
                    fileWriter.write(replace);
                    fileWriter.flush();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }


    }
}
