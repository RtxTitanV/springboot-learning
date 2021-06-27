package com.rtxtitanv.controller;

import com.rtxtitanv.service.SmsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.controller.SmsController
 * @description SmsController
 * @date 2021/6/25 16:20
 */
@RequestMapping("/sms")
@RestController
public class SmsController {

    @Resource
    private SmsService smsService;

    @PostMapping("/send")
    public String sendSms(@RequestParam(value = "phoneNumber") String phoneNumber) {
        return smsService.sendSmsCode(phoneNumber);
    }

    @PostMapping("/verify")
    public String verifySmsCode(@RequestParam(value = "phoneNumber") String phoneNumber,
        @RequestParam(value = "smsCode") String smsCode) {
        return smsService.verifySmsCode(phoneNumber, smsCode);
    }
}