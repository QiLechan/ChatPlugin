package org.yuezhikong.ChatPlugin;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Post {
    public static List<getJson.MessagesBean> beans = new ArrayList<>();
    public static String getToken(String APIKey,String SecretKey) {
        String url = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=" + APIKey + "&client_secret=" + SecretKey;
        String result= HttpUtil.post(url, "");
        JSONObject json = JSONUtil.parseObj(result);
        String token = json.getStr("access_token");
        System.out.println("获取到了Access Token：" + token);
        return token;
    }
    public static String chat(String Question, String APIKey, String SecretKey){
        String url = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions?access_token=" + getToken(APIKey,SecretKey);
        getJson json = new getJson();
        getJson.MessagesBean messagesBean = new getJson.MessagesBean();
        messagesBean.setContent(Question);//Json中的Content
        messagesBean.setRole("user");//Json中的Role
        beans.add(messagesBean);
        json.setMessages(beans);
        String StringJson = new Gson().toJson(json);
        String response= HttpUtil.post(url, StringJson);
        JSONObject resultjson = JSONUtil.parseObj(response);
        String result = resultjson.getStr("result");
        String usage = JSONUtil.parseObj((resultjson.getStr("usage"))).getStr("total_tokens");
        System.out.println(result);
        System.out.println("使用Token数量：" + usage);
        return result;
    }
    public static void assistant(String result){
        getJson.MessagesBean messagesBean = new getJson.MessagesBean();
        messagesBean.setContent(result);//Json中的Content
        messagesBean.setRole("assistant");//Json中的Role
        beans.add(messagesBean);
    }
}
