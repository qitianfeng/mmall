package com.mmall.content.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mmall.content.ContentService;
import com.mmall.content.entity.Content;
import com.mmall.content.mapper.ContentMapper;
import com.mmall.utils.PageUtils;
import com.mmall.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/****
 * @Author:qitianfeng
 * @Description:Content业务层接口实现类
 *****/
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

    @Autowired
    ContentMapper contentMapper;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Content> page = this.page(
                new Query<Content>().getPage(params),
                new QueryWrapper<Content>()
        );

        return new PageUtils(page);
    }

    @Override
    public void updateContentByGoodsId(Long goodsId, String goodsImage) {
        LambdaUpdateWrapper<Content> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Content::getGoodId,goodsId);
        Content content = new Content();
        content.setPic(goodsImage);
        contentMapper.update(content,wrapper);

    }

    /**
     * 广告上线
     *
     * @param contentIds
     */
    @Override
    public void changeActive(Long[] contentIds) {

        List<Long> longs = Arrays.asList(contentIds);

        LambdaUpdateWrapper<Content> contentLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        contentLambdaUpdateWrapper.in(Content::getId,longs);

        Content content = new Content();
        content.setStatus("1");
        contentMapper.update(content,contentLambdaUpdateWrapper);
    }

    /**
     * 广告下线
     *
     * @param contentIds
     */
    @Override
    public void changeInvalid(Long[] contentIds) {

        List<Long> longs = Arrays.asList(contentIds);

        LambdaUpdateWrapper<Content> contentLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        contentLambdaUpdateWrapper.in(Content::getId,longs);

        Content content = new Content();
        content.setStatus("0");
        contentMapper.update(content,contentLambdaUpdateWrapper);
    }

    /**
     * 根据GoodsId删除对应的Content
     *
     * @param goodsIds
     */
    @Override
    public int deleteByGoodsId(Long[] goodsIds) {

        LambdaQueryWrapper<Content> wrapper = new LambdaQueryWrapper<>();

        wrapper.in(Content::getGoodId,Arrays.asList(goodsIds));

        int delete = contentMapper.delete(wrapper);
        return delete;


    }

    @Override
    public PageUtils queryByParam(Map<String, Object> params) {
        LambdaQueryWrapper<Content> wrapper = new LambdaQueryWrapper<>();
        String title = (String) params.get("title");
        String pageIndex = (String) params.get("page");
        String limit = (String) params.get("limit");
        wrapper.like(Content::getTitle,title);
        Page<Content> goodsPage = new Page<>();
        goodsPage.setSize(Integer.parseInt(limit));
        goodsPage.setCurrent(Integer.parseInt(pageIndex));
        IPage<Content>page = contentMapper.selectPage(goodsPage,wrapper);
        List<Content> goodsList = page.getRecords();
        PageUtils pageUtils = new PageUtils();
        pageUtils.setList(goodsList);
        pageUtils.setCurrPage(Integer.parseInt(pageIndex));
        pageUtils.setPageSize(Integer.parseInt(limit));
        pageUtils.setTotalCount((int) page.getTotal());
        return pageUtils;
    }

}
