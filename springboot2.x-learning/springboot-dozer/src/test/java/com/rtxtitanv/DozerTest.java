package com.rtxtitanv;

import com.github.dozermapper.core.Mapper;
import com.rtxtitanv.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.DozerTest
 * @description Dozer单元测试类
 * @date 2021/8/18 16:44
 */
@Slf4j
@SpringBootTest
class DozerTest {

    @Resource
    private Mapper mapper;

    @Test
    void test1() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1L).setUserName("ZhaoYun").setGender("男").setUserAge(20).setEmail("zhaoyun@xxx.com")
            .setBirthday("2001/8/18 18:05:32");
        User user = mapper.map(userDTO, User.class);
        log.info(user.toString());
        UserDTO userDTO2 = mapper.map(user, UserDTO.class);
        log.info(userDTO2.toString());
    }

    @Test
    void test2() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(2L).setUserName("MaChao").setGender("男").setUserAge(21).setEmail("machao@xxx.com")
            .setBirthday("2000/6/15 08:45:20");
        User user = mapper.map(userDTO, User.class, "user");
        log.info(user.toString());
    }

    @Test
    void test3() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(3L).setUserName("LiuBei").setGender("男").setUserAge(30).setEmail("liubei@xxx.com")
            .setBirthday("1991/1/20 13:36:55");
        User user = new User();
        mapper.map(userDTO, user, "user");
        log.info(user.toString());
    }

    @Test
    void test4() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1L).setUserName("ZhaoYun").setGender("男").setUserAge(20).setEmail("zhaoyun@xxx.com")
            .setBirthday("2001/8/18 18:05:32");
        User user = mapper.map(userDTO, User.class, "user-exclude");
        log.info(user.toString());
    }

    @Test
    void test5() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1L).setUserName("ZhaoYun").setGender("男").setUserAge(20).setEmail("zhaoyun@xxx.com")
            .setBirthday("2001/8/18 18:05:32");
        User user = mapper.map(userDTO, User.class, "user-oneway");
        log.info(user.toString());
        UserDTO userDTO2 = mapper.map(user, UserDTO.class, "user-oneway");
        log.info(userDTO2.toString());
    }

    @Test
    void test6() {
        OrderDTO orderDTO = new OrderDTO();
        UserDTO userDTO = new UserDTO().setUserId(6L).setUserName("DiaoChan").setGender("女").setUserAge(18)
            .setEmail("diaochan@xxx.com").setBirthday("2003/12/27 23:10:36");
        orderDTO.setOrderId(1L).setOrderNumber("78956328").setOrderDescription("二两麻辣牛肉面").setUserDTO(userDTO);
        Order order = mapper.map(orderDTO, Order.class, "order");
        log.info(order.toString());
        OrderDTO orderDTO2 = mapper.map(order, OrderDTO.class, "order");
        log.info(orderDTO2.toString());
    }

    @Test
    void test7() {
        UserInfo userInfo = new UserInfo();
        userInfo.setGender("男").setEmail("zhaoyun@xxx.com").setBirthday("2001/8/18 18:05:32");
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserId(1L).setUserName("ZhaoYun").setUserAge(20).setUserInfo(userInfo);
        User user = mapper.map(userInfoDTO, User.class, "user-deep-mapping");
        log.info(user.toString());
    }

    @Test
    void test8() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L).setName("ZhaoYun").setGender("男").setAge(20).setEmail("zhaoyun@xxx.com")
            .setBirthday("2001/8/18 18:05:32");
        UserVO userVO = mapper.map(userEntity, UserVO.class);
        log.info(userVO.toString());
    }
}