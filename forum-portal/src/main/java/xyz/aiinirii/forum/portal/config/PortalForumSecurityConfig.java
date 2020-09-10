package xyz.aiinirii.forum.portal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import xyz.aiinirii.forum.portal.service.UmsMemberService;
import xyz.aiinirii.forum.security.config.SecurityConfig;

/**
 * Spring security的配置类
 *
 * @author AIINIRII
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class PortalForumSecurityConfig extends SecurityConfig {

    private UmsMemberService memberService;

    @Autowired
    public void setMemberService(UmsMemberService memberService) {
        this.memberService = memberService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> memberService.loadUserByUsername(username);
    }

}