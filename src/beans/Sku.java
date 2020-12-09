package beans;

/**
 * @Author: yesong
 * @Date: 2020/11/19 5:35 PM
 * 商品信息
 */
public class Sku {
    public Sku(String code, Integer num) {
        this.code = code;
        this.num = num;
    }

    /**
     * 商品编码 S1...Sn
     */
    private String code;

    /**
     * 商品的数量
     */
    private Integer num;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
