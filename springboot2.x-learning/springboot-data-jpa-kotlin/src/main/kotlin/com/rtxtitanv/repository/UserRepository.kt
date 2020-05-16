package com.rtxtitanv.repository

import com.rtxtitanv.model.User
import com.rtxtitanv.projections.NameOnly
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional


/**
 * @name com.rtxtitanv.repository.UserRepository
 * @description UserRepository接口用于操作用户表，JpaRepository<实体类类型, 主键类型>：用于完成基本CRUD操作
 * JpaSpecificationExecutor<实体类类型>：用于复杂查询
 * @author rtxtitanv
 * @date 2020/1/17 16:54
 * @version v1.0.0
 */
interface UserRepository : JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    /**
     * 方法命名规则之按昵称查询
     * @param nickName 呢称 ? = null 这里表示声明参数为Nullable，即该参数允许为null且不显式传参时该参数默认为null
     * @return 查询结果集
     */
    fun findByNickName(nickName: String? = null): List<User>

    /**
     * 方法命名规则之按昵称和年龄查询
     * @param nickName 昵称
     * @param age 年龄
     * @return 查询结果集
     */
    fun findByNickNameAndAge(nickName: String? = null, age: Int? = null): List<User>

    /**
     * 方法命名规则之按昵称模糊查询
     * @param nickName 昵称
     * @return 查询结果集
     */
    fun findByNickNameLike(nickName: String): List<User>

    /**
     * 方法命名规则之按年龄条件统计
     * @param age 年龄
     * @return 小于等于给定年龄的记录总数
     */
    fun countByAgeLessThanEqual(age: Int): Long

    /**
     * 方法命名规则之按邮箱模糊查询名称并按年龄排序
     * 只查询用户名和昵称
     * @param email 邮箱
     * @return List<NameOnly> 名称列表（projections接口NameOnly只包含用户名和昵称）
     */
    fun findByEmailContainingOrderByAgeAsc(email: String): List<NameOnly>

    /**
     * 方法命名规则之限制查询
     * @return 查询结果
     */
    fun findFirstByOrderByAgeAsc(): User?
    fun findTopByOrderByAgeAsc(): User?
    fun queryFirst10ByNickName(nickName: String, pageable: Pageable): Page<User>
    fun findFirst10ByNickName(nickName: String, sort: Sort): List<User>
    fun findTop10ByNickName(nickName: String, pageable: Pageable): Page<User>

    /**
     * 方法命名规则之按昵称（忽略大小写）删除
     * @param nickName 昵称
     * @return 删除的记录数
     */
    //@Transactional：开启事务支持
    @Transactional(rollbackFor = [Exception::class])
    fun deleteByNickNameIgnoreCase(nickName: String? = null): Int

    /**
     * 方法命名规则之按年龄批量删除
     * @param ages 年龄列表
     * @return 删除的记录数
     */
    @Transactional(rollbackFor = [Exception::class])
    fun deleteByAgeIn(ages: List<Int>): Int

    /**
     * 使用sql语句插入一条记录
     * @param uid uid
     * @param userName 用户名
     * @param passWord 密码
     * @param nickName 昵称
     * @param age 年龄
     * @param email 邮箱
     * @param tel 手机
     * @param regTime 注册时间
     * @return 返回1表示插入成功
     */
    //nativeQuery：是否使用本地sql，true表示使用本地sql，缺省默认值为false，不使用本地sql
    //sql语句中的?占位符后的数字对应方法中的参数索引，从1开始
    @Query(value = "insert into user(uid,user_name,pass_word,nick_name,age,email,tel,reg_time)" +
            " values(?1,?2,?3,?4,?5,?6,?7,?8)", nativeQuery = true)
    //@Modifying：该注解标识该操作为一个插入更新删除操作，框架最终不会生成一个查询操作
    @Modifying
    @Transactional(rollbackFor = [Exception::class])
    fun insertUser(uid: String? = null, userName: String? = null, passWord: String? = null, nickName: String? = null, age: Int? = null, email: String? = null, tel: String? = null, regTime: String? = null): Int

    /**
     * 使用JPQL语句按年龄批量查询名称并排序
     * 只查询用户名和昵称
     * @param ageList 年龄列表
     * @param sort 排序参数
     * @return List<NameOnly> 名称列表（projections接口NameOnly只包含用户名和昵称）
     */
    //:为占位符，#{#Object} SpEL表达式将对象传递进sql语句
    //此处有坑，注意一定要加别名，并且与实体类的实例域名一致，否则NameOnly实现中封装不进数据，查出来的值为null
    @Query("select u.userName as userName, u.nickName as nickName from User u where u.age in :#{#ageList}")
    fun findNameByAgeIn(ageList: List<Int>? = null, sort: Sort? = null): List<NameOnly>


    /**
     * 使用sql语句按用户名和昵称模糊分页查询名称
     * 只查询用户名和昵称
     * @param nickName 昵称
     * @param userName 用户名
     * @param pageable 分页参数
     * @return List<NameOnly> 名称列表（projections接口NameOnly只包含用户名和昵称）
     */
    //@Param注解的值与:占位符后的字符串一致，用于参数传递
    @Query(value = "select u.nick_name as nickName, u.user_name as userName from user u where u.nick_name like %:nickname% and u.user_name like %:username%",
            countQuery = "select count(u.nick_name) from user u where u.nick_name like %:nickname% and u.user_name like %:username%", nativeQuery = true)
    fun findNameByNickNameAndUserNameLike(@Param("nickname") nickName: String? = null, @Param("username") userName: String? = null, pageable: Pageable? = null): Page<NameOnly>

    /**
     * 使用JPQL语句根据年龄更新昵称
     * @param nickName 昵称
     * @param age 年龄
     * @return 更新的记录数
     */
    @Query("update User u set u.nickName = ?1 where u.age = ?2")
    @Modifying
    @Transactional(rollbackFor = [Exception::class])
    fun updateUserNameByAge(nickName: String? = null, age: Int? = null): Int

    /**
     * 使用sql语句更新单条记录
     * @param user 用户对象
     * @return
     */
    //使用SpEL表达式传递对象属性至sql语句
    @Query(value = "update user u set u.uid = :#{#user.uid}, u.user_name = :#{#user.userName}," +
            " u.pass_word = :#{#user.passWord}, u.nick_name = :#{#user.nickName}," +
            " u.email = :#{#user.email}, u.age = :#{#user.age}," +
            " u.tel = :#{#user.tel}, u.reg_time = :#{#user.regTime} where u.id = :#{#user.id}", nativeQuery = true)
    @Modifying
    @Transactional(rollbackFor = [Exception::class])
    fun updateUser(@Param("user") user: User): Int

    /**
     * 使用JPQL语句根据id批量删除
     * @param ids
     * @return 删除记录数
     */
    //#{#entityName} 默认为实体类的名称，如果使用了@Entity(name = "xxx")来注解实体类
    //#{#entityName}的值为xxx
    @Query("delete from #{#entityName} u where u.id in ?1")
    @Modifying
    @Transactional(rollbackFor = [Exception::class])
    fun deleteInBacthById(ids: List<Long>? = null): Int

}