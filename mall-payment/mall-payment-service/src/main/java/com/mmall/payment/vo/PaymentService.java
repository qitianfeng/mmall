package com.mmall.payment.vo;

import com.mmall.goods.GoodsService;
import org.apache.dubbo.config.annotation.Reference;

public class PaymentService {

    @Reference
    private GoodsService goddsService;
}
