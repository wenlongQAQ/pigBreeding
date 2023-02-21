package com.zzut.pigbreeding.excption;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SendMessageException extends RuntimeException{
    public SendMessageException(String message) {
        super(message);
        log.error(message);
    }
}
