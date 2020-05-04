package com.mmall.order.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/****
 * @Author:qitianfeng
 * @Description:Order构建
 *****/
@ApiModel(description = "Order",value = "Order")
@Table(name="order")
public class Order implements Serializable{

	@ApiModelProperty(value = "订单ID",required = false)
	@Id
    @Column(name = "order_id")
	private Long orderId;//订单ID

	@ApiModelProperty(value = "用户ID",required = false)
    @Column(name = "user_id")
	private Long userId;//用户ID

	@ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "create_time")
	private Date createTime;//创建时间

	@ApiModelProperty(value = "用户名",required = false)
    @Column(name = "user_name")
	private String userName;//用户名

	@ApiModelProperty(value = "订单总金额",required = false)
    @Column(name = "total_amount")
	private String totalAmount;//订单总金额

	@ApiModelProperty(value = "应付金额（实际支付金额）",required = false)
    @Column(name = "pay_amount")
	private String payAmount;//应付金额（实际支付金额）

	@ApiModelProperty(value = "支付方式：0->未支付；1->支付宝；",required = false)
    @Column(name = "pay_type")
	private Integer payType;//支付方式：0->未支付；1->支付宝；

	@ApiModelProperty(value = "用户余额",required = false)
    @Column(name = "money_amount")
	private String moneyAmount;//用户余额

	@ApiModelProperty(value = "支付时间",required = false)
    @Column(name = "payment_time")
	private Date paymentTime;//支付时间



	//get方法
	public Long getOrderId() {
		return orderId;
	}

	//set方法
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	//get方法
	public Long getUserId() {
		return userId;
	}

	//set方法
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public String getUserName() {
		return userName;
	}

	//set方法
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getPayAmount() {
		return payAmount;
	}

	//set方法
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	//get方法
	public Integer getPayType() {
		return payType;
	}

	//set方法
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	//get方法
	public String getMoneyAmount() {
		return moneyAmount;
	}

	//set方法
	public void setMoneyAmount(String moneyAmount) {
		this.moneyAmount = moneyAmount;
	}
	//get方法
	public Date getPaymentTime() {
		return paymentTime;
	}

	//set方法
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}


}
