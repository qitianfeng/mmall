package com.mmall.user.bean;

import com.baomidou.mybatisplus.annotation.*;

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
	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	/**
	 * 用户名
	 */
	private String nickname;
	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 用户手机
	 */
	private String mobile;
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
