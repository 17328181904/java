package cn.it.jd.task;

import cn.it.jd.pojo.Item;
import cn.it.jd.service.ItemService;
import cn.it.jd.util.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.scheduling.annotation.Scheduled;

//import javax.swing.text.Document;
//import javax.swing.text.Element;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;



/**
 * @author 15827
 * @date 2020-09-19 9:15
 */

@Component
public class ItemTask  {
    @Autowired
    private HttpUtils httpUtils;
    private ItemService itemService;

    @Scheduled(fixedDelay = 100*1000)
    public void itemTask() throws IOException {

//        String urls="https://search.jd.com/search?keyword=%E6%89%8B%E6%9C%BA&page={0}&s={1}&click=0";
//        int s = 1;
//        for (int i=1;i<10;i=i+2){
//
//            String url = MessageFormat.format(urls, i,s);
//            System.out.println(url);
//            String html = null;
//            try {
//                html = httpUtils.doGetHtml(url);
//                System.out.println(html);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            this.parse(html);
//           s+=50;
//        }
        System.out.println("finished");
    }

    private  void parse(String html) throws IOException{
        Document doc = Jsoup.parse(html);
        Elements spuEles = doc.select("div#J_goodsList>ul>li");
        for (Element spuEle:spuEles){
            long spu=Long.parseLong(spuEle.attr("data-spu"));
            Elements skuEles=spuEle.select("li.ps-item");
            for (Element skuEle:skuEles){
                Long sku = Long.parseLong(skuEle.select("[data-sku]").attr("data-sku"));

                Item item = new Item();
                item.setSku(sku);
                List<Item> list = this.itemService.findAll(item);
                if(list.size()>0){
                    continue;
                }

                item.setSpu(spu);
                String itemUrl="https://item.jd.com/"+sku+".html";
                item.setUrl(itemUrl);
//                item.setPic();
//                item.setPrice();
//                item.setTitle();
                item.setCreated(new Date());
                item.setUpdated(item.getCreated());
            }
        }


    }

//    public static void main(String[] args) {
//        ItemTask url = new ItemTask();
//
//        url.itemTask();
//
//
//    }
}
