/**
 * Copyright 2019 bejson.com
 */
package beans;
import java.util.List;

/**
 * Auto-generated: 2019-09-26 13:57:18
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class OverdueLoanRet {

    private int total;
    private List<OverdueLoanDetail> data;
    public void setTotal(int total) {
        this.total = total;
    }
    public int getTotal() {
        return total;
    }

    public void setData(List<OverdueLoanDetail> data) {
        this.data = data;
    }
    public List<OverdueLoanDetail> getData() {
        return data;
    }

}