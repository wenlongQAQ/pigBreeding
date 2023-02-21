package com.zzut.pigbreeding.pojo.weather;

import lombok.Data;

import java.util.List;

@Data
public class LivesDto {
    private int status;
    private int count;
    private String info;
    private String infocode;
    private List<Lives> lives;
}
