import Utils.TextUtil;
import beans.*;
import com.alibaba.fastjson.JSON;

import java.util.Iterator;
import java.util.List;

public class MockData {
    public static void main(String[] args) {
        new MockData().mockStages();
    }

    /**
     * mock贷款逾期明细数据
     */
    public void mockOverdueLoan() {
        //文件中读取数据
        String jsonStr = TextUtil.readString3("./JavaBase/src/resources/data1");

        //解析数据
        OverdueLoanRoot overdueLoan = JSON.parseObject(jsonStr, OverdueLoanRoot.class);

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
        LendingRoot lendingList = JSON.parseObject(jsonStr, LendingRoot.class);

        //对0的数据随机赋值
        List<LendingDetail> data = lendingList.getResult().getData();

        Iterator<LendingDetail> iterator = data.iterator();

        while (iterator.hasNext()) {
            LendingDetail next = iterator.next();
            next.setAmount(String.valueOf(Math.random() * 999 + 1));
            next.setCancelStatus(String.valueOf((int) (Math.random() * 3)));
            next.setRepayMode(String.valueOf(repayModes[(int) (Math.random() * repayModes.length)]));
            next.setStage(String.valueOf(stages[(int) (Math.random() * stages.length)]));
            next.setSettleStatus(String.valueOf((int) (Math.random() * 2)));
        }

        //重新生成json串，写入新文件
        String newJStr = JSON.toJSONString(lendingList);

        TextUtil.WriteStringToFile("./JavaBase/src/resources/data2_output", newJStr);
    }

    public void mockReleatedBillsDetail() {
        //文件中读取数据
        String jsonStr = TextUtil.readString3("./JavaBase/src/resources/data3");

        //解析数据
        ReleatedBillsRoot releatedBills = JSON.parseObject(jsonStr, ReleatedBillsRoot.class);

        //因为数据太少，多加入一些
        List<ReleatedBillsDetail> data = releatedBills.getResult().getData();

        for (int i = 0; i < 50; i++) {
            ReleatedBillsDetail element = new ReleatedBillsDetail();

            element.setBillNo("20170508105101250");
            element.setOrderNo("1528094902168");
            element.setOrigOrderNo("1528094902168xxxx");
            element.setProductName("SMB");
            element.setJdpayOrderNo("120031528094902167");
            element.setMgrErp("jincai105");
            element.setOrderTime("2018-06-03 14:50:21");
            element.setPayTime("2018-06-03 14:50:21");
            element.setOrderAmount(20);
            element.setPayStatus("已支付");
            element.setSumNrAmount(1000);
            element.setOrderStatus("等待收货");
            element.setToBillStatus("已出账");
            element.setSaleAfterStatus("未进入售后");
            element.setInvoiceStatus("未开");
            element.setSettleStatus("未结清");

            data.add(element);
        }

        //重新生成json串，写入新文件
        String newJStr = JSON.toJSONString(releatedBills);

        TextUtil.WriteStringToFile("./JavaBase/src/resources/data3_output", newJStr);
    }

    public void mockStages() {
        //文件中读取数据
        String jsonStr = TextUtil.readString3("./JavaBase/src/resources/data4");

        //解析数据
        StagesRoot stagesList = JSON.parseObject(jsonStr, StagesRoot.class);

        //因为数据太少，多加入一些
        List<StagesDetail> data = stagesList.getResult().getData();

        int j = 4;

        for (int i = 0; i < 50; i++) {
            StagesDetail element = new StagesDetail();

            element.setPeriodNum(j++);
            element.setPeriodNo("201705111941354564158");
            element.setSumNrInt(1000);
            element.setSumNrDefer(400);
            element.setOverdays(10);
            element.setSumNrPri(1500);
            element.setStatus("客官没结账呢");
            element.setNrRepayAmount(1111);
            element.setRepayDate("2018-06-03 14:50:21");

            data.add(element);
        }

        //重新生成json串，写入新文件
        String newJStr = JSON.toJSONString(stagesList);

        TextUtil.WriteStringToFile("./JavaBase/src/resources/data4_output", newJStr);
    }
}
