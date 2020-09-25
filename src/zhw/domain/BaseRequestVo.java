package zhw.domain;


import zhw.utils.RandomStringGenerator;

/**
 * @author xiongz
 * @Description
 * @create 2020-04-14 17:06
 */
public class BaseRequestVo {

    /**
     * 10位随机字符串 (用于签名)
     */
    private String nonce = RandomStringGenerator.getRandomStringByLength(10);

    /**
     * 时间戳 (用于签名),10位正整数
     */
    private String timestamp = String.valueOf(System.currentTimeMillis() / 1000);


    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
