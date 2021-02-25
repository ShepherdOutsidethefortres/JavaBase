package zhw.designpattern.adaptive;

public class Client {
    public static void main(String[] args) {
        IUserInfo youngGirl = new UserInfo();

        for (int i = 0; i < 101; i++) {
            youngGirl.getMobileNumber();
        }

        //使用适配器模式
        IUserInfo youngGirl2 = new OuterUserInfo();
        for(int i=0;i<101;i++){
            youngGirl.getMobileNumber();
        }
    }
}
