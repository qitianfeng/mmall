package com.mmall.user.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import com.mmall.user.vo.RegisterVo;
import lombok.Data;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-03 15:58:49
 */
@Data
@TableName("user")
public class User extends RegisterVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId
	private String userId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户密码
	 */
	private String userPassword;
	/**
	 * 用户积分
	 */
	private String userScore;
	/**
	 * 用户手机
	 */
	private String userMobile;
	/**
	 * 用户注册时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date gmtCreate;

	/**
	 * 修改时间
	 *
	 */

	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date gmtModified;

}
