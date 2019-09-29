package beans;

import java.util.List;

public class ReleatedBillsRet {
    private String total;
    private int pageSize;
    private List<ReleatedBillsDetail> data;
    private int totalPage;
    private int pageNum;

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setData(List<ReleatedBillsDetail> data) {
        this.data = data;
    }

    public List<ReleatedBillsDetail> getData() {
        return data;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageNum() {
        return pageNum;
    }
}
