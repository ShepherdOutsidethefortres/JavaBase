/**
 * Copyright 2019 bejson.com
 */
package beans;
import java.util.Date;

/**
 * Auto-generated: 2019-09-26 14:25:30
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class LendingDetail {

    private String amount;
    private String cancelStatus;
    private String loanId;
    private String loanTerm;
    private String repayMode;
    private String settleStatus;
    private String stage;
    private Date startDate;
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getAmount() {
        return amount;
    }

    public void setCancelStatus(String cancelStatus) {
        this.cancelStatus = cancelStatus;
    }
    public String getCancelStatus() {
        return cancelStatus;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }
    public String getLoanId() {
        return loanId;
    }

    public void setLoanTerm(String loanTerm) {
        this.loanTerm = loanTerm;
    }
    public String getLoanTerm() {
        return loanTerm;
    }

    public void setRepayMode(String repayMode) {
        this.repayMode = repayMode;
    }
    public String getRepayMode() {
        return repayMode;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }
    public String getSettleStatus() {
        return settleStatus;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
    public String getStage() {
        return stage;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getStartDate() {
        return startDate;
    }

}