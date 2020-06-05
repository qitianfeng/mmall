package com.mmall.user.vo;

import lombok.Data;

import java.io.Serializable;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-03 15:58:49
 */
@Data
public class RegisterVo implements Serializable {
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户密码
	 */
	private String userPassword;
	/**
	 * 用户手机
	 */
	private String userMobile;
}
