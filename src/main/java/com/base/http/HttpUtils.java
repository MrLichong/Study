package com.base.http;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * http请求工具类
 * @author LiChong
 * @Date 2018/11/25
 */
public class HttpUtils {

   private static final Logger logger = LogManager.getLogger(HttpUtils.class);

   /**
    * post请求
    * @param url 请求url
    * @param params 请求参数
    * @return
    * @throws Exception
    */
   public static String post(String url, Map<String, Object> params) throws Exception {
      CloseableHttpClient httpClient = getHttpClient();
      CloseableHttpResponse httpResponse = null;
      try {
         HttpPost post = new HttpPost(url);
         // 创建参数列表
         List<NameValuePair> list = new ArrayList<>();
         Iterator<?> iterator = params.entrySet().iterator();
         while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = entry.getKey().toString();
            String val = entry.getValue() == null ? "" : entry.getValue().toString();
            list.add(new BasicNameValuePair(key, val));
         }
         // url格式编码
         UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
         post.setEntity(uefEntity);
         logger.info("POST 请求...." + post.getURI()+",params:"+ JSON.toJSONString(params));
         post.setHeader("ContentType", "application/json");
         // 执行请求
         httpResponse = httpClient.execute(post);
         if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            logger.info("请求成功");
         } else {
            throw new Exception("请求失败：" + httpResponse.getStatusLine().getStatusCode());
         }
         return EntityUtils.toString(httpResponse.getEntity());

      } catch (Exception e) {
         logger.error(e.getMessage());

      } finally {
         try {
            httpResponse.close();
            closeHttpClient(httpClient);
         } catch (Exception e) {
            logger.error(e.getMessage());
         }
      }
      return null;
   }

   /**
    * get请求
    * @param url
    * @param params
    * @return
    * @throws Exception
    */
   public static String get(String url, Map<String, Object> params) throws Exception {
      // url = url.toString().replaceAll("\"","\\\\\"");
      CloseableHttpClient httpClient = getHttpClient();
      CloseableHttpResponse httpResponse = null;
      String result = "";
      try {
         StringBuffer urlParams = new StringBuffer();
         // 创建参数列表
         List<NameValuePair> list = new ArrayList<>();
         if (params != null) {
            Iterator<?> iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
               Map.Entry entry = (Map.Entry) iterator.next();
               String key = entry.getKey().toString();
               String val = entry.getValue().toString();
               list.add(new BasicNameValuePair(key, val));
               urlParams.append(key).append("=").append(val).append("&");
            }
         }
         String urlParam = "";
         if (!urlParams.toString().equals("")) {
            urlParam = "?" + urlParams.toString().substring(0, urlParams.toString().length() - 1);
         }
         HttpGet get = new HttpGet(url + urlParam);
         // 发送get请求
         httpResponse = httpClient.execute(get);
         // response实体
         HttpEntity entity = httpResponse.getEntity();
         if (null != entity) {
            result = EntityUtils.toString(entity);
            logger.info("响应状态码:" + httpResponse.getStatusLine());
            logger.info("-------------------------------------------------");
            logger.info("响应内容:" + result);
         }
      } catch (Exception e) {
         logger.error(e.getMessage());
      } finally {
         try {
            httpResponse.close();
            closeHttpClient(httpClient);
         } catch (IOException e) {
            logger.error(e.getMessage());
         }
      }
      return result;
   }
   private static CloseableHttpClient getHttpClient() {
      return HttpClients.createDefault();
   }
   private static void closeHttpClient(CloseableHttpClient client) throws IOException {
      if (client != null) {
         client.close();
      }
   }
}