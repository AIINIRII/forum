package xyz.aiinirii.forum.portal.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Mybatis配置
 *
 * @author AIINIRII
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"xyz.aiinirii.forum.mapper", "xyz.aiinirii.forum.portal.dao"})
public class MybatisConfig {
}