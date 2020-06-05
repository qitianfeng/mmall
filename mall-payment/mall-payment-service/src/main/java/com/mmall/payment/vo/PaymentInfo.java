package com.mmall.payment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "PaymentInfo",value = "PaymentInfo")
public class PaymentInfo implements Serializable{

	@ApiModelProperty(value = "",required = false)
	private Long id;//

	@ApiModelProperty(value = "对外业务编号(订单id)",required = false)
	private String orderId;//对外业务编号(订单id)

	@ApiModelProperty(value = "支付宝交易编号",required = false)
	private String alipayTradeNo;//支付宝交易编号

	@ApiModelProperty(value = "支付金额",required = false)
	private String totalAmount;//支付金额

	@ApiModelProperty(value = "支付状态",required = false)
	private String paymentStatus;//支付状态

	@ApiModelProperty(value = "创建时间",required = false)
	private Date createTime;//创建时间

	@ApiModelProperty(value = "确认时间",required = false)
	private Date confirmTime;//确认时间

	@ApiModelProperty(value = "回调信息",required = false)
	private String callbackContent;//回调信息

	@ApiModelProperty(value = "",required = false)
	private Date callbackTime;//



}
