package zhw.execute;

import org.apache.commons.codec.digest.DigestUtils;

public class SignMain {
    public static void main(String[] args) {
        String organ = "1001001";
        String timestamp = "1570723466000";
//        String json = "{\"deviceId\":\"450\",\"externalCode\":\"HGKJYXNFHAGS\",\"vendorId\":\"92\",\"storeId\":\"12919\",\"wareInputs\":[{\"wareCode\":\"692019102417\",\"count\":2}]}";
        String json2 = "{\"deviceId\":\"9015\",\"externalCode\":\"ZZ124124\",\"vendorId\":\"1\",\"storeId\":\"17337\",\"wareInputs\":[{\"wareCode\":\"4209268\",\"count\":7}]}";
        String secretKey = "a8d85653ad284ef8b8147312c5fc8145";
        String values = organ + timestamp + json2.replace(" ", "") + secretKey;

        String sign = DigestUtils.sha512Hex(values);

        System.out.println(sign);
    }
}
