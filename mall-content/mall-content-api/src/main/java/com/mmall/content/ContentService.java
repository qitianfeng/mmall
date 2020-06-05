package com.mmall.content;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mmall.content.entity.Content;
import com.mmall.utils.PageUtils;

import java.util.Map;

/****
 * @Author:qitianfeng
 * @Description:Content业务层接口
 *****/
public interface ContentService extends IService<Content> {

    PageUtils queryPage(Map<String, Object> params);

    void updateContentByGoodsId(Long goodsId, String goodsImage);

    /**
     * 广告上线
     * @param contentIds
     */
    void changeActive(Long[] contentIds);

    /**
     * 广告下线
     * @param contentIds
     */
    void changeInvalid(Long[] contentIds);

    /**
     * 根据GoodsId删除对应的Content
     * @param goodsIds
     */
    int deleteByGoodsId(Long[] goodsIds);

    PageUtils queryByParam(Map<String, Object> params);
}
