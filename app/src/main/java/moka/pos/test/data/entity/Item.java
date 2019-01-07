package moka.pos.test.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import moka.pos.test.util.AppUtil;

/**
 * Created by raju on 30/12/18.
 */
@Entity(tableName = "items")
public class Item {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private Integer _ID;

    @ColumnInfo(name = "item_id")
    private Integer id;

    @ColumnInfo(name = "album_id")
    private Integer albumId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "price")
    private Long price;

    @ColumnInfo(name = "url")
    private String url;

    @ColumnInfo(name = "thumbnail_url")
    private String thumbnailUrl;

    public Item(Integer id, Integer albumId, String title, Long price, String url, String thumbnailUrl) {
        this._ID = _ID;
        this.id = id;
        this.albumId = albumId;
        this.title = title;
        this.price = price;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public Integer get_ID() {
        return _ID;
    }

    public void set_ID(Integer _ID) {
        this._ID = _ID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = (long) (getId() * AppUtil.getRandomNumber(10, 99));
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
