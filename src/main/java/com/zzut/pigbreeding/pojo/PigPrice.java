package com.zzut.pigbreeding.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PigPrice {
    private String name;
    private float price;
    private String change;
}
