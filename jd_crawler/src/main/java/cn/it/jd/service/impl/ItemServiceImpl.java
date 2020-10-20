package cn.it.jd.service.impl;

import cn.it.jd.dao.ItemDao;
import cn.it.jd.pojo.Item;
import cn.it.jd.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Example;

import java.util.List;



@Service
public  class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;



    @Override
    public void sava(Item item) {
        this.itemDao.save(item);
    }

    @Override
    public List<Item> findAll(Item item){
        Example<Item> example = Example.of(item);
        List<Item> list = this.itemDao.findAll(example);
        return list;
    }
}
