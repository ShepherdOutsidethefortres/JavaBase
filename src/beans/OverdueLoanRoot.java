/**
 * Copyright 2019 bejson.com
 */
package beans;

/**
 * Auto-generated: 2019-09-26 13:57:18
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class OverdueLoanRoot {

    private String message;
    private OverdueLoanRet result;
    private String code;
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setResult(OverdueLoanRet overdueLoanRet) {
        this.result = overdueLoanRet;
    }
    public OverdueLoanRet getResult() {
        return result;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

}