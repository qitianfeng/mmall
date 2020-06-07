package com.mmall.content.controller;
import com.mmall.content.ContentService;
import com.mmall.content.entity.Content;
import com.mmall.utils.PageUtils;
import com.mmall.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.AbstractController;

import java.util.Arrays;
import java.util.Map;

/****
 * @Author:qitianfeng
 * @Description:
 *****/
@Api(value = "ContentController",description = "ContentController前台广告轮播模块",tags = "ContentController")
@RestController
@RequestMapping("content")
public class ContentController{
    @Autowired
    private ContentService contentService;


    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("content:content:list")
    public R list(@RequestParam Map<String, Object> params){

        PageUtils page = contentService.queryPage(params);

        return R.ok().put("page", page);
    }
    /**
     * 列表
     */
    @RequestMapping("/param")
//    @RequiresPermissions("content:content:list")
    public R queryByParam(@RequestParam Map<String, Object> params){

        PageUtils page = contentService.queryByParam(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("content:content:info")
    public R info(@PathVariable("id") Long id){
        Content content = contentService.getById(id);

        return R.ok().put("content", content);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("content:content:save")
    public R save(@RequestBody Content content){
        contentService.save(content);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("content:content:update")
    public R update(@RequestBody Content content){
        contentService.updateById(content);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("content:content:delete")
    public R delete(@RequestBody Long[] ids){
        contentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 广告上线或批量上线
     * @param contentIds
     * @return
     */
    @ApiOperation(value = "广告上线",notes = "广告上线",tags = {"ContentController"})
    @PostMapping("changeActive")
    public R changeActive(@RequestBody Long[] contentIds){
        contentService.changeActive(contentIds);
        return R.ok();
    }

    /**
     * 广告下线或批量下线
     * @param contentIds
     * @return
     */
    @ApiOperation(value = "广告下线",notes = "广告下线",tags = {"ContentController"})
    @PostMapping("changeInvalid")
    public R changeInvalid(@RequestBody Long[] contentIds){
        contentService.changeInvalid(contentIds);
        return R.ok();
    }

}

