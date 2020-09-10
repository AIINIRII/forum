package xyz.aiinirii.forum.portal.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import xyz.aiinirii.forum.model.UmsMember;

import java.util.Collection;
import java.util.Collections;

/**
 * 前台用户信息模块
 *
 * @author AIINIRII
 */
public class PortalUserDetails implements UserDetails {

    private final UmsMember umsMember;

    public PortalUserDetails(UmsMember umsMember) {
        this.umsMember = umsMember;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority(/* for now, all users have customer role */"CUSTOMER")
        );
    }

    @Override
    public String getPassword() {
        return umsMember.getPassword();
    }

    @Override
    public String getUsername() {
        return umsMember.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return umsMember.getStatus().equals(1);
    }
}