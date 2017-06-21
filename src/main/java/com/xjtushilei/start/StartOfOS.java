package com.xjtushilei.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;


/**
 * 服务启动执行的程序
 * 优先级：1>2
 */

@Component
@Order(value = 1)
public class StartOfOS implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(String... args) throws Exception {
        logger.info(">>>>>>>>>>>>>>>服务启动执行，开始设置phantomjs操作<<<<<<<<<<<<<");
        // 指定PhantomJS 可执行程序的位置
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            logger.info("windwos:  phantomjs.binary.path:phantomjs/phantomjs.exe");
            System.setProperty("phantomjs.binary.path", StartOfOS.class.getClassLoader().getResource("").getPath() + "phantomjs/phantomjs.exe");
        } else  if (System.getProperty("os.name").toLowerCase().startsWith("linux")) {
            logger.info("Linux:  phantomjs.binary.path:phantomjs/phantomjs");
            System.setProperty("phantomjs.binary.path", StartOfOS.class.getClassLoader().getResource("").getPath() + "phantomjs/phantomjs");
        }
        File phantomjsFile = new File(StartOfOS.class.getClassLoader().getResource("").getPath() + "phantomjs/phantomjs");
        logger.info("是否可执行：" + phantomjsFile.canExecute());
        if (!phantomjsFile.canExecute()) {
            phantomjsFile.setExecutable(true);
            if (!phantomjsFile.canExecute()) {
                logger.error("爬虫软件无权限运行！");
            }
        }
        logger.info("是否可执行2：" + phantomjsFile.canExecute());

    }


}

