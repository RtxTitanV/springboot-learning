package com.rtxtitanv.service.impl;

import com.rtxtitanv.config.SmsConfig;
import com.rtxtitanv.service.SmsService;
import com.rtxtitanv.util.RandomUtil;
import com.rtxtitanv.util.RedisUtil;
import com.rtxtitanv.util.SmsUtil;
import com.tencentcloudapi.sms.v20210111.models.SendStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.service.impl.SmsServiceImpl
 * @description SmsService实现类
 * @date 2021/6/25 16:20
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Resource
    private SmsConfig smsConfig;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public String sendSmsCode(String phoneNumber) {
        // 下发手机号码，采用e.164标准，+[国家或地区码][手机号]
        String[] phoneNumbers = {"+86" + phoneNumber};
        // 生成6位随机数字字符串
        String smsCode = RandomUtil.randomNumbers(6);
        // 模板参数：若无模板参数，则设置为空（参数1为随机验证码，参数2为有效时间）
        String[] templateParams = {smsCode, smsConfig.getExpireTime()};
        // 发送短信验证码
        SendStatus[] sendStatuses = SmsUtil.sendSms(smsConfig, templateParams, phoneNumbers);
        if ("Ok".equals(sendStatuses[0].getCode())) {
            // 创建缓存的key
            String key = RedisUtil.createCacheKey(smsConfig.getPhonePrefix(), phoneNumber);
            // 将验证码缓存到redis并设置过期时间
            redisUtil.setCacheObject(key, smsCode, Long.parseLong(smsConfig.getExpireTime()));
            return "验证码发送成功";
        } else {
            return "验证码发送失败：" + sendStatuses[0].getMessage();
        }
    }

    @Override
    public String verifySmsCode(String phoneNumber, String smsCode) {
        // 创建key
        String key = RedisUtil.createCacheKey(smsConfig.getPhonePrefix(), phoneNumber);
        // 判断指定key是否存在并且未过期
        if (redisUtil.hasKey(key)) {
            // 验证输入的验证码是否正确
            if (smsCode.equals(redisUtil.getCacheObject(key))) {
                // 验证成功后删除验证码缓存
                redisUtil.delete(key);
                return "验证成功";
            } else {
                return "验证码错误";
            }
        } else {
            return "验证码已失效";
        }
    }
}