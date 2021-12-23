package com.chz.jenkins.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/12/22/16:33
 * @Description:
 */
@Slf4j
@Component
public class Socket implements ApplicationRunner {

    private static final String IP="localhost";

    private static final Integer PORT =5080;




    @Override
    public void run(ApplicationArguments args) throws InterruptedException {
//        ThreadPoolUtil.getPool().execute(new ThreadHandlerServer());
//        TimeUnit.SECONDS.sleep(5);
        ThreadPoolUtil.getPool().execute(new ThreadHandler(IP,PORT));
    }
}
