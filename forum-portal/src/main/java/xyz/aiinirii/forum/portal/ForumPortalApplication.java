package xyz.aiinirii.forum.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 前台程序入口
 *
 * @author AIINIRII
 */
@SpringBootApplication(scanBasePackages = "xyz.aiinirii.forum")
public class ForumPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumPortalApplication.class, args);
    }

}