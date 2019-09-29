import Utils.TextUtil;
import beans.LendingDetail;
import beans.LendingList;
import beans.OverdueLoan;
import beans.OverdueLoanDetail;
import com.alibaba.fastjson.JSON;

import java.util.Iterator;
import java.util.List;

public class MockData {
    public static void main(String[] args) {
        new MockData().mockLendingdetails();

    }

    /**
     * mock贷款逾期明细数据
     */
    public void mockOverdueLoan() {
        //文件中读取数据
        String jsonStr = TextUtil.readString3("./JavaBase/src/resources/data1");

        //解析数据
        OverdueLoan overdueLoan = JSON.parseObject(jsonStr, OverdueLoan.class);

        //对0的数据随机赋值
        List<OverdueLoanDetail> data = overdueLoan.getResult().getData();

        Iterator<OverdueLoanDetail> iterator = data.iterator();

        while (iterator.hasNext()) {
            OverdueLoanDetail next = iterator.next();
            next.setOvdAmount(String.valueOf(Math.random() * 99 + 1));
            next.setOvdCoi(String.valueOf(Math.random() * 99 + 1));
            next.setOvdCop(String.valueOf(Math.random() * 99 + 1));
            next.setOvdpenalty(String.valueOf(Math.random() * 99 + 1));
        }

        //重新生成json串，写入新文件
        String newJStr = JSON.toJSONString(overdueLoan);

        TextUtil.WriteStringToFile("./JavaBase/src/resources/data1_output", newJStr);
    }

    public void mockLendingdetails() {
        int[] repayModes = {1, 2, 4, 7};
        int[] stages = {2, 5};

        //文件中读取数据
        String jsonStr = TextUtil.readString3("./JavaBase/src/resources/data1");

        //解析数据
        LendingList lendingList = JSON.parseObject(jsonStr, LendingList.class);

        //对0的数据随机赋值
        List<LendingDetail> data = lendingList.getResult().getData();

        Iterator<LendingDetail> iterator = data.iterator();

        while (iterator.hasNext()) {
            LendingDetail next = iterator.next();
            next.setAmount(String.valueOf(Math.random() * 999 + 1));
            next.setCancelStatus(String.valueOf((int)(Math.random() * 3)));
            next.setRepayMode(String.valueOf(repayModes[(int) (Math.random() * repayModes.length)]));
            next.setStage(String.valueOf(stages[(int) (Math.random() * stages.length)]));
            next.setSettleStatus(String.valueOf((int) (Math.random() * 2)));
        }

        //重新生成json串，写入新文件
        String newJStr = JSON.toJSONString(lendingList);

        TextUtil.WriteStringToFile("./JavaBase/src/resources/data2_output", newJStr);
    }
}
