package app.entities;/* @auther: Frederik Dupont */

import java.util.List;

public class Topping {
    public int toppingId;
    public String toppingName;
    public int toppingPrice;

    public Topping(int toppingId, String toppingName, int toppingPrice) {
        this.toppingId = toppingId;
        this.toppingName = toppingName;
        this.toppingPrice = toppingPrice;
    }
    public static Topping toppingById(int id, List<Topping> toppingList){
        Topping topping = null;
        for (Topping t : toppingList) {
            if(t.getToppingId()==id){
                topping = t;
            }
        }
        return topping;
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
