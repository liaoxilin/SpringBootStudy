package com.liaoxilin.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.liaoxilin.demo.dto.AccessTokenDto;
import com.liaoxilin.demo.dto.GithubUser;
import com.liaoxilin.demo.mapper.UserMapper;
import com.liaoxilin.demo.model.User;
import com.liaoxilin.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    //把Spring容器实例化好的容器加载到当前上下文
    @Autowired
    private GithubProvider githubProvider;


    @Value("${github.client_id}")//去配置文件中读取其的value，
    private String clientId;
    @Value("${github.client_secret}")
    private String clientSecret;
    @Value("${github.redirect_uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request,
                            HttpServletResponse response) {
//        AccessTokenDto accessTokenDto = new AccessTokenDto();
//        accessTokenDto.setCline_id("Iv1.bb3541fd43c2c64c");
//        accessTokenDto.setCline_secret("0ee2a018bb58adeae8a187b9611649cdeebfe48f");
//        accessTokenDto.setCode(code);
//        accessTokenDto.setRedirect_uri("http://localhost:8080/callback");
//        accessTokenDto.setState(state);
        JSONObject o = new JSONObject();
        o.put("client_id",clientId);
        o.put("client_secret",clientSecret);
        o.put("code",code);
        o.put("redirect_uri",redirectUri);
        o.put("state",state);

        String accessToken= githubProvider.getAccessToken(o);
        GithubUser githubUser=githubProvider.getUser(accessToken);
        if(githubUser!=null&&githubUser.getId()!=null){
            User user=new User();
            String token=UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGetCreate(System.currentTimeMillis());
            user.setGetModified(user.getGetCreate());
            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }
        else{
            //登录失败，重新登录
            return "redirect:/";
        }


    }
}
