package xyz.aiinirii.forum.portal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import xyz.aiinirii.forum.common.api.CommonPage;
import xyz.aiinirii.forum.common.api.CommonResult;
import xyz.aiinirii.forum.model.BbsReplyMulti;
import xyz.aiinirii.forum.model.UmsMember;
import xyz.aiinirii.forum.portal.domain.BbsMultiReplyParam;
import xyz.aiinirii.forum.portal.domain.BbsReplyParam;
import xyz.aiinirii.forum.portal.service.BbsReplyService;
import xyz.aiinirii.forum.portal.service.UmsMemberCacheService;
import xyz.aiinirii.forum.portal.service.UmsMemberService;

import java.security.Principal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 论坛回复有关操作
 *
 * @author AIINIRII
 */
@Api(value = "BbsReplyController", tags = {"论坛回复有关操作"})
@RestController
@RequestMapping("/reply")
public class BbsReplyController {

    BbsReplyService bbsReplyService;
    UmsMemberCacheService umsMemberCacheService;
    UmsMemberService umsMemberService;

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

    @ApiOperation("分页获取对应id的回复的回复")
    @GetMapping("/{id}/reply")
    public CommonResult<CommonPage<BbsReplyMulti>> getPostReply(@PathVariable Long id,
                                                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        List<BbsReplyMulti> bbsReplies = bbsReplyService.getReplyReplies(id, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(bbsReplies));
    }

    @ApiOperation("创建对应id的回复的回复")
    @PostMapping("/{id}/reply/create")
    public CommonResult<?> createMultiReply(@PathVariable Long id,
                                            @RequestBody BbsMultiReplyParam bbsMultiReplyParam,
                                            Principal principal) {
        Long userId = getUserId(principal);
        int postMultiReplyInsertLineNum = bbsReplyService.createPostMultiReply(id, userId, bbsMultiReplyParam);
        if (postMultiReplyInsertLineNum == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("数据插入失败");
        }
    }

    @ApiOperation("更新回复内容")
    @PostMapping("/update/{id}")
    public CommonResult<?> updateReply(@PathVariable Long id,
                                       @RequestBody BbsReplyParam bbsReplyParam,
                                       Principal principal) {
        Long userId = getUserId(principal);
        int updateLineNum = bbsReplyService.updateReply(id, userId, bbsReplyParam);
        if (updateLineNum == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("更新失败");
        }
    }

    @ApiOperation("删除回复内容")
    @PostMapping("/delete/{id}")
    public CommonResult<?> deleteReply(@PathVariable Long id,
                                       Principal principal) {
        Long userId = getUserId(principal);
        int deleteLineNum = bbsReplyService.deleteReply(id, userId);
        if (deleteLineNum == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("删除失败");
        }
    }

    @ApiOperation("删除回复的回复")
    @PostMapping("/multi/delete/{id}")
    public CommonResult<?> deleteMultiReply(@PathVariable Long id,
                                            Principal principal) {
        Long userId = getUserId(principal);
        int deleteLineNum = bbsReplyService.deleteMultiReply(id, userId);
        if (deleteLineNum == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("删除失败");
        }
    }

    @ApiOperation("更新回复的回复")
    @PostMapping("/multi/update/{id}")
    public CommonResult<?> updateMultiReply(@PathVariable Long id,
                                            @RequestBody BbsMultiReplyParam bbsMultiReplyParam,
                                            Principal principal) {
        Long userId = getUserId(principal);
        int updateLineNum = bbsReplyService.updateMultiReply(id, userId, bbsMultiReplyParam);
        if (updateLineNum == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("更新失败");
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