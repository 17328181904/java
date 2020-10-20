package cn.it.jd.util;


import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import sun.net.www.http.HttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;


@Component
public class HttpUtils {

    private PoolingHttpClientConnectionManager cm;

    public HttpUtils() {
        this.cm = new PoolingHttpClientConnectionManager();
        this.cm.setMaxTotal(100);
//        this.cm.setMaxPerRoute(100);
    }

    /*
      * 
      * @author 15827
      * @date 2020-09-19 08:28:39 
     * @param url
      * @return
     **/
    public String doGetHtml(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(this.getGonfig());
        CloseableHttpResponse response = null;
        try{
             response = httpClient.execute(httpGet);
             if (response.getStatusLine().getStatusCode()==200){
                 if(response.getEntity() != null){
                     String content = EntityUtils.toString(response.getEntity(),"utf8");
                     return content;
                 }
             }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (response !=null){
                response.close();
            }
        }
        return "";
    }

    public  String doGetImage(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(this.getGonfig());
        CloseableHttpResponse response = null;
        try{
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode()==200){
                if(response.getEntity() != null){
                   String extName = url.substring(url.lastIndexOf("."));
                   String picName = UUID.randomUUID().toString()+extName;
                    OutputStream outputStream=new FileOutputStream(new File("C:\\Users\\15827\\Desktop")+picName);
                   response.getEntity().writeTo(outputStream);
                   return picName;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (response !=null){
                response.close();
            }
        }
        return "";
    }

    public RequestConfig getGonfig() {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000).setConnectionRequestTimeout(500).setSocketTimeout(10000).build();
        return config;
    }
}
