package moka.pos.test.data.model;

/**
 * Created by karthikeyan on 25/1/18.
 */

public class CartItem {

    private int itemId;
    private String itemTitle;
    private String thumbnailUrl;
    private int quantity;
    private int totalPrice;
    private double discount;
    private long discountRate;

    public CartItem() {
    }

    public CartItem(int itemId, int quantity, double discount) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.discount = discount;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public long getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(long discountRate) {
        this.discountRate = discountRate;
    }
}
