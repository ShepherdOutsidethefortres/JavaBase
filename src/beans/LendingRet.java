/**
 * Copyright 2019 bejson.com
 */
package beans;
import java.util.List;

/**
 * Auto-generated: 2019-09-26 14:25:30
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class LendingRet {

    private int total;
    private List<LendingDetail> data;
    public void setTotal(int total) {
        this.total = total;
    }
    public int getTotal() {
        return total;
    }

    public void setData(List<LendingDetail> data) {
        this.data = data;
    }
    public List<LendingDetail> getData() {
        return data;
    }

}