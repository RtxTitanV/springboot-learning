package com.rtxtitanv.service;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.service.SmsService
 * @description SmsService
 * @date 2021/6/25 16:20
 */
public interface SmsService {

    /**
     * 发送短信验证码
     *
     * @param phoneNumber 手机号
     * @return
     */
    String sendSmsCode(String phoneNumber);

    /**
     * 验证短信验证码
     *
     * @param phoneNumber 手机号
     * @param smsCode     短信验证码
     * @return
     */
    String verifySmsCode(String phoneNumber, String smsCode);
}