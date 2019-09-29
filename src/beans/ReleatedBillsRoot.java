package beans;

public class ReleatedBillsRoot {
    private String message;
    private ReleatedBillsRet result;
    private String code;
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setResult(ReleatedBillsRet result) {
        this.result = result;
    }
    public ReleatedBillsRet getResult() {
        return result;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
