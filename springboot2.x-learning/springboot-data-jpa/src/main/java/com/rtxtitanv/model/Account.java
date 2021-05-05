package com.rtxtitanv.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.model.Account
 * @description 账户实体类 OneToMany 一对多的一方
 * @date 2020/1/3 20:34
 */
@Setter
@Getter
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "account_name", unique = true, nullable = false)
    private String accountName;
    @Column(name = "account_password", nullable = false)
    private String accountPassword;
    @Column(name = "account_alias")
    private String accountAlias;
    @Column(name = "account_addr")
    private String accountAddr;
    @Column(name = "account_tel", unique = true, nullable = false)
    private String accountTel;
    @Column(name = "account_rank", nullable = false)
    private Long accountRank;
    @Column(name = "account_location", nullable = false)
    private String accountLocation;
    @OneToMany(mappedBy = "account", //引用在多方实体类中一方实体类对象名称，一方放弃维护外键关系
            //CascadeType为级联设置：操作一个对象同时操作它的关联对象
            // PERSIST：保存，REFRESH：刷新，MERGE：合并，REMOVE：删除
            //FetchType：设置加载方式，LAZY：延迟加载，EAGER：立即加载
            //orphanRemoval：是否使用孤儿删除，即在一方关联多方的集合中移除的多方记录将
            //成为孤儿，没有与一方任何一条记录关联，此时会自动删除这些多方记录，true：使用，false：不使用
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE},
            fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountName='" + accountName + '\'' +
                ", accountPassword='" + accountPassword + '\'' +
                ", accountAlias='" + accountAlias + '\'' +
                ", accountAddr='" + accountAddr + '\'' +
                ", accountTel='" + accountTel + '\'' +
                ", accountRank=" + accountRank +
                ", accountLocation='" + accountLocation + '\'' +
                '}';
    }
}
