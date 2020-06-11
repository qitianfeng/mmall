package com.mmall.order.controller;


import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mmall.base.QiException;
import com.mmall.order.OrderService;
import com.mmall.order.entity.Order;
import com.mmall.order.utils.ConstantPropertiesApliPayUtil;
import com.mmall.order.vo.AlipayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class PayController {

    @Autowired
    OrderService orderService;

    @GetMapping("/alipay/{orderNo}")
    public String alipay(@PathVariable String orderNo, HttpServletResponse response) {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getOrderId, orderNo);
        Order order = orderService.getOne(wrapper);
        if (order == null) {
            return "fail";
        }
        String singnType = ConstantPropertiesApliPayUtil.SINGN_TYPE;
        AlipayClient alipayClient = new DefaultAlipayClient(ConstantPropertiesApliPayUtil.GATEWAY_URL,
                ConstantPropertiesApliPayUtil.APPID, ConstantPropertiesApliPayUtil.APP_PRIVATE_KEY, ConstantPropertiesApliPayUtil.FORMAT,
                "utf-8",
                ConstantPropertiesApliPayUtil.APP_PUBLIC_KEY, "RSA2");

        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //在公共参数中设置会跳和通知地址
        request.setReturnUrl(ConstantPropertiesApliPayUtil.RETURN_URL);
        request.setNotifyUrl(ConstantPropertiesApliPayUtil.NOTIFY_URL);

        String orderOrderNo = order.getOrderId();
        BigDecimal totalFee = order.getTotalAmount();

        AlipayVo alipayVo = new AlipayVo();
        alipayVo.setOut_trade_no(orderOrderNo);
        alipayVo.setProduct_code("FAST_INSTANT_TRADE_PAY");
        alipayVo.setSubject(order.getGoodsName());
        alipayVo.setTotal_amount(totalFee);
        String jsonString = JSON.toJSONString(alipayVo);

        request.setBizContent(jsonString);
        String form = null;

        try {
            form = alipayClient.pageExecute(request).getBody(); /// 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

       /* response.setContentType("text/html;charset=utf-8");
        try {
            response.getWriter().write(form);
            response.getWriter().flush();
            response.getWriter().close();

        } catch (IOException e) {
            e.printStackTrace();
        }*/
//将表达返回
        return form;
    }

    @PostMapping("notify")
    public String alipayNotify(HttpServletRequest request, String out_trade_no, String trade_no, String trade_status) {
        Map<String, String> paramsMap = getParamsMap(request);
        //验证签名
        try {
            boolean b = AlipaySignature.rsaCheckV1(paramsMap, ConstantPropertiesApliPayUtil.APP_PUBLIC_KEY, "utf-8", ConstantPropertiesApliPayUtil.SINGN_TYPE);
            if (b) {
                // 商户订单号
                String outTradeNo = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

                // 支付宝交易号
                String tradeNo = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

                // 付款金额
                String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

                System.out.println("商户订单号=" + outTradeNo);
                System.out.println("支付宝交易号=" + tradeNo);
                System.out.println("付款金额=" + total_amount);

                //支付成功，修复支付状态
                return "success";
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new QiException(20001, "跳转付款失败");
        }
        return "";
    }
    @GetMapping("return")
    private String alipayReturn(Map<String, String> params, HttpServletRequest request, String out_trade_no,String trade_no,String total_amount)
            throws AlipayApiException {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                System.out.println(valueStr);
            }
            map.put(name, valueStr);
        }
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(map, ConstantPropertiesApliPayUtil.APP_PUBLIC_KEY, "UTF-8", "RSA2");//UTF-8要大写，不然验签失败
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return ("fail");// 验签发生异常,则直接返回失败
        }
        if (signVerified) {
            return ("success");
        } else {
            System.out.println("验证失败,不去更新状态");
            return ("fail");
        }
    }
    private Map<String, String> getParamsMap(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            try {
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return params;
    }
}
