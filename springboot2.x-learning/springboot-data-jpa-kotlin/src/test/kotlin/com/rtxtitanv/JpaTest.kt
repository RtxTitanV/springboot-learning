package com.rtxtitanv

import com.mysql.cj.util.StringUtils
import com.rtxtitanv.model.*
import com.rtxtitanv.model.query.UserQuery
import com.rtxtitanv.repository.*
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import java.text.SimpleDateFormat
import java.util.*


/**
 * @name com.rtxtitanv.JpaTest
 * @description SpringDataJpa单元测试类
 * @author rtxtitanv
 * @date 2020/1/17 17:49
 * @version v1.0.0
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [JpaApplication::class])
class JpaTest {

    //单元测试类不能通过构造函数注入,使用@Autowired注入时需声明属性为lateinit
    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var accountRepository: AccountRepository
    @Autowired
    private lateinit var orderRepository: OrderRepository
    @Autowired
    private lateinit var roleRepository: RoleRepository
    @Autowired
    private lateinit var menuRepository: MenuRepository
    private val logger: Logger = LoggerFactory.getLogger(JpaTest::class.java)

    /**
     * 保存5条测试数据,一次插入一条数据
     * 使用方法 <S extends T> S save(S var1)
     * 实体中主键不存在时保存记录
     */
    @Test
    fun saveTest() {
        val date = Date()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val formattedDate = dateFormat.format(date)
        logger.info("保存5条测试数据开始")
        userRepository.save(User(null, "296ebeb46acd49aca54f0d5a5a1257c3", "qwer123", "123456", "aaa", 24,
                "qwer123@ss.com", "13915176512", formattedDate, "beijing"))
        userRepository.save(User(null, "e6c133e338bb4b7c857be76104986acb", "asd6666", "qw23ss", "asc", 18,
                "asd6666@ss.com", "15736226963", formattedDate, "tianjin"))
        userRepository.save(User(null, "179a91c205f84416b39347d714516c95", "tgh3er2", "11111", "r123er", 22,
                "advx@ss.com", "18956929863", formattedDate, "hangzhou"))
        userRepository.save(User(null, "dddfa7b84b194ea5a62393ef8f211710", "erfgyhn", "567809a.", "rw23ed", 27,
                "ddfty@ss.com", "16389562477", formattedDate, "shanghai"))
        userRepository.save(User(null, "7fc652d011e8448e99aee948f1af9187", "rty7ui81", "a2ef56.a", "asc", 18,
                "sdrtyhui@ss.com", "15966358996", formattedDate, "nanjing"))
        logger.info("保存5条测试数据结束")
    }

    /**
     * 批量保存5条测试数据 后台执行时仍是一条一条插入
     * 使用方法 <S extends T> List<S> saveAll(Iterable<S> var1)
     * 实体中主键不存在时保存记录
     */
    @Test
    fun saveInBatchTest() {
        val date = Date()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val formattedDate = dateFormat.format(date)
        val user1 = User(null, "8960a15b37d0552aa84483e24fc57b80", "23erg", "2234rf", "aaa", 15,
                "qadfgyty@ss.com", "16895635789", formattedDate, "beijing")
        val user2 = User(null, "cc0106b175a6572e8d2967b3dd563193", "63serf", "2ww5t", "w323rrt", 36,
                "wer33@ss.com", "15766958245", formattedDate, "suzhou")
        val user3 = User(null, "7669890a99c1581483edf72fa48d702c", "2w4r", "3345tt", "aaa", 24,
                "qert23@ss.com", "19725689756", formattedDate, "wuhan")
        val user4 = User(null, "9a512c6ffe01565abb619e1199002603", "12er", "134rty", "aa23e54", 23,
                "qwer5@ss.com", "13858963144", formattedDate, "jinan")
        val user5 = User(null, "f7e05429074b5db9a85d623377475ced", "yu2sd", "1w0978", "asc", 31,
                "wer123@ss.com", "18741569832", formattedDate, "xian")
        val list = listOf(user1, user2, user3, user4, user5)
        logger.info("批量保存5条测试数据开始")
        userRepository.saveAll(list)
        logger.info("批量保存5条测试数据结束")
    }

    /**
     * 修改单条记录
     * 使用方法 <S extends T> S save(S var1)
     * 当实体中主键存在时先根据主键查询再根据主键更新
     */
    @Test
    fun updateOneTest() {
        val userOptional = userRepository.findById(3L)
        if (!userOptional.isPresent) {
            logger.info("该用户不存在")
        } else {
            val user = userOptional.get()
            //$符号,字符串模板写法,将变量插入字符串
            logger.info("修改前的记录: $user")
            logger.info("修改一条用户记录开始")
            user.nickName = "6wer23a"
            user.passWord = "123123"
            userRepository.save(user)
            logger.info("修改一条用户记录结束")
            logger.info("修改后的记录: " + userRepository.findById(3L).get().toString())
        }
    }

    /**
     * 批量修改
     * 使用方法 <S extends T> List<S> saveAll(Iterable<S> var1)
     * 当实体中主键存在时先根据主键查询再根据主键更新
     */
    @Test
    fun updateInBatchTest() {
        val ids = listOf(1L, 3L, 5L)
        val userList = userRepository.findAllById(ids)
        if (userList.isEmpty()) {
            logger.info("查询不到记录")
        } else {
            logger.info("修改前的记录")
            userList.forEach { user -> logger.info(user.toString()) }
            logger.info("------------分割线-------------")
            userList.forEach { user -> user.passWord = "666666" }
            logger.info("批量修改开始")
            userRepository.saveAll(userList)
            logger.info("批量修改结束")
            logger.info("修改后的记录")
            userRepository.findAllById(ids).forEach { user -> logger.info(user.toString()) }
        }
    }

    /**
     * 根据id删除
     * 使用方法 void deleteById(ID var1)
     */
    @Test
    fun deleteByIdTest() {
        logger.info("根据id删除开始")
        userRepository.deleteById(3L)
        logger.info("根据id删除结束")
    }

    /**
     * 删除单条记录
     * 使用方法 void delete(T var1)
     */
    @Test
    fun deleteOneTest() {
        val userOptional = userRepository.findById(1L)
        if (!userOptional.isPresent) {
            logger.info("该用户不存在")
        } else {
            val user = userOptional.get()
            logger.info("删除一条用户记录开始")
            userRepository.delete(user)
            logger.info("删除一条用户记录结束")
        }
    }

    /**
     * 批量删除
     * 使用方法 void deleteInBatch(Iterable<T> var1)
     * 后台执行时生成一条sql语句删除
     */
    @Test
    fun deleteInBatch() {
        val ids = listOf(2L, 4L, 5L, 7L)
        val userList = userRepository.findAllById(ids)
        if (userList.isEmpty()) {
            logger.info("用户数据不存在")
        } else {
            logger.info("批量删除开始")
            userRepository.deleteInBatch(userList)
            logger.info("批量删除结束")
        }
    }

    /**
     * 删除所有记录
     * 使用方法 void deleteAllInBatch()
     * 后台执行时生成一条sql语句删除
     */
    @Test
    fun deleteAllTest() {
        logger.info("删除所有开始")
        userRepository.deleteAllInBatch()
        logger.info("删除所有结束")
    }

    /**
     * 查询所有记录
     * 使用方法 List<T> findAll();
     */
    @Test
    fun findAllTest() {
        logger.info("查询所有开始")
        val userList = userRepository.findAll()
        if (userList.isEmpty()) {
            logger.info("不存在用户数据")
        } else {
            userList.forEach { user -> logger.info(user.toString()) }
        }
        logger.info("查询所有结束")
    }

    /**
     * 查询所有记录并排序
     * 使用方法 List<T> findAll(Sort var1)
     */
    @Test
    fun findAllAndSortTest() {
        val sort = Sort.by(Sort.Direction.DESC, "age")
        logger.info("查询所有并按年龄降序排序开始")
        val userList = userRepository.findAll(sort)
        if (userList.isEmpty()) {
            logger.info("不存在用户数据")
        } else {
            userList.forEach { user -> logger.info(user.toString()) }
        }
        logger.info("查询所有并按年龄降序排序结束")
    }

    /**
     * 分页查询所有记录
     * 使用方法 Page<T> findAll(Pageable var1)
     */
    @Test
    fun findAllAndPageTest() {
        val sort = Sort.by(Sort.Direction.ASC, "age")
        val pageable: Pageable = PageRequest.of(2, 3, sort)
        logger.info("分页查询所有开始")
        val page = userRepository.findAll(pageable)
        if (page.isEmpty) {
            logger.info("不存在分页数据")
        } else {
            logger.info("总条数：" + page.totalElements)
            logger.info("总页数：" + page.totalPages)
            val userList = page.content
            userList.forEach { user -> logger.info(user.toString()) }
        }
        logger.info("分页查询所有结束")
    }

    /**
     * 根据id查询
     * 使用方法 Optional<T> findById(ID var1)
     */
    @Test
    fun findByIdTest() {
        logger.info("根据id查询开始")
        val userOptional = userRepository.findById(1L)
        if (!userOptional.isPresent) {
            logger.info("查询的用户不存在")
        } else {
            val user = userOptional.get()
            val userInfo = user.toString()
            logger.info(userInfo)
        }
        logger.info("根据id查询结束")
    }

    /**
     * 根据id批量查询
     * 使用方法 List<T> findAllById(Iterable<ID> var1)
     */
    @Test
    fun findInBatchByIdTest() {
        val ids = listOf(1L, 3L, 5L)
        logger.info("根据id批量查询开始")
        val userList = userRepository.findAllById(ids)
        if (userList.isEmpty()) {
            logger.info("查询的用户不存在")
        } else {
            userList.forEach { user -> logger.info(user.toString()) }
        }
        logger.info("根据id批量查询结束")
    }

    /**
     * 方法命名规则之按昵称查询
     */
    @Test
    fun findByNickNameTest() {
        logger.info("方法命名规则查询之按昵称查询开始")
        val userList = userRepository.findByNickName("aaa")
        if (userList.isEmpty()) {
            logger.info("昵称为aaa的用户不存在")
        } else {
            userList.forEach { user -> logger.info(user.toString()) }
        }
        logger.info("方法命名规则查询之按昵称查询结束")
    }

    /**
     * 方法命名规则之按昵称和年龄查询
     */
    @Test
    fun findByNickNameAndAgeTest() {
        logger.info("方法命名规则之按昵称和年龄查询开始")
        val userList = userRepository.findByNickNameAndAge("asc", 18)
        if (userList.isEmpty()) {
            logger.info("昵称为asc并且年龄为18的用户不存在")
        } else {
            userList.forEach { user -> logger.info(user.toString()) }
        }
        logger.info("方法命名规则之按昵称和年龄查询结束")
    }

    /**
     * 方法命名规则之按昵称模糊查询
     */
    @Test
    fun findByNickNameLikeTest() {
        logger.info("方法命名规则之按昵称模糊查询开始")
        val userList = userRepository.findByNickNameLike("%23%")
        if (userList.isEmpty()) {
            logger.info("昵称包含23的用户不存在")
        } else {
            userList.forEach { user -> logger.info(user.toString()) }
        }
        logger.info("方法命名规则之按昵称模糊查询结束")
    }

    /**
     * 方法命名规则之按年龄条件统计
     */
    @Test
    fun countByAgeLessThanEqualTest() {
        logger.info("方法命名规则之按年龄条件统计开始")
        val count = userRepository.countByAgeLessThanEqual(24)
        logger.info("年龄不超过24岁的用户总数: $count")
        logger.info("方法命名规则之按年龄条件统计结束")
    }

    /**
     * 方法命名规则之按邮箱模糊查询名称并按年龄升序
     */
    @Test
    fun findByEmailContainingOrderByAgeTest() {
        logger.info("方法命名规则之按邮箱模糊查询名称并按年龄升序开始")
        val nameOnly = userRepository.findByEmailContainingOrderByAgeAsc("er")
        if (nameOnly.isEmpty()) {
            logger.info("不存在满足条件记录")
        } else {
            nameOnly.forEach { name -> logger.info("userName: " + name.getUserName() + ", nickName: " + name.getNickName()) }
        }
        logger.info("方法命名规则之按邮箱模糊查询名称并按年龄升序结束")
    }

    /**
     * 方法命名规则之限制查询
     */
    @Test
    fun findFirstByOrderByAgeAscTest() {
        logger.info("方法命名规则之限制查询开始")
        val user = userRepository.findFirstByOrderByAgeAsc()
        if (user == null) {
            logger.info("用户数据不存在")
        } else {
            val userInfo = user.toString()
            logger.info(userInfo)
        }
        logger.info("方法命名规则之限制查询结束")
    }

    /**
     * 方法命名规则之按昵称(忽略大小写)删除
     */
    @Test
    fun deleteByNickNameIgnoreCaseTest() {
        logger.info("方法命名规则之按昵称删除开始")
        val result = userRepository.deleteByNickNameIgnoreCase("AAa")
        logger.info("总共删除了$result" + "条记录")
        logger.info("方法命名规则之按昵称删除结束")
    }

    /**
     * 方法命名规则之按年龄批量删除
     */
    @Test
    fun deleteByAgeInTest() {
        val ages = listOf(18, 23, 30)
        logger.info("方法命名规则之按年龄批量删除开始")
        val result = userRepository.deleteByAgeIn(ages)
        logger.info("总共删除了$result 条记录")
        logger.info("方法命名规则之按年龄批量删除结束")
    }

    /**
     * 使用sql语句插入一条记录
     */
    @Test
    fun insertUserTest() {
        logger.info("sql插入一条记录开始")
        val result = userRepository.insertUser("f0ff89db-aa72-55dc-aaba-e1ec11fa2fec", "dfgyytvb2", "123456", "rty235", 18, "miopl@ss.com", "1256698463", "2020-01-02 11:51:16")
        logger.info("sql插入一条记录结束")
        if (result == 1) {
            logger.info("插入成功")
        } else {
            logger.info("插入失败")
        }
    }

    /**
     * 使用JPQL语句按年龄批量查询名称并排序
     */
    @Test
    fun findNameByAgeInTest() {
        val sort = Sort.by("userName").descending()
        val ages = listOf(18, 24, 27)
        logger.info("JPQL按年龄批量查询名称并按用户名降序开始")
        val names = userRepository.findNameByAgeIn(ages, sort)
        if (names.isEmpty()) {
            logger.info("满足条件记录不存在")
        } else {
            names.forEach { name -> logger.info("userName: " + name.getUserName() + ", nickName: " + name.getNickName()) }
        }
        logger.info("JPQL按年龄批量查询名称并按用户名降序结束")
    }

    /**
     * 使用sql语句按用户名和昵称模糊分页查询名称
     */
    @Test
    fun findNameByNickNameAndUserNameLikeTest() {
        val pageable: Pageable = PageRequest.of(1, 2)
        logger.info("sql语句按用户名和昵称模糊分页查询名称开始")
        val names = userRepository.findNameByNickNameAndUserNameLike("a", "r", pageable)
        if (names.isEmpty) {
            logger.info("满足条件的查询记录不存在")
        } else {
            logger.info("总条数: ${names.totalElements}")
            logger.info("总页数: ${names.totalPages}")
            names.content.forEach { name -> logger.info("userName: " + name.getUserName() + ", nickName: " + name.getNickName()) }
        }
        logger.info("sql语句按用户名和昵称模糊分页查询名称结束")
    }

    /**
     * 使用JPQL语句根据年龄更新昵称
     */
    @Test
    fun updateUserNameByAgeTest() {
        logger.info("JPQL根据年龄更新昵称开始")
        val result = userRepository.updateUserNameByAge("nickname-01", 18)
        logger.info("更新了$result" + "条记录")
        logger.info("JPQL根据年龄更新昵称结束")
    }

    /**
     * 使用sql语句更新单条记录
     */
    @Test
    fun updateUserTest() {
        val userOptional = userRepository.findById(1L)
        if (!userOptional.isPresent) {
            logger.info("查询用户不存在")
        } else {
            val user = userOptional.get()
            user.passWord = "6543215622"
            user.nickName = "ava33"
            logger.info("sql语句更新单条记录开始")
            userRepository.updateUser(user)
            logger.info("sql语句更新单条记录结束")
        }
    }

    /**
     * 使用JPQL语句根据id批量删除
     */
    @Test
    fun deleteInBacthByIdTest() {
        val ids = listOf(1L, 3L, 6L, 9L)
        logger.info("使用JPQL语句根据id批量删除开始")
        val result = userRepository.deleteInBacthById(ids)
        logger.info("总共删除了$result" + "条记录")
        logger.info("使用JPQL语句根据id批量删除结束")
    }

    /**
     * JpaSpecificationExecutor单条件查询测试
     */
    @Test
    fun findByOneConditionTest() {
        logger.info("单条件查询测试开始")
        /**
         * root:查询的根对象,可以通过get方法获取实体属性
         * criteriaQuery:代表一个顶层查询对象,可以构建自定义查询,包含select,where,orderBy,groupBy等
         * criteriaBuilder:查询条件构造器
         */
        val userList = userRepository.findAll { root, criteriaQuery, criteriaBuilder ->
            //user_name = "qwer123"
            val predicate1 = criteriaBuilder.equal(root.get<String>("userName"), "qwer123")
            //email like %er%
            val predicate2 = criteriaBuilder.like(root.get<String>("email"), "%er%")
            //age between 15 and 25
            val predicate3 = criteriaBuilder.between(root.get<Int>("age"), 15, 25)
            //age >= 18
            val predicate4 = criteriaBuilder.ge(root.get<Int>("age"), 18)
            //age <= 25
            val predicate5 = criteriaBuilder.le(root.get<Int>("age"), 25)
            //从lambda表达式中返回一个值 等价于 return@findAll predicate1
            predicate5
        }
        if (userList.isEmpty()) {
            logger.info("没有满足条件的数据")
        } else {
            userList.forEach { user -> logger.info(user.toString()) }
        }
        logger.info("单条件查询测试结束")
    }

    /**
     * JpaSpecificationExecutor动态拼接多条件查询测试
     * 拼接方式1:每个条件均为and连接
     * 实际开发中可以根据实际的动态条件灵活处理
     */
    @Test
    fun findByConditionsTest() {
        //手动模拟查询条件
//        val userQuery : UserQuery? = null
        val userQuery = UserQuery()
        val ids = listOf(1L, 2L, 5L)
        val ages = listOf(18, 24)
        userQuery.userNameQuery = "r"
        userQuery.nickNameQuery = "a"
        userQuery.minAgeQuery = null
        userQuery.maxAgeQuery = 25
        userQuery.idQuery = null
        userQuery.telQuery = "135"
        userQuery.emailQuery = "rt"
        userQuery.passWordQuery = "123"
        userQuery.idsQuery = ids
        userQuery.agesQuery = ages
        logger.info("动态拼接多条件查询测试开始")
        val userList = userRepository.findAll { root, criteriaQuery, criteriaBuilder ->
            //如果userQuery为空或者userQuery所有属性均为空会自动生成where 1 = 1
            val predicate = criteriaBuilder.conjunction()
            //如果userQuery为空或者userQuery所有属性均为空会查询所有记录
            if (userQuery != null) {
                if (userQuery.idQuery != null) {
                    //notEqual查询 !!:非空断言运算符,将任何值转换为非空类型,若该值为空则抛出异常
                    predicate.expressions.add(criteriaBuilder.notEqual(root.get<Long>("id"), userQuery.idQuery!!))
                }
                if (!StringUtils.isNullOrEmpty(userQuery.userNameQuery)) {
                    //like查询
                    predicate.expressions.add(criteriaBuilder.like(root.get<String>("userName"), "%${userQuery.userNameQuery}%"))
                }
                if (!StringUtils.isNullOrEmpty(userQuery.nickNameQuery)) {
                    //notLike查询
                    predicate.expressions.add(criteriaBuilder.notLike(root.get<String>("nickName"), "%${userQuery.nickNameQuery}%"))
                }
                if (!StringUtils.isNullOrEmpty(userQuery.passWordQuery)) {
                    //equal查询
                    predicate.expressions.add(criteriaBuilder.equal(root.get<String>("passWord"), userQuery.passWordQuery))
                }
                if (!StringUtils.isNullOrEmpty(userQuery.emailQuery)) {
                    //notEqual查询
                    predicate.expressions.add(criteriaBuilder.notEqual(root.get<String>("email"), userQuery.emailQuery))
                }
                if (!StringUtils.isNullOrEmpty(userQuery.telQuery)) {
                    //like查询
                    predicate.expressions.add(criteriaBuilder.like(root.get<String>("tel"), "%${userQuery.telQuery}"))
                }
                if (userQuery.minAgeQuery != null && userQuery.maxAgeQuery != null) {
                    //between查询
                    predicate.expressions.add(criteriaBuilder.between(root.get<Int>("age"), userQuery.minAgeQuery!!, userQuery.maxAgeQuery!!))
                } else if (userQuery.minAgeQuery != null) {
                    //>=查询 gt为>
                    predicate.expressions.add(criteriaBuilder.ge(root.get<Int>("age"), userQuery.minAgeQuery!!))
                } else if (userQuery.maxAgeQuery != null) {
                    //<=查询 lt为<
                    predicate.expressions.add(criteriaBuilder.le(root.get<Int>("age"), userQuery.maxAgeQuery!!))
                }
                if (userQuery.idsQuery != null && !userQuery.idsQuery!!.isEmpty()) {
                    //in 批量查询 `in`:in在Kotlin为关键字用反引号转义
                    predicate.expressions.add(criteriaBuilder.and(root.get<Long>("id").`in`(userQuery.idsQuery!!)))
                }
                if (userQuery.agesQuery != null && !userQuery.agesQuery!!.isEmpty()) {
                    predicate.expressions.add(criteriaBuilder.and(root.get<Int>("age").`in`(userQuery.agesQuery!!)))
                }
            }
            predicate
        }
        if (userList.isEmpty()) {
            logger.info("查询不到满足条件的数据")
        } else {
            userList.forEach { user -> logger.info(user.toString()) }
        }
        logger.info("动态拼接多条件查询测试结束")
    }

    /**
     * JpaSpecificationExecutor动态拼接多条件查询测试
     * 拼接方式2:与方式1效果相同
     */
    @Test
    fun findByConditionsTest2() {
        //手动模拟查询条件
//        val userQuery : UserQuery? = null
        val userQuery = UserQuery()
        val ids = listOf(1L, 2L, 5L)
        val ages = listOf(18, 24)
        userQuery.userNameQuery = "r"
        userQuery.nickNameQuery = "a"
        userQuery.minAgeQuery = null
        userQuery.maxAgeQuery = 25
        userQuery.idQuery = 6L
        userQuery.telQuery = "135"
        userQuery.emailQuery = "rt"
        userQuery.passWordQuery = "123"
        userQuery.idsQuery = ids
        userQuery.agesQuery = ages
        logger.info("动态拼接多条件查询测试开始")
        val userList = userRepository.findAll { root, criteriaQuery, criteriaBuilder ->
            //sql语句会自动生成where 1 = 1
            val predicates = mutableListOf(criteriaBuilder.conjunction())
            //如果userQuery为空或者userQuery所有属性均为空会查询所有记录
            if (userQuery != null) {
                if (userQuery.idQuery != null) {
                    predicates.add(criteriaBuilder.notEqual(root.get<Long>("id"), userQuery.idQuery!!))
                }
                if (!StringUtils.isNullOrEmpty(userQuery.userNameQuery)) {
                    predicates.add(criteriaBuilder.like(root.get<String>("userName"), "%${userQuery.userNameQuery}%"))
                }
                if (!StringUtils.isNullOrEmpty(userQuery.nickNameQuery)) {
                    predicates.add(criteriaBuilder.notLike(root.get<String>("nickName"), "%${userQuery.nickNameQuery}%"))
                }
                if (!StringUtils.isNullOrEmpty(userQuery.passWordQuery)) {
                    predicates.add(criteriaBuilder.equal(root.get<String>("passWord"), userQuery.passWordQuery))
                }
                if (!StringUtils.isNullOrEmpty(userQuery.emailQuery)) {
                    predicates.add(criteriaBuilder.notEqual(root.get<String>("email"), userQuery.emailQuery))
                }
                if (!StringUtils.isNullOrEmpty(userQuery.telQuery)) {
                    predicates.add(criteriaBuilder.like(root.get<String>("tel"), "%${userQuery.telQuery}"))
                }
                if (userQuery.minAgeQuery != null && userQuery.maxAgeQuery != null) {
                    predicates.add(criteriaBuilder.between(root.get<Int>("age"), userQuery.minAgeQuery!!, userQuery.maxAgeQuery!!))
                } else if (userQuery.minAgeQuery != null) {
                    predicates.add(criteriaBuilder.ge(root.get<Int>("age"), userQuery.minAgeQuery!!))
                } else if (userQuery.maxAgeQuery != null) {
                    predicates.add(criteriaBuilder.le(root.get<Int>("age"), userQuery.maxAgeQuery!!))
                }
                if (userQuery.idsQuery != null && !userQuery.idsQuery!!.isEmpty()) {
                    predicates.add(criteriaBuilder.and(root.get<Long>("id").`in`(userQuery.idsQuery!!)))
                }
                if (userQuery.agesQuery != null && !userQuery.agesQuery!!.isEmpty()) {
                    predicates.add(criteriaBuilder.and(root.get<Int>("age").`in`(userQuery.agesQuery!!)))
                }
            }
            /**
             * Predicate and(Predicate... var1):and连接查询条件,可传入Predicate[] and连接数组内所有条件
             * Predicate or(Predicate... var1):or连接查询条件,可传入Predicate[] or连接数组内所有条件
             * 此处所有条件均为and连接,如果有更复杂的条件连接,比如:
             * where ((a=? and b=? and c=?) or (e=? and f=?) or g=?) and x=? and y=?
             * 先and连接abc,ef,再or连接abc,ef,g作为一个条件z,再and连接xyz
             */
            criteriaBuilder.and(*predicates.toTypedArray())
        }
        if (userList.isEmpty()) {
            logger.info("查询不到满足条件的数据")
        } else {
            userList.forEach { user -> logger.info(user.toString()) }
        }
        logger.info("动态拼接多条件查询测试结束")
    }

    /**
     * 多条件排序分页查询
     */
    @Test
    fun findByConditionsPageAndSortTest() {
        //手动模拟查询条件
//        val userQuery : UserQuery? = null
        val userQuery = UserQuery()
        val ids = listOf(1L, 2L, 5L)
        val ages = listOf(18, 24)
        userQuery.userNameQuery = "r"
        userQuery.nickNameQuery = "a"
        userQuery.minAgeQuery = null
        userQuery.maxAgeQuery = 25
        userQuery.idQuery = null
        userQuery.telQuery = "135"
        userQuery.emailQuery = "rt"
        userQuery.passWordQuery = "123"
        userQuery.idsQuery = ids
        userQuery.agesQuery = ages
        //定义排序规则 先按age降序,再按tel升序,再按id降序
        val orderAge = Sort.Order(Sort.Direction.DESC, "age")
        val orderTel = Sort.Order(Sort.Direction.ASC, "tel")
        val orderId = Sort.Order(Sort.Direction.DESC, "id")
        val sort = Sort.by(orderAge, orderTel, orderId)
        //定义分页参数,由于是测试第几页和每页记录数写死
        val pageNum = 3
        val pageSize = 3
        //jpa分页从0页开始,页码需要-1
        val pageable: Pageable = PageRequest.of(pageNum - 1, pageSize, sort)
        logger.info("多条件排序分页查询测试开始")
        val page = userRepository.findAll({ root, criteriaQuery, criteriaBuilder ->
            //如果userQuery为空或者userQuery所有属性均为空会自动生成where 1 = 1
            val predicate = criteriaBuilder.conjunction()
            //如果userQuery为空或者userQuery所有属性均为空会查询所有记录
            if (userQuery != null) {
                if (userQuery.idQuery != null) {
                    //notEqual查询 !!:非空断言运算符,将任何值转换为非空类型,若该值为空则抛出异常
                    predicate.expressions.add(criteriaBuilder.notEqual(root.get<Long>("id"), userQuery.idQuery!!))
                }
                if (!StringUtils.isNullOrEmpty(userQuery.userNameQuery)) {
                    //like查询
                    predicate.expressions.add(criteriaBuilder.like(root.get<String>("userName"), "%${userQuery.userNameQuery}%"))
                }
                if (!StringUtils.isNullOrEmpty(userQuery.nickNameQuery)) {
                    //notLike查询
                    predicate.expressions.add(criteriaBuilder.notLike(root.get<String>("nickName"), "%${userQuery.nickNameQuery}%"))
                }
                if (!StringUtils.isNullOrEmpty(userQuery.passWordQuery)) {
                    //equal查询
                    predicate.expressions.add(criteriaBuilder.equal(root.get<String>("passWord"), userQuery.passWordQuery))
                }
                if (!StringUtils.isNullOrEmpty(userQuery.emailQuery)) {
                    //notEqual查询
                    predicate.expressions.add(criteriaBuilder.notEqual(root.get<String>("email"), userQuery.emailQuery))
                }
                if (!StringUtils.isNullOrEmpty(userQuery.telQuery)) {
                    //like查询
                    predicate.expressions.add(criteriaBuilder.like(root.get<String>("tel"), "%${userQuery.telQuery}"))
                }
                if (userQuery.minAgeQuery != null && userQuery.maxAgeQuery != null) {
                    //between查询
                    predicate.expressions.add(criteriaBuilder.between(root.get<Int>("age"), userQuery.minAgeQuery!!, userQuery.maxAgeQuery!!))
                } else if (userQuery.minAgeQuery != null) {
                    //>=查询 gt为>
                    predicate.expressions.add(criteriaBuilder.ge(root.get<Int>("age"), userQuery.minAgeQuery!!))
                } else if (userQuery.maxAgeQuery != null) {
                    //<=查询 lt为<
                    predicate.expressions.add(criteriaBuilder.le(root.get<Int>("age"), userQuery.maxAgeQuery!!))
                }
                if (userQuery.idsQuery != null && !userQuery.idsQuery!!.isEmpty()) {
                    //in 批量查询 `in`:in在Kotlin为关键字用反引号转义
                    predicate.expressions.add(criteriaBuilder.and(root.get<Long>("id").`in`(userQuery.idsQuery!!)))
                }
                if (userQuery.agesQuery != null && !userQuery.agesQuery!!.isEmpty()) {
                    predicate.expressions.add(criteriaBuilder.and(root.get<Int>("age").`in`(userQuery.agesQuery!!)))
                }
            }
            predicate
        }, pageable)
        if (page.isEmpty) {
            logger.info("查询不到满足条件的数据")
        } else {
            logger.info("总条数：${page.totalElements}")
            logger.info("总页数：${page.totalPages}")
            page.content.forEach { user -> logger.info(user.toString()) }
        }
        logger.info("多条件排序分页查询测试结束")
    }

    /**
     * 多表关联一对多保存
     */
    @Transactional //开启事务支持
    @Rollback(false) //设置不回滚
    @Test
    fun oneToManySaveTest() {
        //封装保存数据
        val account = Account()
        account.accountName = "rtx_titan_v"
        account.accountPassword = "123456"
        account.accountAlias = "cdf_dv"
        account.accountRank = 7L
        account.accountTel = "13313313311"
        account.accountLocation = "china"
        account.accountAddr = "北京西城区"
        val order1 = Order()
        val order2 = Order()
        val order3 = Order()
        order1.orderName = "索尼ps5次世代游戏主机"
        order1.orderDescription = "索尼ps5,无索不玩"
        order1.orderStatus = "等待卖家发货"
        order1.orderTotalPrice = "5000"
        order1.orderItemCount = 1
        order1.orderAddr = "北京西城区"
        order2.orderName = "XBox Edit 2代"
        order2.orderDescription = "微软精英2代,无线蓝牙手柄国"
        order2.orderStatus = "卖家已发货"
        order2.orderTotalPrice = "1390"
        order2.orderItemCount = 1
        order2.orderAddr = "北京西城区"
        order3.orderName = "XBox Edit 3代"
        order3.orderDescription = "微软精英3代,无线蓝牙手柄国行"
        order3.orderStatus = "卖家已发货"
        order3.orderTotalPrice = "1390"
        order3.orderItemCount = 1
        order3.orderAddr = "北京西城区"
        //关联操作
        account.orders.add(order1)
        account.orders.add(order2)
        order1.account = account
        order2.account = account
        //保存操作
        //由于account和order实体设置了级联保存
        //此处任意保存其中一个order,后台会自动保存order1,order2和关联的account
        logger.info("保存开始")
        orderRepository.save(order1)
        orderRepository.save(order2)
        //保存account,会自动保存关联的order1,order2
        accountRepository.save(account)
        //此处为account关联order1,和order2保存之后再关联一个新的order3保存
        account.orders.add(order3)
        order3.account = account
        orderRepository.save(order3)
        logger.info("保存结束")
    }

    /**
     * 多表关联一对多查询
     */
    //解决延迟加载时Session已关闭出现的LazyInitializationException
    @Transactional
    @Rollback(false)
    @Test
    fun oneToManyFindTest() {
        //查询一个账户,并获取账户的所有订单
        val accountOptional = accountRepository.findById(1L)
        if (!accountOptional.isPresent) {
            logger.info("账户不存在")
        } else {
            val account = accountOptional.get()
            logger.info("----------------账户信息----------------")
            val accountInf = account.toString()
            logger.info(accountInf)
            logger.info("----------------订单信息----------------")
            account.orders.forEach { order -> logger.info(order.toString()) }
        }
        //查询一个订单,并获取订单所对应的账户
        val orderOptional = orderRepository.findById(1L)
        if (!orderOptional.isPresent) {
            logger.info("订单不存在")
        } else {
            val order = orderOptional.get()
            logger.info("----------------订单信息----------------")
            val orderInfo = order.toString()
            logger.info(orderInfo)
            logger.info("----------------账户信息----------------")
            val accountInfo = order.account.toString()
            logger.info(accountInfo)
        }
    }

    /**
     * 多表关联一对多更新
     */
    @Transactional
    @Rollback(false)
    @Test
    fun oneToManyUpdateTest() {
        //通过一方更新多方的记录
        val accountOpt = accountRepository.findById(1L)
        if (!accountOpt.isPresent) {
            logger.info("账号不存在")
        } else {
            accountOpt.get().orders.forEach { order -> kotlin.run {
                if (order.orderId == 1L) {
                    order.orderTotalPrice = "1590"
                    order.orderAddr = "重庆渝北区"
                }
            } }
        }
        //通过多方更新一方记录
        val orderOpt = orderRepository.findById(1L)
        if (!orderOpt.isPresent) {
            logger.info("订单不存在")
        } else {
            orderOpt.get().account!!.accountAddr = "重庆江北区"
            orderOpt.get().account!!.accountRank = 8L
        }
    }

    /**
     * 多表关联一对多删除
     */
    @Transactional
    @Rollback(false)
    @Test
    fun oneToManyDeleteTest() {
        //account设置了级联删除,有多方关联时删除account将会同时删除关联的所有order
        //如果account没有设置级联删除,有多方关联删除一方时,默认置外键为null,如果外键不允许
        //为空,会报错,如果配置了放弃维护关联关系则不能删除
        //accountRepository.deleteById(1L)
        //只删除多方记录,直接删除会有问题,删除后再关联查询多方记录时没有生成删除语句
        //orderRepository.deleteById(3L)
        val accountOptional = accountRepository.findById(1L)
        if (!accountOptional.isPresent) {
            logger.info("账号不存在")
        } else {
            val account = accountOptional.get()
            val orders = account.orders
            logger.info("删除开始")
            //可变迭代器(扩展自Iterator的MutableIterator,在迭代时可以删除,插入和替换元素)遍历可变集合
            val mutableIterator = orders.iterator()
            while (mutableIterator.hasNext()) {
                //由于orphanRemoval = true,在一方关联多方的集合中移除多方,将会在多方删除这些记录
                if (mutableIterator.next().orderId == 1L) {
                    mutableIterator.remove()
                }
            }
            accountRepository.save(account)
            logger.info("删除结束")
        }
    }

    /**
     * 多表关联多对多保存
     */
    @Transactional
    @Rollback(false)
    @Test
    fun manyToManySaveTest() {
        val role1 = Role()
        val role2 = Role()
        val role3 = Role()
        role1.roleName = "admin"
        role1.roleType = "A"
        role2.roleName = "user"
        role2.roleType = "U"
        role3.roleName = "system"
        role3.roleType = "S"
        val menu1 = Menu()
        val menu2 = Menu()
        val menu3 = Menu()
        menu1.menuName = "导航栏"
        menu1.menuHidden = false
        menu1.menuPath = "http://ascd/sddf/kk.img"
        menu2.menuName = "下拉框"
        menu2.menuParentId = 1L
        menu2.menuIcon = "下拉图标"
        menu3.menuName = "确认按钮"
        menu3.menuIcon = "方块"
        menu3.menuPath = "http://123.123.44.56/xx"
        //关联操作
        role1.menus.add(menu1)
        role1.menus.add(menu3)
        role2.menus.add(menu2)
        role2.menus.add(menu3)
        role3.menus.add(menu1)
        role3.menus.add(menu2)
        menu1.roles.add(role1)
        menu1.roles.add(role3)
        menu2.roles.add(role2)
        menu2.roles.add(role3)
        menu3.roles.add(role1)
        menu3.roles.add(role2)
        //保存,role和menu均设置了级联保存
        menuRepository.save(menu1)
        //roleRepository.save(role1) 和save menu一样
    }

    /**
     * 多表关联多对多查询
     */
    @Transactional
    @Rollback(false)
    @Test
    fun manyToManyFindTest() {
        //查询一个角色,并获取角色的所有菜单
        val roleOptional = roleRepository.findById(1L)
        if (!roleOptional.isPresent) {
            logger.info("角色不存在")
        } else {
            val role = roleOptional.get()
            logger.info("----------------角色信息----------------")
            val roleInf = role.toString()
            logger.info(roleInf)
            logger.info("----------------菜单信息----------------")
            role.menus.forEach { menu -> logger.info(menu.toString()) }
        }
        //查询一个菜单,并获取菜单的角色
        val menuOptional = menuRepository.findById(1L)
        if (!menuOptional.isPresent) {
            logger.info("菜单不存在")
        } else {
            val menu = menuOptional.get()
            logger.info("----------------菜单信息----------------")
            val menuInfo = menu.toString()
            logger.info(menuInfo)
            logger.info("----------------角色信息----------------")
            menu.roles.forEach { role -> logger.info(role.toString()) }
        }
    }

    /**
     * 多表关联多对多更新
     */
    @Transactional
    @Rollback(false)
    @Test
    fun manyToManyUpdateTest() {
        val roleOptional = roleRepository.findById(1L)
        if (!roleOptional.isPresent) {
            logger.info("角色不存在")
        } else {
            val roles = roleOptional.get()
            //通过id为1的role修改role关联的id为1的menu
            //根据id为1的menu修改menu关联的id为3的role
            roles.menus.forEach { menu -> kotlin.run{
                if (menu.menuId == 1L) {
                    menu.menuHidden = true
                    menu.menuName = "左侧导航栏"
                    menu.roles.forEach { role -> kotlin.run {
                        if (role.roleId == 3L) {
                            role.roleName = "vip"
                            role.roleType = "V"
                        }
                    } }
                }
            } }
        }
    }

    /**
     * 多表关联多对多删除
     */
    @Transactional
    @Rollback(false)
    @Test
    fun manyToManyDeleteTest() {
        //准备删除的role
        val roleOptional = roleRepository.findById(1L)
        if (!roleOptional.isPresent) {
            logger.info("角色不存在")
        } else {
            val role1 = roleOptional.get()
            //关联关系解除(id为1的role-->id为1的menu,id为2的menu)
            //删除中间表的关联记录
            role1.menus.forEach { menu -> kotlin.run {
                menu.roles.remove(role1)
            } }
            //不删除role的情况,重新关联(id为1的role-->id为3的menu)
            /*val menuOptional = menuRepository.findById(3L)
            if (!menuOptional.isPresent) {
                logger.info("菜单不存在")
            } else {
                val menu3 = menuOptional.get()
                role1.menus.add(menu3)
                menu3.roles.add(role1)
                //更新关联,可以省略,省略也会更新中间表关联关系
                menuRepository.save(menu3)
            }*/
            //删除role
            roleRepository.delete(role1)
        }
    }











}

