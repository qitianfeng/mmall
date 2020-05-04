package com.mmall.cart.controller;


import com.github.pagehelper.PageInfo;
import com.mmall.cart.CartItemService;
import com.mmall.cart.bean.CartItem;
import com.mmall.entity.Result;
import com.mmall.entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/****
 * @Author:qitianfeng
 * @Description:
 *****/

@Api(value = "购物车CartItemController",tags = "CartItemController",description = "购物车模块")
@RestController
@RequestMapping("/cartItem")
@CrossOrigin
public class CartItemController {

    @Resource
    private CartItemService cartItemService;

    /***
     * CartItem分页条件搜索实现
     * @param cartItem
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "CartItem条件分页查询",notes = "分页条件查询CartItem方法详情",tags = {"CartItemController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "CartItem对象",value = "传入JSON数据",required = false) CartItem cartItem, @PathVariable  int page, @PathVariable  int size){
        //调用CartItemService实现分页条件查询CartItem
        PageInfo<CartItem> pageInfo = cartItemService.findPage(cartItem, page, size);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * CartItem分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "CartItem分页查询",notes = "分页查询CartItem方法详情",tags = {"CartItemController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用CartItemService实现分页查询CartItem
        PageInfo<CartItem> pageInfo = cartItemService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param cartItem
     * @return
     */
    @ApiOperation(value = "CartItem条件查询",notes = "条件查询CartItem方法详情",tags = {"CartItemController"})
    @PostMapping(value = "/search" )
    public Result<List<CartItem>> findList(@RequestBody(required = false) @ApiParam(name = "CartItem对象",value = "传入JSON数据",required = false) CartItem cartItem){
        //调用CartItemService实现条件查询CartItem
        List<CartItem> list = cartItemService.findList(cartItem);
        return new Result<List<CartItem>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "CartItem根据ID删除",notes = "根据ID删除CartItem方法详情",tags = {"CartItemController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用CartItemService实现根据主键删除
        cartItemService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改CartItem数据
     * @param cartItem
     * @param id
     * @return
     */
    @ApiOperation(value = "CartItem根据ID修改",notes = "根据ID修改CartItem方法详情",tags = {"CartItemController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "CartItem对象",value = "传入JSON数据",required = false) CartItem cartItem,@PathVariable Long id){
        //设置主键值
        cartItem.setCartId(id);
        //调用CartItemService实现修改CartItem
        cartItemService.update(cartItem);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增CartItem数据
     * @param cartItem
     * @return
     */
    @ApiOperation(value = "CartItem添加",notes = "添加CartItem方法详情",tags = {"CartItemController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "CartItem对象",value = "传入JSON数据",required = true) CartItem cartItem){
        //调用CartItemService实现添加CartItem
        cartItemService.add(cartItem);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询CartItem数据
     * @param id
     * @return
     */
    @ApiOperation(value = "CartItem根据ID查询",notes = "根据ID查询CartItem方法详情",tags = {"CartItemController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public Result<CartItem> findById(@PathVariable Long id){
        //调用CartItemService实现根据主键查询CartItem
        CartItem cartItem = cartItemService.findById(id);
        return new Result<CartItem>(true,StatusCode.OK,"查询成功",cartItem);
    }

    /***
     * 查询CartItem全部数据
     * @return
     */
    @ApiOperation(value = "查询所有CartItem",notes = "查询所CartItem有方法详情",tags = {"CartItemController"})
    @GetMapping
    public Result<List<CartItem>> findAll(){
        //调用CartItemService实现查询所有CartItem
        List<CartItem> list = cartItemService.findAll();
        return new Result<List<CartItem>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
