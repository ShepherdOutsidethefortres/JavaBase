package zhw.domain;

/**
 * @author xiongz
 * @Description
 * @create 2020-04-14 17:25
 */

public class CouponQueryVo extends BaseRequestVo {

    /**
     * 店号	数字	10
     */
    private Integer storekey = 10;

    /**
     * 客户编号	数字	160682
     */
    private Integer custkey = 912835;

    /**
     * 持卡人号	数字	1
     */
    private Integer cardholderkey = 1;


    public Integer getStorekey() {
        return storekey;
    }

    public void setStorekey(Integer storekey) {
        this.storekey = storekey;
    }

    public Integer getCustkey() {
        return custkey;
    }

    public void setCustkey(Integer custkey) {
        this.custkey = custkey;
    }

    public Integer getCardholderkey() {
        return cardholderkey;
    }

    public void setCardholderkey(Integer cardholderkey) {
        this.cardholderkey = cardholderkey;
    }
}
