package com.zzut.pigbreeding;


import com.zzut.pigbreeding.paho.server.ConnectServer;
import com.zzut.pigbreeding.paho.server.impl.ConnectServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@ConfigurationProperties
public class PigBreedingApplication {


    public static void main(String[] args) {


        ConfigurableApplicationContext run = SpringApplication.run(PigBreedingApplication.class, args);
        ConnectServer bean = run.getBean(ConnectServer.class);
        bean.retainMessage();

    }

}
