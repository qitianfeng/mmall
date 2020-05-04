package com.mmall.goods.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmall.goods.GoodsService;
import com.mmall.goods.bean.Goods;
import com.mmall.goods.mapper.GoodsMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:qitianfeng
 * @Description:Goods业务层接口实现类
 *****/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;


    /**
     * Goods条件+分页查询
     * @param goods 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Goods> findPage(Goods goods, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(goods);
        //执行搜索
        return new PageInfo<Goods>(goodsMapper.selectByExample(example));
    }

    /**
     * Goods分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Goods> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Goods>(goodsMapper.selectAll());
    }

    /**
     * Goods条件查询
     * @param goods
     * @return
     */
    @Override
    public List<Goods> findList(Goods goods){
        //构建查询条件
        Example example = createExample(goods);
        //根据构建的条件查询数据
        return goodsMapper.selectByExample(example);
    }


    /**
     * Goods构建查询对象
     * @param goods
     * @return
     */
    public Example createExample(Goods goods){
        Example example=new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();
        if(goods!=null){
            // 商品ID
            if(!StringUtils.isEmpty(goods.getGoodsId())){
                    criteria.andEqualTo("goodsId",goods.getGoodsId());
            }
            // 商品名字
            if(!StringUtils.isEmpty(goods.getGoodsName())){
                    criteria.andEqualTo("goodsName",goods.getGoodsName());
            }
            // 商品价格
            if(!StringUtils.isEmpty(goods.getGoodsPrice())){
                    criteria.andEqualTo("goodsPrice",goods.getGoodsPrice());
            }
            // 商品描述
            if(!StringUtils.isEmpty(goods.getGoodsDesc())){
                    criteria.andEqualTo("goodsDesc",goods.getGoodsDesc());
            }
            // 商品添加时间
            if(!StringUtils.isEmpty(goods.getGoodAddTime())){
                    criteria.andEqualTo("goodAddTime",goods.getGoodAddTime());
            }
            // 商品图片
            if(!StringUtils.isEmpty(goods.getSgoodImage())){
                    criteria.andEqualTo("sgoodImage",goods.getSgoodImage());
            }
            // 商品库存
            if(!StringUtils.isEmpty(goods.getGoodNum())){
                    criteria.andEqualTo("goodNum",goods.getGoodNum());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Long id){
        goodsMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Goods
     * @param goods
     */
    @Override
    public void update(Goods goods){
        goodsMapper.updateByPrimaryKey(goods);
    }

    /**
     * 增加Goods
     * @param goods
     */
    @Override
    public void add(Goods goods){
        goodsMapper.insert(goods);
    }

    /**
     * 根据ID查询Goods
     * @param id
     * @return
     */
    @Override
    public Goods findById(Long id){
        return  goodsMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Goods全部数据
     * @return
     */
    @Override
    public List<Goods> findAll() {
        return goodsMapper.selectAll();
    }
}
