package com.example.designpattern.pattern.structural.facade;

/**
 *
 * Created by geely
 */
public class GiftExchangeService {

    /**
     * 库存
     */
    private QualifyService qualifyService = new QualifyService();

    /**
     * 积分支付
     */
    private PointsPaymentService pointsPaymentService = new PointsPaymentService();

    /**
     * 物流
     */
    private ShippingService shippingService = new ShippingService();

    /**
     * 积分兑换
     */
    public void giftExchange(PointsGift pointsGift) {
        if (qualifyService.isAvailable(pointsGift)) {
            if (pointsPaymentService.pay(pointsGift)) {
                String shippingOrderNo = shippingService.shipGift(pointsGift);
                System.out.println("物流系统下单成功,订单号是:" + shippingOrderNo);
            }
        }
    }
}
