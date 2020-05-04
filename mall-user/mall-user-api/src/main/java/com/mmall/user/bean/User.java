package com.mmall.user.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/****
 * @Author:qitianfeng
 * @Description:User构建
 *****/
@ApiModel(description = "User",value = "User")
@Table(name="user")
public class User implements Serializable{

	@ApiModelProperty(value = "用户ID",required = false)
	@Id
    @Column(name = "user_id")
	private Long userId;//用户ID

	@ApiModelProperty(value = "用户名",required = false)
    @Column(name = "user_name")
	private String userName;//用户名

	@ApiModelProperty(value = "用户密码",required = false)
    @Column(name = "user_password")
	private String userPassword;//用户密码

	@ApiModelProperty(value = "用户积分",required = false)
    @Column(name = "user_score")
	private String userScore;//用户积分

	@ApiModelProperty(value = "用户手机",required = false)
    @Column(name = "user_mobile")
	private String userMobile;//用户手机

	@ApiModelProperty(value = "用户余额",required = false)
    @Column(name = "user_money")
	private String userMoney;//用户余额

	@ApiModelProperty(value = "用户注册时间",required = false)
    @Column(name = "user_reg_time")
	private Date userRegTime;//用户注册时间

	@ApiModelProperty(value = "盐",required = false)
    @Column(name = "salt")
	private String salt;//盐



	//get方法
	public Long getUserId() {
		return userId;
	}

	//set方法
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public String getUserPassword() {
		return userPassword;
	}

	//set方法
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	//get方法
	public String getUserScore() {
		return userScore;
	}

	//set方法
	public void setUserScore(String userScore) {
		this.userScore = userScore;
	}
	//get方法
	public String getUserMobile() {
		return userMobile;
	}

	//set方法
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	//get方法
	public String getUserMoney() {
		return userMoney;
	}

	//set方法
	public void setUserMoney(String userMoney) {
		this.userMoney = userMoney;
	}
	//get方法
	public Date getUserRegTime() {
		return userRegTime;
	}

	//set方法
	public void setUserRegTime(Date userRegTime) {
		this.userRegTime = userRegTime;
	}
	//get方法
	public String getSalt() {
		return salt;
	}

	//set方法
	public void setSalt(String salt) {
		this.salt = salt;
	}


}
