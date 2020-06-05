package com.mmall.goods.controller;

import com.mmall.content.ContentService;
import com.mmall.content.entity.Content;
import com.mmall.goods.GoodsService;
import com.mmall.goods.entity.FastDFSFile;
import com.mmall.goods.entity.Goods;
import com.mmall.goods.utils.FastDFSClient;
import com.mmall.utils.PageUtils;
import com.mmall.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.AbstractController;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/****
 * @Author:qitianfeng
 * @Description:
 *****/
@Api(value = "GoodsController",description = "GoodsController商品模块",tags = "GoodsController")
@RestController
//@RequestMapping("/goods/")
public class GoodsController{

    @Autowired
    private GoodsService goodsService;
    @Reference
    private ContentService contentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("goods:goods:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = goodsService.queryPage(params);
        return R.ok().put("page", page);
    }
    @GetMapping("param")
//    @RequiresPermissions("goods:goods:list")
    public R queryByParam(@RequestParam Map<String, Object> params){

        PageUtils page = goodsService.queryByParam(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("info/{goodsId}")
//    @RequiresPermissions("goods:goods:info")
    public R info(@PathVariable("goodsId") Long goodsId){
        Goods goods = goodsService.getById(goodsId);

        return R.ok().put("goods", goods);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions({"goods:goods:save,goods:goods:update,goods:goods:upload"})
    public R save(@RequestBody Goods goods){
        goods.setGoodsAddTime(new Date());
        //默认上架
        goods.setGoodsStatus(1);
        goodsService.save(goods);
        Content content = new Content();
        content.setGoodId(goods.getGoodsId());
        content.setPic(goods.getGoodsImage());
        content.setTitle(goods.getGoodsName());
        //默认下线
        content.setStatus("0");
        contentService.save(content);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("goods:goods:update")
    public R update(@RequestBody Goods goods){


        goods.setGoodsAddTime(new Date());;
        contentService.updateContentByGoodsId(goods.getGoodsId(),goods.getGoodsImage());
        goodsService.updateById(goods);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("goods:goods:delete")
    public R delete(@RequestBody Long[] goodsIds){
        contentService.deleteByGoodsId(goodsIds);
        goodsService.removeByIds(Arrays.asList(goodsIds));
        return R.ok();
    }

    /**
     * 商品上架或批量上架
     * @param goodsIds
     * @return
     */
    @ApiOperation(value = "Goods上架",notes = "Goods上架",tags = {"GoodsController"})
    @PostMapping("put")
    public R put(@RequestBody Long[] goodsIds){
        goodsService.putMany(goodsIds);
        return R.ok();
    }


    /**
     * 商品下架
     * @param id
     * @return
     */
    @ApiOperation(value = "Goods下架",notes = "Goods下架",tags = {"GoodsController"})
    @PostMapping("/pull")
    public R pull(@RequestBody Long[] id){
        Integer pull = goodsService.pullMany(id);
        return R.ok();
    }



    @ApiOperation("上传文件")
    @ApiImplicitParam(value = "上传的文件", name = "file", required = true, dataType = "MultipartFile", paramType = "query")
//    @RequiresPermissions("goods:goods:upload")
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {

        try {
            FastDFSFile fastDFSFile = new FastDFSFile(
                    file.getOriginalFilename(),//原来文件名
                    file.getBytes(),//文件的字节数组
                    org.springframework.util.StringUtils.getFilenameExtension(file.getOriginalFilename()));
            String[] upload = FastDFSClient.upload(fastDFSFile);
            //  upload[0] group1
            //  upload[1] M00/00/00/wKjThF1aW9CAOUJGAAClQrJOYvs424.jpg
            //3. 拼接图片的全路径返回
            System.out.println(FastDFSClient.getTrackerUrl() + "/" + upload[0] + "/" + upload[1]);
            return FastDFSClient.getTrackerUrl() + "/" + upload[0] + "/" + upload[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }






}
