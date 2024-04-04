package app.entities;/* @auther: Frederik Dupont */

public class Topping {
    private int toppingId;
    private String toppingName;
    private int toppingPrice;

    public Topping(int toppingId, String toppingName, int toppingPrice) {
        this.toppingId = toppingId;
        this.toppingName = toppingName;
        this.toppingPrice = toppingPrice;
    }

    public int getToppingId() {
        return toppingId;
    }

    public String getToppingName() {
        return toppingName;
    }

    public int getToppingPrice() {
        return toppingPrice;
    }
}
