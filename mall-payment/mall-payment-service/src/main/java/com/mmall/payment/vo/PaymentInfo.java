package com.mmall.payment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "PaymentInfo",value = "PaymentInfo")
@Table(name="payment_info")
public class PaymentInfo implements Serializable{

	@ApiModelProperty(value = "",required = false)
	@Id
    @Column(name = "id")
	private Long id;//

	@ApiModelProperty(value = "对外业务编号(订单id)",required = false)
    @Column(name = "order_id")
	private String orderId;//对外业务编号(订单id)

	@ApiModelProperty(value = "支付宝交易编号",required = false)
    @Column(name = "alipay_trade_no")
	private String alipayTradeNo;//支付宝交易编号

	@ApiModelProperty(value = "支付金额",required = false)
    @Column(name = "total_amount")
	private String totalAmount;//支付金额

	@ApiModelProperty(value = "支付状态",required = false)
    @Column(name = "payment_status")
	private String paymentStatus;//支付状态

	@ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "create_time")
	private Date createTime;//创建时间

	@ApiModelProperty(value = "确认时间",required = false)
    @Column(name = "confirm_time")
	private Date confirmTime;//确认时间

	@ApiModelProperty(value = "回调信息",required = false)
    @Column(name = "callback_content")
	private String callbackContent;//回调信息

	@ApiModelProperty(value = "",required = false)
    @Column(name = "callback_time")
	private Date callbackTime;//



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
	}
	//get方法
	public String getOrderId() {
		return orderId;
	}

	//set方法
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	//get方法
	public String getAlipayTradeNo() {
		return alipayTradeNo;
	}

	//set方法
	public void setAlipayTradeNo(String alipayTradeNo) {
		this.alipayTradeNo = alipayTradeNo;
	}
	//get方法
	public String getTotalAmount() {
		return totalAmount;
	}

	//set方法
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	//get方法
	public String getPaymentStatus() {
		return paymentStatus;
	}

	//set方法
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	//get方法
	public Date getCreateTime() {
		return createTime;
	}

	//set方法
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	//get方法
	public Date getConfirmTime() {
		return confirmTime;
	}

	//set方法
	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}
	//get方法
	public String getCallbackContent() {
		return callbackContent;
	}

	//set方法
	public void setCallbackContent(String callbackContent) {
		this.callbackContent = callbackContent;
	}
	//get方法
	public Date getCallbackTime() {
		return callbackTime;
	}

	//set方法
	public void setCallbackTime(Date callbackTime) {
		this.callbackTime = callbackTime;
	}


}
