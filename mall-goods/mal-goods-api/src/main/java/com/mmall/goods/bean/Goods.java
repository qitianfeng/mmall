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
 * @Description:Goods构建
 *****/
@ApiModel(description = "Goods",value = "Goods")
@Table(name="goods")
public class Goods implements Serializable{

	@ApiModelProperty(value = "商品ID",required = false)
	@Id
	@Column(name = "goods_id")
	private Long goodsId;//商品ID

	@ApiModelProperty(value = "商品名字",required = false)
	@Column(name = "goods_name")
	private String goodsName;//商品名字

	@ApiModelProperty(value = "商品价格",required = false)
	@Column(name = "goods_price")
	private String goodsPrice;//商品价格

	@ApiModelProperty(value = "商品描述",required = false)
	@Column(name = "goods_desc")
	private String goodsDesc;//商品描述

	@ApiModelProperty(value = "商品添加时间",required = false)
	@Column(name = "good_add_time")
	private Date goodAddTime;//商品添加时间

	@ApiModelProperty(value = "商品图片",required = false)
	@Column(name = "sgood_image")
	private String sgoodImage;//商品图片

	@ApiModelProperty(value = "商品库存",required = false)
	@Column(name = "good_num")
	private Integer goodNum;//商品库存

	@ApiModelProperty(value = "1 表示商品上架，0 表示商品下架",required = false)
	@Column(name = "good_status")
	private Integer goodStatus;//1 表示商品上架，0 表示商品下架

	@ApiModelProperty(value = "商品作者",required = false)
	@Column(name = "goods_author")
	private String goodsAuthor;//商品作者

	@ApiModelProperty(value = "商品图片",required = false)
	@Column(name = "goods_image")
	private String goodsImage;//商品图片

	@ApiModelProperty(value = "添加时间",required = false)
	@Column(name = "goods_add_time")
	private Date goodsAddTime;//添加时间



	//get方法
	public Long getGoodsId() {
		return goodsId;
	}

	//set方法
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	//get方法
	public String getGoodsName() {
		return goodsName;
	}

	//set方法
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	//get方法
	public String getGoodsPrice() {
		return goodsPrice;
	}

	//set方法
	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	//get方法
	public String getGoodsDesc() {
		return goodsDesc;
	}

	//set方法
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	//get方法
	public Date getGoodAddTime() {
		return goodAddTime;
	}

	//set方法
	public void setGoodAddTime(Date goodAddTime) {
		this.goodAddTime = goodAddTime;
	}
	//get方法
	public String getSgoodImage() {
		return sgoodImage;
	}

	//set方法
	public void setSgoodImage(String sgoodImage) {
		this.sgoodImage = sgoodImage;
	}
	//get方法
	public Integer getGoodNum() {
		return goodNum;
	}

	//set方法
	public void setGoodNum(Integer goodNum) {
		this.goodNum = goodNum;
	}
	//get方法
	public Integer getGoodStatus() {
		return goodStatus;
	}

	//set方法
	public void setGoodStatus(Integer goodStatus) {
		this.goodStatus = goodStatus;
	}
	//get方法
	public String getGoodsAuthor() {
		return goodsAuthor;
	}

	//set方法
	public void setGoodsAuthor(String goodsAuthor) {
		this.goodsAuthor = goodsAuthor;
	}
	//get方法
	public String getGoodsImage() {
		return goodsImage;
	}

	//set方法
	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}
	//get方法
	public Date getGoodsAddTime() {
		return goodsAddTime;
	}

	//set方法
	public void setGoodsAddTime(Date goodsAddTime) {
		this.goodsAddTime = goodsAddTime;
	}


}
