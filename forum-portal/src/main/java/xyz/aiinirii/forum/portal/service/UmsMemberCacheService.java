package xyz.aiinirii.forum.portal.service;

import xyz.aiinirii.forum.model.UmsMember;

/**
 * 会员信息缓存业务类
 *
 * @author AIINIRII
 */
public interface UmsMemberCacheService {

    /**
     * 删除用户缓存
     *
     * @param memberId 用户id
     */
    void delMember(Long memberId);

    /**
     * 从缓存获取用户
     *
     * @param username 用户名
     * @return 用户
     */
    UmsMember getMember(String username);

    /**
     * 设置会员用户缓存
     *
     * @param member 会员用户
     */
    void setMember(UmsMember member);

    /**
     * 设置验证码
     *
     * @param telephone 电话
     * @param authCode  验证码
     */
    void setAuthCode(String telephone, String authCode);

    /**
     * 获取验证码
     *
     * @param telephone 电话
     * @return 验证码
     */
    String getAuthCode(String telephone);
}