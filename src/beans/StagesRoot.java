package beans;

public class StagesRoot {
    private String message;
    private StagesRet result;
    private String code;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setResult(StagesRet result) {
        this.result = result;
    }

    public StagesRet getResult() {
        return result;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
