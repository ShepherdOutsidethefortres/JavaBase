package zhw.execute;

import org.apache.commons.codec.digest.DigestUtils;

public class SignMain {
    public static void main(String[] args) {
        String organ = "1001002";
        String timestamp = "1557203607305";
//        String json = "{\"deviceId\":\"450\",\"externalCode\":\"HGKJYXNFHAGS\",\"vendorId\":\"92\",\"storeId\":\"12919\",\"wareInputs\":[{\"wareCode\":\"692019102417\",\"count\":2}]}";
        String json2 = "{\"deviceId\":\"8966\",\"externalCode\":\"ZZ123123\",\"vendorId\":\"250\",\"storeId\":\"17337\",\"wareInputs\":[{\"wareCode\":\"3104644\",\"count\":1}]}";
        String secretKey = "b9dcf4e3aff54b19b12a35633d58cede";
        String values = organ + timestamp + json2.replace(" ", "") + secretKey;

        String sign = DigestUtils.sha512Hex(values);

        System.out.println(sign);
    }
}
