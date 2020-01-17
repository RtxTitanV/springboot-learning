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
data class User(var id: Long,
                var userName: String,
                var passWord: String,
                var nickName: String,
                var age: Int,
                var tel: String,
                var addr: String)