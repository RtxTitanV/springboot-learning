package com.rtxtitanv.model

import com.rtxtitanv.annotation.KtNoArgsConstructor

/**
 * @name com.rtxtitanv.model.User
 * @description 用户实体类
 * @author rtxtitanv
 * @date 2020/1/16 16:07
 * @version v1.0.0
 */
@KtNoArgsConstructor
class User(private var id: Long,
                private var userName: String,
                private var passWord: String,
                private var nickName: String,
                private var age: Int,
                private var tel: String,
                private var addr: String) {
    fun getId(): Long = id
    fun setId(id: Long) {
        this.id = id
    }
    fun getUserName(): String = userName
    fun setUserName(userName: String) {
        this.userName = userName
    }
    fun getPassWord(): String = passWord
    fun setPassWord(passWord: String) {
        this.passWord = passWord
    }
    fun getNickName(): String = nickName
    fun setNickName(passWord: String) {
        this.nickName = nickName
    }
    fun getAge(): Int = age
    fun setAge(age: Int) {
        this.age = age
    }
    fun getTel(): String = tel
    fun setTel(passWord: String) {
        this.tel = tel
    }
    fun getAddr(): String = addr
    fun setAddr(addr: String) {
        this.addr = addr
    }

    override fun toString(): String {
        return "User(" + "id:" + id +
                " userName:" + userName +
                " passWord:" + passWord +
                " nickName:" + nickName +
                " age:" + age +
                " tel:" + tel +
                " addr:" + addr + ")"
    }
}