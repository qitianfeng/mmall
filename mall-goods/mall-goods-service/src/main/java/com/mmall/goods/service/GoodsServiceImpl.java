package com.mmall.goods.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mmall.goods.GoodsService;
import com.mmall.goods.entity.Goods;
import com.mmall.goods.mapper.GoodsMapper;
import com.mmall.goods.vo.GoodsVo;
import com.mmall.utils.PageUtils;
import com.mmall.utils.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/****
 * @Author:qitianfeng
 * @Description:Goods业务层接口实现类
 *****/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String pageIndex = (String) params.get("page");
        String limit = (String) params.get("limit");
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();

        IPage<Goods> page = this.page(
                new Query<Goods>().getPage(params),
                new QueryWrapper<Goods>()
        );

        return new PageUtils(page);
    }


    /**
     * 根据查询参数指定查询
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryByParam(Map<String, Object> params) {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        String goodsName = (String) params.get("goodsName");
        String pageIndex = (String) params.get("page");
        String limit = (String) params.get("limit");

        wrapper.like(Goods::getGoodsName,goodsName);

        Page<Goods> goodsPage = new Page<>();
        goodsPage.setSize(Integer.parseInt(limit));
        goodsPage.setCurrent(Integer.parseInt(pageIndex));
        IPage<Goods>page = goodsMapper.selectPage(goodsPage,wrapper);
        List<Goods> goodsList = page.getRecords();
        PageUtils pageUtils = new PageUtils();
        pageUtils.setList(goodsList);
        pageUtils.setCurrPage(Integer.parseInt(pageIndex));
        pageUtils.setPageSize(Integer.parseInt(limit));
        pageUtils.setTotalCount((int) page.getTotal());
        return pageUtils;
    }

    /**
     * 分页查询首页商品
     *  @param goodsPage
     * @param goodsVo
     */
    @Override
    public IPage<Goods> selectPageByParam(Page<Goods> goodsPage, GoodsVo goodsVo) {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(goodsVo.getGoodsName())) {
            wrapper.eq(Goods::getGoodsName,goodsVo.getGoodsName());
        }

        if(!StringUtils.isEmpty(goodsVo.getGoodsAuthor())) {
            wrapper.eq(Goods::getGoodsAuthor,goodsVo.getGoodsAuthor());
        }

        if (goodsVo.getGoodsPrice()!=null) {
            wrapper.orderByDesc(Goods::getGoodsPrice);
        }
        IPage<Goods> goodsIPage = baseMapper.selectPage((IPage<Goods>) goodsPage, wrapper);
        return goodsIPage;

    }

    /**
     * 批量上架
     *
     * @param ids
     * @return
     */
    @Override
    public int putMany(Long[] ids) {
        LambdaUpdateWrapper<Goods> wrapper = new LambdaUpdateWrapper<>();

        List<Long> longs = Arrays.asList(ids);

        wrapper.in(Goods::getGoodsId,longs);
        Goods goods = new Goods();
        goods.setGoodsStatus(1);
        //批量修改
//        Integer integer = goodsMapper.updatGoodsxiaJiajiaStatus(id);
        int i = goodsMapper.update(goods, wrapper);
        return  i;
    }



    /**
     * 商品批量下架
     *
     * @param id
     */
    @Override
    public int pullMany(Long[] id) {

        LambdaUpdateWrapper<Goods> wrapper = new LambdaUpdateWrapper<>();

        List<Long> longs = Arrays.asList(id);

        wrapper.in(Goods::getGoodsId,longs);
        Goods goods = new Goods();
        goods.setGoodsStatus(0);
        //批量修改
//        Integer integer = goodsMapper.updatGoodsxiaJiajiaStatus(id);
        int i = goodsMapper.update(goods, wrapper);
        return  i;

    }



}
