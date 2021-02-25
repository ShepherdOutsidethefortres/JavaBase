package zhw.designpattern.decorate.silicon;

public abstract class Drink {
    public String des;//描述
    private float price = 0.0f;

    public abstract float cost();

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
