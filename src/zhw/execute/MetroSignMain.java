package zhw.execute;

import org.apache.commons.codec.digest.DigestUtils;
import zhw.domain.CouponQueryVo;
import zhw.utils.MetroSignUtil;

public class MetroSignMain {
    public static void main(String[] args){
        CouponQueryVo couponQueryVo = new CouponQueryVo();
        //根据用户ID获取三段码
        //开发测试用号
        couponQueryVo.setCustkey(Integer.valueOf(2077329));
        couponQueryVo.setStorekey(Integer.valueOf(37));
        couponQueryVo.setCardholderkey(Integer.valueOf(1));
//        //赵慧伟
//        couponQueryVo.setCustkey(Integer.valueOf(2135587));
//        couponQueryVo.setStorekey(Integer.valueOf(11));
//        couponQueryVo.setCardholderkey(Integer.valueOf(1));
        String signKey = "kIH3^ydbnsjhGdT&*fa6ghVYgFJKH7-5jdgHb62#bc=fcvS90B";

        String bodyStr = MetroSignUtil.toBodyString(couponQueryVo, true);
        String signature = MetroSignUtil.toSignString(bodyStr, signKey, couponQueryVo);
        String signatureSha1 = DigestUtils.sha1Hex(signature);

        System.out.println("sign："+signatureSha1);
    }
}
