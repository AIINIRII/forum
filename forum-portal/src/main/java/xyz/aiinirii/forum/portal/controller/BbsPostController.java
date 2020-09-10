package xyz.aiinirii.forum.portal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import xyz.aiinirii.forum.common.api.CommonPage;
import xyz.aiinirii.forum.common.api.CommonResult;
import xyz.aiinirii.forum.model.BbsPost;
import xyz.aiinirii.forum.model.BbsReply;
import xyz.aiinirii.forum.model.UmsMember;
import xyz.aiinirii.forum.portal.domain.BbsPostParam;
import xyz.aiinirii.forum.portal.domain.BbsReplyParam;
import xyz.aiinirii.forum.portal.service.BbsPostService;
import xyz.aiinirii.forum.portal.service.BbsReplyService;
import xyz.aiinirii.forum.portal.service.UmsMemberCacheService;
import xyz.aiinirii.forum.portal.service.UmsMemberService;

import java.security.Principal;
import java.util.List;

/**
 * 关于帖子的Controller类
 *
 * @author AIINIRII
 */
@RestController
@RequestMapping("/post")
@Api(value = "BbsPostController", tags = {"论坛帖子有关操作"})
public class BbsPostController {
    public static final Logger LOGGER = LoggerFactory.getLogger(BbsPostController.class);
    private BbsPostService bbsPostService;
    private BbsReplyService bbsReplyService;
    private UmsMemberCacheService umsMemberCacheService;
    private UmsMemberService umsMemberService;

    @Autowired
    public void setBbsForumService(BbsPostService bbsPostService) {
        this.bbsPostService = bbsPostService;
    }

    @Autowired
    public void setBbsReplyService(BbsReplyService bbsReplyService) {
        this.bbsReplyService = bbsReplyService;
    }

    @Autowired
    public void setUmsMemberCacheService(UmsMemberCacheService umsMemberCacheService) {
        this.umsMemberCacheService = umsMemberCacheService;
    }

    @Autowired
    public void setUmsMemberService(UmsMemberService umsMemberService) {
        this.umsMemberService = umsMemberService;
    }

    @ApiOperation("分页查询当前用户帖子")
    @GetMapping("/mine")
    public CommonResult<CommonPage<BbsPost>> getMyPosts(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @ApiParam(hidden = true) Principal principal) {

        Long id = getUserId(principal);
        List<BbsPost> posts = bbsPostService.getPostsByUserId(id, pageSize, pageNum);
        if (posts == null) {
            return CommonResult.failed("无法根据当前用户名获取帖子");
        } else {
            return CommonResult.success(CommonPage.restPage(posts));
        }
    }

    @ApiOperation("分页查询当前所有帖子")
    @GetMapping()
    public CommonResult<CommonPage<BbsPost>> getPosts(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        List<BbsPost> posts = bbsPostService.getPosts(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(posts));
    }

    @ApiOperation("创建一个新帖子")
    @PostMapping("/create")
    public CommonResult<?> createPost(@RequestBody BbsPostParam bbsPostParam,
                                      @ApiParam(hidden = true) Principal principal) {
        Long id = getUserId(principal);
        int insertedPostNum = bbsPostService.createPost(id, bbsPostParam);
        if (insertedPostNum == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("创建失败");
        }
    }

    @ApiOperation("更新帖子")
    @PostMapping("/update/{id}")
    public CommonResult<?> updatePost(@PathVariable Long id,
                                      @RequestBody BbsPostParam bbsPostParam,
                                      Principal principal) {
        Long userId = getUserId(principal);
        int updatePost = bbsPostService.updatePost(id, userId, bbsPostParam);
        if (updatePost == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("无法更新此帖子");
        }
    }

    @ApiOperation("删除帖子")
    @PostMapping("/delete/{id}")
    public CommonResult<?> deletePost(@PathVariable Long id,
                                      Principal principal) {
        Long userId = getUserId(principal);
        int deletePost = bbsPostService.deletePost(id, userId);
        if (deletePost == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("删除帖子失败");
        }
    }

    @ApiOperation("分页获取对应id的帖子的回复")
    @GetMapping("/{id}/reply")
    public CommonResult<CommonPage<BbsReply>> getPostReply(@PathVariable Long id,
                                                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        List<BbsReply> bbsReplies = bbsReplyService.getPostReplies(id, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(bbsReplies));
    }

    @ApiOperation("在对应帖子下添加回复")
    @PostMapping("/{id}/reply/create")
    public CommonResult<?> createPostReply(@PathVariable Long id,
                                           @RequestBody BbsReplyParam bbsReplyParam,
                                           @ApiParam(hidden = true) Principal principal) {
        Long uid = getUserId(principal);
        int insertedLineNum = bbsReplyService.createPostReply(id, uid, bbsReplyParam);
        if (insertedLineNum == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("创建回复失败");
        }
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
