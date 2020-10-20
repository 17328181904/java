package cn.it.jd.service;

import cn.it.jd.pojo.Item;

import java.util.List;

public interface ItemService {
    public void sava(Item item);
    public List<Item> findAll(Item item);

}
