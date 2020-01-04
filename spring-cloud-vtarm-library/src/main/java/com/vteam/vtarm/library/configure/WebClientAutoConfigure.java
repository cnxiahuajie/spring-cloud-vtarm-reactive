package com.vteam.vtarm.library.configure;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientAutoConfigure {

    @Bean(name = "articleClient")
    public WebClient articleClient() {
        WebClient webClient = WebClient.create("http://localhost:6060/article/");
        webClient.get().uri("actuator/health").retrieve().bodyToFlux(JSONObject.class).log("Create articleClient").subscribe();
        return webClient;
    }

}
