package com.mmall.pojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:qitianfeng
 * @Description:CartItem构建
 *****/
@ApiModel(description = "CartItem",value = "CartItem")
@Table(name="cart_item")
public class CartItem implements Serializable{

	@ApiModelProperty(value = "购物车ID",required = false)
	@Id
	@Column(name = "cart_id")
	private Long cartId;//购物车ID

	@ApiModelProperty(value = "商品ID",required = false)
	@Column(name = "good_id")
	private Long goodId;//商品ID

	@ApiModelProperty(value = "用户ID",required = false)
	@Column(name = "user_id")
	private Long userId;//用户ID

	@ApiModelProperty(value = "购买数量",required = false)
	@Column(name = "quantity")
	private Long quantity;//购买数量

	@ApiModelProperty(value = "商品主图",required = false)
	@Column(name = "good_pic")
	private String goodPic;//商品主图

	@ApiModelProperty(value = "商品名字",required = false)
	@Column(name = "good_name")
	private String goodName;//商品名字

	@ApiModelProperty(value = "创建时间",required = false)
	@Column(name = "create_date")
	private Date createDate;//创建时间

	@ApiModelProperty(value = "修改时间",required = false)
	@Column(name = "modify_date")
	private Date modifyDate;//修改时间

	@ApiModelProperty(value = "删除状态",required = false)
	@Column(name = "delete_status")
	private Integer deleteStatus;//删除状态



	//get方法
	public Long getCartId() {
		return cartId;
	}

	//set方法
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	//get方法
	public Long getGoodId() {
		return goodId;
	}

	//set方法
	public void setGoodId(Long goodId) {
		this.goodId = goodId;
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
	public Long getQuantity() {
		return quantity;
	}

	//set方法
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	//get方法
	public String getGoodPic() {
		return goodPic;
	}

	//set方法
	public void setGoodPic(String goodPic) {
		this.goodPic = goodPic;
	}
	//get方法
	public String getGoodName() {
		return goodName;
	}

	//set方法
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	//get方法
	public Date getCreateDate() {
		return createDate;
	}

	//set方法
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	//get方法
	public Date getModifyDate() {
		return modifyDate;
	}

	//set方法
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	//get方法
	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	//set方法
	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}


}
