package com.mmall.goods;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mmall.goods.entity.Goods;
import com.mmall.goods.vo.GoodsVo;
import com.mmall.utils.PageUtils;

import java.util.Map;

/****
 * @Author:qitianfeng
 * @Description:Goods业务层接口
 *****/
public interface GoodsService extends IService<Goods> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 批量上架
     * @param ids
     * @return
     */
    int putMany(Long[] ids);

    /**
     * 商品批量下架
     * @param id
     */
    int pullMany(Long[] id);

    /**
     * 根据查询参数指定查询
     * @param params
     * @return
     */
    PageUtils queryByParam(Map<String, Object> params);

    /**
     * 分页查询首页商品
     * @param goodsPage
     * @param goodsVo
     */
    IPage<Goods> selectPageByParam(Page<Goods> goodsPage, GoodsVo goodsVo);
}
