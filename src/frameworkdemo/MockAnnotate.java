package frameworkdemo;

import org.mockito.Mock;

public class MockAnnotate {
    private String title;
    private String desc;

    public String getTitle() {
        return "title";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return "desc";
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
