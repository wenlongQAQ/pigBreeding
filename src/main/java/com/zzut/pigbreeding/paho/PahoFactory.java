package com.zzut.pigbreeding.paho;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PahoFactory {
    @Bean
    public static PahoClientFactory getPahoClientFactory(){
        return new PahoClientFactory();
    }

}
