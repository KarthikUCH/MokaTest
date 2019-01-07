package moka.pos.test.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by raju on 30/12/18.
 */

@Entity(tableName = "shopping_cart")
public class CartItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private Integer _ID;

    @ColumnInfo(name = "item_id")
    private Integer itemId;

    private Integer quantity;

    @ColumnInfo(name = "total_ptice")
    private Double totalPrice;

    private Double discount;

    @ColumnInfo(name = "discount_rate")
    private Double discountRate;

    public Integer get_ID() {
        return _ID;
    }

    public void set_ID(Integer _ID) {
        this._ID = _ID;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }
}
