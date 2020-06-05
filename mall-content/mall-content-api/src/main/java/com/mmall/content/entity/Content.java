package com.mmall.content.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/****
 * @Author:qitianfeng
 * @Description:Content构建
 *****/
@ApiModel(description = "Content",value = "Content")
@Data
@TableName("content")
public class Content implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private Long id;
	/**
	 * 内容类目ID
	 */
	private Long goodId;
	/**
	 * 内容标题
	 */
	private String title;
	/**
	 * 链接
	 */
	private String url;
	/**
	 * 图片绝对路径
	 */
	private String pic;
	/**
	 * 状态,0无效，1有效
	 */
	private String status;
	/**
	 * 排序
	 */
	private Integer sortOrder;

}
