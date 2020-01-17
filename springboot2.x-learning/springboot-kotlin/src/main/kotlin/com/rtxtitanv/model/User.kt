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
data class User(var id: Long? = null,//?表示声明为Nullable变量
                var userName: String? = null,
                var passWord: String? = null,
                var nickName: String? = null,
                var age: Int? = null,
                var tel: String? = null,
                var addr: String? = null)