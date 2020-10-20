package cn.it.jd.pojo;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="jd_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private Long spu;
    private Long sku;
    private  String title;
    private  Double price;
    private String pic;
    private String url;
    private  Date created;
    private  Date updated;

    public void setId(Long id) {
        this.id = id;
    }

    public void setSpu(Long spu) {
        this.spu = spu;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public Long getSpu() {
        return spu;
    }

    public Long getSku() {
        return sku;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public String getPic() {
        return pic;
    }

    public String getUrl() {
        return url;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }
}
