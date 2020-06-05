package com.mmall.goods.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/****
 * @Author:qitianfeng
 * @Description:Goods构建
 *****/
@Data
public class GoodsVo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 商品名字
	 */
	private String goodsName;
	/**
	 * 商品价格
	 */
	private BigDecimal goodsPrice;


	/**
	 * 商品作者
	 */
	private String goodsAuthor;

}
