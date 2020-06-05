package com.mmall.goods.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mmall.goods.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods>  {

    public Integer updatGoodsShangjiaStatus(@Param("ids") Long[] ids);

    public Integer updatGoodsxiaJiajiaStatus(Long[] ids);

}
