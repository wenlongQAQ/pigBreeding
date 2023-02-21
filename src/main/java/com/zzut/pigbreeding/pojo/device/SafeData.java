package com.zzut.pigbreeding.pojo.device;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SafeData {
    private Long deviceId;
    private Long id;
    private float safeDataMin;
    private float  safeDataMax;

}
