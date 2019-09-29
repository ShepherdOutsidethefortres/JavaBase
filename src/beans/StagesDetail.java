package beans;

public class StagesDetail {
    private int periodNum;
    private int sumNrPri;
    private String periodNo;
    private int sumNrInt;
    private String status;
    private int nrRepayAmount;
    private int sumNrDefer;
    private int sumNrDelin;
    private int overdays;
    private String repayDate;
    public void setPeriodNum(int periodNum) {
        this.periodNum = periodNum;
    }
    public int getPeriodNum() {
        return periodNum;
    }

    public void setSumNrPri(int sumNrPri) {
        this.sumNrPri = sumNrPri;
    }
    public int getSumNrPri() {
        return sumNrPri;
    }

    public void setPeriodNo(String periodNo) {
        this.periodNo = periodNo;
    }
    public String getPeriodNo() {
        return periodNo;
    }

    public void setSumNrInt(int sumNrInt) {
        this.sumNrInt = sumNrInt;
    }
    public int getSumNrInt() {
        return sumNrInt;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setNrRepayAmount(int nrRepayAmount) {
        this.nrRepayAmount = nrRepayAmount;
    }
    public int getNrRepayAmount() {
        return nrRepayAmount;
    }

    public void setSumNrDefer(int sumNrDefer) {
        this.sumNrDefer = sumNrDefer;
    }
    public int getSumNrDefer() {
        return sumNrDefer;
    }

    public void setSumNrDelin(int sumNrDelin) {
        this.sumNrDelin = sumNrDelin;
    }
    public int getSumNrDelin() {
        return sumNrDelin;
    }

    public void setOverdays(int overdays) {
        this.overdays = overdays;
    }
    public int getOverdays() {
        return overdays;
    }

    public void setRepayDate(String repayDate) {
        this.repayDate = repayDate;
    }
    public String getRepayDate() {
        return repayDate;
    }

}