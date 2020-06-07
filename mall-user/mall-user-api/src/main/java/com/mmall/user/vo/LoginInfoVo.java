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
public class LoginInfoVo implements Serializable {

	private String id;


	private String nickname;

	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 用户密码
	 */
	private String password;
}
