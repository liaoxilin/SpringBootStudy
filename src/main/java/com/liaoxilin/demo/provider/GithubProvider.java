package com.liaoxilin.demo.provider;


import com.alibaba.fastjson.JSON;
import com.liaoxilin.demo.dto.AccessTokenDto;
import com.liaoxilin.demo.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;


//把当前类初始到容器的上下文（加了注解就不需要实例化对象了）
@Component
public class GithubProvider {
    public String getAccessToken(JSON accessTokenDto){

        MediaType mediaType = MediaType.get("application/json;charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDto),mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string=response.body().string();
            String[] split=string.split("&");
            String tokenstr=split[0];
            String token=tokenstr.split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.github.com/user")
                    .header("Authorization","token "+accessToken)
                    .build();

        try {
            Response response = client.newCall(request).execute();
            String string=response.body().string();
            GithubUser githubUser=JSON.parseObject(string,GithubUser.class);
            return githubUser;
        } catch (IOException e) {

        }
        return null;
    }

}
