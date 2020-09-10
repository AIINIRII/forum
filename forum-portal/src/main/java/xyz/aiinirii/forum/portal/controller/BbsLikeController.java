package xyz.aiinirii.forum.portal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import xyz.aiinirii.forum.common.api.CommonResult;
import xyz.aiinirii.forum.model.UmsMember;
import xyz.aiinirii.forum.portal.service.BbsLikeService;
import xyz.aiinirii.forum.portal.service.UmsMemberCacheService;
import xyz.aiinirii.forum.portal.service.UmsMemberService;

import java.security.Principal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * like的Controller类
 *
 * @author AIINIRII
 */
@RestController
@RequestMapping("/like")
@Api(value = "BbsLikeController", tags = {"论坛喜欢有关操作"})
public class BbsLikeController {

    private BbsLikeService bbsLikeService;
    private UmsMemberService umsMemberService;
    private UmsMemberCacheService umsMemberCacheService;

    @Autowired
    public void setBbsLikeService(BbsLikeService bbsLikeService) {
        this.bbsLikeService = bbsLikeService;
    }

    @Autowired
    public void setUmsMemberService(UmsMemberService umsMemberService) {
        this.umsMemberService = umsMemberService;
    }

    @Autowired
    public void setUmsMemberCacheService(UmsMemberCacheService umsMemberCacheService) {
        this.umsMemberCacheService = umsMemberCacheService;
    }

    @ApiOperation("喜欢帖子")
    @PostMapping("/post/{id}")
    public CommonResult<?> likePost(@PathVariable Long id,
                                    Principal principal) {
        Long userId = getUserId(principal);
        return bbsLikeService.likePost(userId, id) == 1 ?
                CommonResult.success(null) : CommonResult.failed("喜欢帖子失败");
    }

    @ApiOperation("喜欢回复")
    @PostMapping("/reply/{id}")
    public CommonResult<?> likeReply(@PathVariable Long id,
                                     Principal principal) {
        Long userId = getUserId(principal);
        return bbsLikeService.likeReply(userId, id) == 1 ?
                CommonResult.success(null) : CommonResult.failed("喜欢回复失败");
    }

    @ApiOperation("喜欢回复的回复")
    @PostMapping("/replyMulti/{id}")
    public CommonResult<?> likeReplyMulti(@PathVariable Long id,
                                          Principal principal) {
        Long userId = getUserId(principal);
        return bbsLikeService.likeReplyMulti(userId, id) == 1 ?
                CommonResult.success(null) : CommonResult.failed("喜欢回复的回复失败");
    }

    @ApiOperation("取消喜欢帖子")
    @PostMapping("/post/delete/{id}")
    public CommonResult<?> cancelLikePost(@PathVariable Long id,
                                          Principal principal) {
        Long userId = getUserId(principal);
        return bbsLikeService.cancelLikePost(userId, id) == 1 ?
                CommonResult.success(null) : CommonResult.failed("取消喜欢帖子失败");
    }

    @ApiOperation("取消喜欢回复")
    @PostMapping("/reply/delete/{id}")
    public CommonResult<?> cancelLikeReply(@PathVariable Long id,
                                           Principal principal) {
        Long userId = getUserId(principal);
        return bbsLikeService.cancelLikeReply(userId, id) == 1 ?
                CommonResult.success(null) : CommonResult.failed("取消喜欢回复失败");
    }

    @ApiOperation("取消喜欢回复的回复")
    @PostMapping("/replyMulti/delete/{id}")
    public CommonResult<?> cancelLikeReplyMulti(@PathVariable Long id,
                                                Principal principal) {
        Long userId = getUserId(principal);
        return bbsLikeService.cancelLikeReplyMulti(userId, id) == 1?
                CommonResult.success(null) : CommonResult.failed("取消喜欢回复的回复失败");
    }

    @ApiOperation("列出喜欢帖子的人")
    @GetMapping("/post/{id}")
    public CommonResult<List<Long>> getUserLikePost(@PathVariable Long id) {
        return CommonResult.success(bbsLikeService.getPostLikesById(id));
    }

    @ApiOperation("列出喜欢回复的人")
    @GetMapping("/reply/{id}")
    public CommonResult<List<Long>> getUserLikeReply(@PathVariable Long id) {
        return CommonResult.success(bbsLikeService.getReplyLikesById(id));
    }

    @ApiOperation("列出喜欢回复的回复的人")
    @GetMapping("/replyMulti/{id}")
    public CommonResult<List<Long>> getUserLikeReplyMulti(@PathVariable Long id) {
        return CommonResult.success(bbsLikeService.getReplyMultiLikesById(id));
    }

    @ApiOperation("计数喜欢帖子")
    @GetMapping("/post/count/{id}")
    public CommonResult<Long> countUserLikePost(@PathVariable Long id) {
        return CommonResult.success(bbsLikeService.countPostLikesById(id));
    }

    @ApiOperation("计数喜欢回复")
    @GetMapping("/reply/count/{id}")
    public CommonResult<Long> countUserLikeReply(@PathVariable Long id) {
        return CommonResult.success(bbsLikeService.countReplyLikesById(id));
    }

    @ApiOperation("计数喜欢回复的回复")
    @GetMapping("/replyMulti/count/{id}")
    public CommonResult<Long> countUserLikeReplyMulti(@PathVariable Long id) {
        return CommonResult.success(bbsLikeService.countReplyMultiLikesById(id));
    }

    private Long getUserId(Principal principal) {
        String username = principal.getName();
        UmsMember umsMember = umsMemberCacheService.getMember(username);
        if (umsMember == null) {
            umsMember = umsMemberService.getMemberByUsername(username);
            if (umsMember != null) {
                umsMemberCacheService.setMember(umsMember);
            } else {
                throw new UsernameNotFoundException("无法找到相应的用户信息");
            }
        }
        return umsMember.getId();
    }
}