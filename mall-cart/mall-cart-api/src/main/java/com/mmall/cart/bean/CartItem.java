package com.mmall.cart.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("cart_item")
public class CartItem implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 购物车ID
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String cartId;
	/**
	 * 商品ID
	 */
	private String goodId;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 购买数量
	 */
	private BigDecimal quantity;
	/**
	 * 商品主图
	 */
	private String goodPic;
	/**
	 * 商品名字
	 */
	private String goodName;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 修改时间
	 */
	private Date modifyDate;
	/**
	 * 删除状态
	 */
	private Integer deleteStatus;
	/**
	 * 是否被勾选
	 */
	private Integer checkStatus;

}
