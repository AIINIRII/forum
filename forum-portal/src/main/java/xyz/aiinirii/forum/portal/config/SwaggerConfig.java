package xyz.aiinirii.forum.portal.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import xyz.aiinirii.forum.common.config.BaseSwaggerConfig;
import xyz.aiinirii.forum.common.domain.SwaggerProperties;

/**
 * @author AIINIRII
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("xyz.aiinirii.forum.portal.controller")
                .title("forum前台系统")
                .description("forum前台系统相关API文档")
                .version("1.0")
                .contactName("AIINIRII")
                .contactEmail("aiinirii@163.com")
                .contactUrl("https://www.aiinirii.xyz")
                .enableSecurity(true)
                .build();
    }
}