package com.mmall.goods.entity;

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
@ApiModel(description = "Goods",value = "Goods")
@Data
@TableName("goods")
public class Goods implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品ID
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private Long goodsId;
	/**
	 * 商品名字
	 */
	private String goodsName;
	/**
	 * 商品价格
	 */
	private BigDecimal goodsPrice;
	/**
	 * 商品描述
	 */
	private String goodsDesc;
	/**
	 * 商品添加时间
	 */

	private Date goodsAddTime;

	/**
	 * 1 表示商品上架，0 表示商品下架
	 */
	private Integer goodsStatus;
	/**
	 * 商品作者
	 */
	private String goodsAuthor;
	/**
	 * 商品图片
	 */
	private String goodsImage;

	/**
	 * 商品下载链接
	 */
	private  String goodsDownload;

}
