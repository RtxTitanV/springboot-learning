package com.rtxtitanv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.model.Account
 * @description Account
 * @date 2021/7/4 13:53
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
    private Long accountId;
    private String accountName;
    private String accountPassword;
    private User user;
    private List<Order> orders;
}