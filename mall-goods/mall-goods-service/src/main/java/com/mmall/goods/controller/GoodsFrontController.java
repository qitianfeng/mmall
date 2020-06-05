package com.mmall.goods.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mmall.goods.GoodsService;
import com.mmall.goods.entity.Goods;
import com.mmall.goods.vo.GoodsVo;
import com.mmall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoodsFrontController {

    @Autowired
    GoodsService goodsService;

    //查询所有商品
    @PostMapping("list/{page}/{limit}")
    public R getGoodInfo(@PathVariable String page, @PathVariable String limit, @RequestBody(required = false)GoodsVo goodsVo){

        Page<Goods> goodsPage = new Page<>();
        IPage<Goods> goodsIPage = goodsService.selectPageByParam(goodsPage, goodsVo);
        long total = goodsIPage.getTotal();
        List<Goods> records = goodsIPage.getRecords();
        return R.ok().put("total",total).put("rows",records);

    }

    //查询单个商品详情
    @GetMapping("getGoods/{goodsId}")
    public R getGoodsById(@PathVariable String goodsId) {
        Goods goods = goodsService.getById(goodsId);
        return R.ok().put("good",goods);
    }

}
