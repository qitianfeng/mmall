package com.mmall.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/****
 * @Author:qitianfeng
 * @Description:Order构建
 *****/
@ApiModel(description = "Order",value = "Order")
@Data
@TableName("order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单ID
	 */
	@TableId
	private String orderId;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 订单总金额
	 */
	private BigDecimal totalAmount;
	/**
	 * 应付金额（实际支付金额）
	 */
	private BigDecimal payAmount;
	/**
	 * 支付方式：0->未支付；1->支付宝；
	 */
	private Integer payType;
	/**
	 * 用户余额
	 */
	private BigDecimal moneyAmount;
	/**
	 * 支付时间
	 */
	private Date paymentTime;
	/**
	 *
	 */
	private String goodsPic;

	/**
	 * 商品名字
	 */
	@TableField(exist = false)
	private String goodsName;

}


