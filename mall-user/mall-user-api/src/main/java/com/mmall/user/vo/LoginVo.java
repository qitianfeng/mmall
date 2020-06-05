package com.mmall.user.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-03 15:58:49
 */
@Data
public class LoginVo implements Serializable {
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户密码
	 */
	private String userPassword;
}
