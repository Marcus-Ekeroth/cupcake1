package app.entities;/* @auther: Frederik Dupont */

import app.persistence.ConnectionPool;
import io.javalin.http.Context;

import java.util.List;

public class Bottom {
    public int bottomId;
    public String bottomName;
    public int bottomPrice;

    public Bottom(int bottomId, String bottomName, int bottomPrice) {
        this.bottomId = bottomId;
        this.bottomName = bottomName;
        this.bottomPrice = bottomPrice;
    }

    public Bottom(String bottomName, int bottomPrice) {
        this.bottomName = bottomName;
        this.bottomPrice = bottomPrice;
    }

    public int getBottomId() {
        return bottomId;
    }

    public String getBottomName() {
        return bottomName;
    }

    public int getBottomPrice() {
        return bottomPrice;
    }
}
