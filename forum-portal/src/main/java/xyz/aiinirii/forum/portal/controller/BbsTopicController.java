package xyz.aiinirii.forum.portal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.aiinirii.forum.common.api.CommonResult;
import xyz.aiinirii.forum.model.BbsTopic;
import xyz.aiinirii.forum.portal.domain.BbsTopicParam;
import xyz.aiinirii.forum.portal.service.BbsTopicService;

import java.util.List;

/**
 * 关于标题的Controller类
 *
 * @author AIINIRII
 */
@RestController
@RequestMapping("/topic")
@Api(value = "BbsTopicController", tags = {"论坛话题有关操作"})
public class BbsTopicController {

    private BbsTopicService bbsTopicService;

    @Autowired
    public void setBbsTopicService(BbsTopicService bbsTopicService) {
        this.bbsTopicService = bbsTopicService;
    }

    @ApiOperation("创建一个话题")
    @PostMapping("/create")
    public CommonResult<?> createTopic(@RequestBody BbsTopicParam bbsTopicParam) {

        int insertLineNum = bbsTopicService.createTopic(bbsTopicParam);
        if (insertLineNum == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("无法创建该话题");
        }
    }

    @ApiOperation("列出所有话题")
    @GetMapping("")
    public CommonResult<List<BbsTopic>> getTopics() {

        List<BbsTopic> bbsTopics = bbsTopicService.getTopics();
        return CommonResult.success(bbsTopics);
    }

}