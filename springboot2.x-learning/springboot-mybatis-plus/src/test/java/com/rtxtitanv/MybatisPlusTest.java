package com.rtxtitanv;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import com.rtxtitanv.mapper.UserMapper;
import com.rtxtitanv.model.User;
import com.rtxtitanv.model.query.UserQuery;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.MybatisPlusTest
 * @description Mybatis-Plus单元测试类
 * @date 2021/5/16 15:44
 */
@SpringBootTest
class MybatisPlusTest {

    @Resource
    private UserMapper userMapper;
    private static Logger logger = LoggerFactory.getLogger(MybatisPlusTest.class);

    /**
     * 查询所有记录测试
     */
    @Test
    void selectAllTest() {
        List<User> users = userMapper.selectList(null);
        if (users.isEmpty()) {
            logger.info("不存在用户数据");
        } else {
            users.forEach(user -> logger.info(user.toString()));
        }
    }

    /**
     * 根据ID查询测试，通过Mapper中自定义方法查询
     */
    @Test
    void findById() {
        User user = userMapper.findById(3L);
        logger.info(user.toString());
    }

    /**
     * 插入一条记录测试
     * 使用方法 int insert(T entity)
     */
    @Test
    void insertTest() {
        User user = new User();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse("1993-10-16 20:36:10", dateTimeFormatter);
        user.setUsername("kong1ming").setPassword("mkg568er7k").setRealname("诸葛亮").setGender("男").setAge(27)
            .setEmail("kongming@xxx.com").setUserPoint(6000).setUserLevel(Byte.valueOf("5")).setBirthday(localDateTime);
        // 返回的result是受影响的行数，并不是自增后的ID
        int result = userMapper.insert(user);
        logger.info("result: " + result);
        // 自增后的ID会回填到对象中
        logger.info("新增加的一条记录的ID：" + user.getId());
    }

    /**
     * 根据ID删除测试
     * 使用方法 int deleteById(Serializable id)
     */
    @Test
    void deleteByIdTest() {
        int result = userMapper.deleteById(11L);
        logger.info("result: " + result);
    }

    /**
     * 根据ID批量删除测试
     * 使用方法 int deleteBatchIds(@Param("coll") Collection<? extends Serializable> idList)
     */
    @Test
    void deleteByIdsTest() {
        Long[] array = {1L, 3L, 9L};
        List<Long> ids = Arrays.stream(array).collect(Collectors.toList());
        int result = userMapper.deleteBatchIds(ids);
        logger.info("result: " + result);
    }

    /**
     * 根据entity条件删除测试
     * 使用方法 int delete(@Param("ew") Wrapper<T> queryWrapper)
     */
    @Test
    void deletedTest() {
        User user = new User().setGender("女").setAge(22);
        // WHERE gender=? AND age=?
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        int result = userMapper.delete(queryWrapper);
        logger.info("result: " + result);
    }

    /**
     * 删除所有测试，queryWrapper为null
     * 使用方法 int delete(@Param("ew") Wrapper<T> queryWrapper)
     */
    @Test
    void deleteAllTest() {
        int result = userMapper.delete(null);
        logger.info("result: " + result);
    }

    /**
     * 根据columnMap条件删除测试
     * 使用方法 int deleteByMap(@Param("cm") Map<String, Object> columnMap)
     */
    @Test
    void deletedByMapTest() {
        HashMap<String, Object> map = new HashMap<>();
        // WHERE user_level = ? AND user_point = ?
        map.put("user_level", Byte.valueOf("4"));
        map.put("user_point", 3000);
        int result = userMapper.deleteByMap(map);
        logger.info("result: " + result);
    }

    /**
     * 根据ID更新测试
     * 使用方法 int updateById(@Param("et") T entity)
     */
    @Test
    void updateByIDTest() {
        User user = new User().setId(2L).setPassword("aa123bbd678").setUserPoint(1300).setUserLevel(Byte.valueOf("3"));
        int result = userMapper.updateById(user);
        logger.info("result: " + result);
    }

    /**
     * 根据whereWrapper条件更新测试1，使用entity进行set，queryWrapper构造条件
     * 使用方法 int update(@Param("et") T entity, @Param("ew") Wrapper<T> updateWrapper)
     */
    @Test
    void updateByWrapperTest1() {
        // UPDATE user SET user_point=?, user_level=? WHERE (gender = ?)
        User user = new User().setUserPoint(3200).setUserLevel(Byte.valueOf("4")).setBirthday(null);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gender", "男");
        int result = userMapper.update(user, queryWrapper);
        logger.info("result: " + result);
    }

    /**
     * 根据whereWrapper条件更新测试2，entity为null，使用updateWrapper构造条件并set
     * 使用方法 int update(@Param("et") T entity, @Param("ew") Wrapper<T> updateWrapper)
     */
    @Test
    void updateByWrapperTest2() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        // UPDATE user SET user_point=?,user_level=?,birthday=? WHERE (gender = ?)
        updateWrapper.eq("gender", "女").set("user_point", 12000).set("user_level", Byte.valueOf("6"))
            .set("birthday", null);
        int result = userMapper.update(null, updateWrapper);
        logger.info("result: " + result);
    }

    /**
     * 根据ID查询测试
     * 使用方法 T selectById(Serializable id)
     */
    @Test
    void selectByIdTest() {
        User user = userMapper.selectById(6L);
        logger.info(user.toString());
    }

    /**
     * 根据ID批量查询测试
     * 使用方法 List<T> selectBatchIds(@Param("coll") Collection<? extends Serializable> idList)
     */
    @Test
    void selectByIdsTest() {
        Long[] array = {1L, 5L, 9L, 11L};
        List<Long> ids = Arrays.stream(array).collect(Collectors.toList());
        List<User> users = userMapper.selectBatchIds(ids);
        users.forEach(user -> logger.info(user.toString()));
    }

    /**
     * 根据条件查询一条记录测试
     * 使用方法 T selectOne(@Param("ew") Wrapper<T> queryWrapper)
     */
    @Test
    void selectOneTest() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // WHERE (realname = ?)
        queryWrapper.eq("realname", "貂蝉");
        //        queryWrapper.eq("gender", "女");
        User user = userMapper.selectOne(queryWrapper);
        logger.info(user.toString());
    }

    /**
     * 根据条件查询全部记录测试
     * 使用方法 List<T> selectList(@Param("ew") Wrapper<T> queryWrapper)
     */
    @Test
    void selectListTest() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // SELECT realname,age,user_point,user_level FROM user WHERE (age BETWEEN ? AND ? OR user_point < ?)
        queryWrapper.select("realname", "age", "user_point", "user_level").between("age", 21, 24).or()
            .lt("user_point", 500);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(user -> logger.info(user.toString()));
    }

    /**
     * 根据columnMap条件查询测试
     * 使用方法 List<T> selectByMap(@Param("cm") Map<String, Object> columnMap)
     */
    @Test
    void selectByMapTest() {
        HashMap<String, Object> map = new HashMap<>();
        // WHERE gender = ? AND age = ?
        map.put("gender", "男");
        map.put("age", 25);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(user -> logger.info(user.toString()));
    }

    /**
     * 根据Wrapper条件查询全部记录测试
     * 使用方法 List<Map<String, Object>> selectMaps(@Param("ew") Wrapper<T> queryWrapper)
     */
    @Test
    void selectMapsTest() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // SELECT realname,age,user_point,user_level FROM user WHERE (age BETWEEN ? AND ? OR user_point < ?)
        queryWrapper.select("realname", "age", "user_point", "user_level").between("age", 21, 24).or()
            .lt("user_point", 500);
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(map -> logger.info(map.toString()));
    }

    /**
     * 根据Wrapper条件查询全部记录测试，只返回第一个字段的值
     * 使用方法 List<Object> selectObjs(@Param("ew") Wrapper<T> queryWrapper)
     */
    @Test
    void selectObjsTest() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // SELECT username,password,realname FROM user WHERE (age > ?)
        queryWrapper.select("username", "password", "realname").gt("age", 25);
        // 只返回第一个字段username
        List<Object> objects = userMapper.selectObjs(queryWrapper);
        objects.forEach(object -> logger.info(object.toString()));
    }

    /**
     * 根据Wrapper条件查询总记录数测试
     * 按用方法 Integer selectCount(@Param("ew") Wrapper<T> queryWrapper)
     */
    @Test
    void selectCountTest() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // WHERE (user_point > ? AND age < ?)
        queryWrapper.gt("user_point", 1000).lt("age", 25);
        Integer count = userMapper.selectCount(queryWrapper);
        logger.info("count: " + count);
    }

    /**
     * 分页查询测试1，queryWrapper为null，查询所有记录并分页
     * 使用方法 <E extends IPage<T>> E selectPage(E page, @Param("ew") Wrapper<T> queryWrapper)
     */
    @Test
    void selectPageTest1() {
        // 参数1：当前页，默认为1，小于1则按1处理，参数2：每页记录数，默认为10
        Page<User> page = new Page<>(3, 3);
        Page<User> userPage = userMapper.selectPage(page, null);
        logger.info("查询到的记录总数：" + userPage.getTotal());
        logger.info("总页数：" + userPage.getPages());
        // 取出分页数据
        List<User> users = userPage.getRecords();
        users.forEach(user -> logger.info(user.toString()));
    }

    /**
     * 分页查询测试2，根据条件查询全部记录并分页
     * 使用方法 <E extends IPage<T>> E selectPage(E page, @Param("ew") Wrapper<T> queryWrapper)
     */
    @Test
    void selectPageTest2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // WHERE (gender = ? AND age >= ?)
        queryWrapper.eq("gender", "男").ge("age", 25);
        // 参数1：当前页，默认为1，小于1则按1处理，参数2：每页记录数，默认为10
        Page<User> page = new Page<>(1, 2);
        Page<User> userPage = userMapper.selectPage(page, queryWrapper);
        logger.info("查询到的记录总数：" + userPage.getTotal());
        logger.info("总页数：" + userPage.getPages());
        // 取出分页数据
        List<User> users = userPage.getRecords();
        users.forEach(user -> logger.info(user.toString()));
    }

    /**
     * 条件构造器测试1
     * allEq(boolean condition, Map<R, V> params, boolean null2IsNull)：全部eq，或个别isNull
     * 参数说明：
     * condition：表示该条件是否加入最后生成的sql中，true表示加入，false表示不加入
     * params：key为数据库字段名，value为字段值
     * null2IsNull：为true则在map的value为null时调用isNull方法，为false时则忽略value为null的
     */
    @Test
    void selectByWrapperTest1() {
        // 手动模拟查询条件
        UserQuery userQuery = new UserQuery().setGenderQuery("男").setAgeQuery(25);
//        userQuery = null;
        // userQuery为null会查询所有记录
        if (userQuery != null) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            HashMap<String, Object> map = new HashMap<>();
            map.put("gender", userQuery.getGenderQuery());
            map.put("age", userQuery.getAgeQuery());
            map.put("birthday", null);
            // gender不为null或空，age不为null，WHERE (gender = ? AND age = ?)
            // gender不为null或空，age为null，WHERE (gender = ?)
            // gender为null或空，age不为null，WHERE (age = ?)
            // gender为null或空，age为null，没有条件
            queryWrapper
                .allEq(!StringUtils.isNullOrEmpty(userQuery.getGenderQuery()) || userQuery.getAgeQuery() != null, map,
                    false);
            List<User> users = userMapper.selectList(queryWrapper);
            users.forEach(user -> logger.info(user.toString()));
        } else {
            List<User> users = userMapper.selectList(null);
            users.forEach(user -> logger.info(user.toString()));
        }
    }

    /**
     * 条件构造器测试2
     * ne(boolean condition, R column, Object val)：不等于 <>
     * gt(boolean condition, R column, Object val)：大于 >
     * le(boolean condition, R column, Object val)：小于等于 <=
     * in(boolean condition, R column, Collection<?> coll)：字段 IN (value.get(0), value.get(1), ...)
     * 多个条件默认使用and连接
     */
    @Test
    void selectByWrapperTest2() {
        // 手动模拟查询条件
        Long[] array = {1L, 5L, 7L, 9L};
        UserQuery userQuery = new UserQuery().setRealnameQuery("张飞").setMinAgeQuery(20).setMaxUserPointQuery(5000)
            .setIdsQuery(Arrays.stream(array).collect(Collectors.toList()));
        if (userQuery != null) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            // 只有userQuery.getRealnameQuery()不为null或空才会拼接realname <> ?
            queryWrapper
                .ne(!StringUtils.isNullOrEmpty(userQuery.getRealnameQuery()), "realname", userQuery.getRealnameQuery())
                // 只有userQuery.getMinAgeQuery()不为null才会拼接age > ?
                .gt(userQuery.getMinAgeQuery() != null, "age", userQuery.getMinAgeQuery())
                // 只有userQuery.getMaxUserPointQuery()不为null才会拼接user_point <= ?
                .le(userQuery.getMaxUserPointQuery() != null, "user_point", userQuery.getMaxUserPointQuery())
                // 只有userQuery.getIdsQuery()不为null或空才会拼接id IN (?,?,?,?)
                .in(userQuery.getIdsQuery() != null && !userQuery.getIdsQuery().isEmpty(), "id",
                    userQuery.getIdsQuery());
            List<User> users = userMapper.selectList(queryWrapper);
            users.forEach(user -> logger.info(user.toString()));
        } else {
            List<User> users = userMapper.selectList(null);
            users.forEach(user -> logger.info(user.toString()));
        }
    }

    /**
     * 条件构造器测试3
     * between(boolean condition, R column, Object val1, Object val2)：BETWEEN 值1 AND 值2
     * notBetween(boolean condition, R column, Object val1, Object val2)：NOT BETWEEN 值1 AND 值2
     * notInSql(R column, String inValue)：字段 NOT IN ( sql语句 )
     * 多个条件默认使用and连接，Lambda方式构造条件
     */
    @Test
    void selectByWrapperTest3() {
        // 手动模拟查询条件
        UserQuery userQuery =
            new UserQuery().setMinAgeQuery(20).setMaxAgeQuery(30).setMinUserPointQuery(2000).setMaxUserPointQuery(5000);
        if (userQuery != null) {
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            // 只有userQuery.getMinAgeQuery()不为null且userQuery.getMaxAgeQuery()不为null才会拼接age BETWEEN ? AND ?
            queryWrapper.between(userQuery.getMinAgeQuery() != null && userQuery.getMaxAgeQuery() != null, User::getAge,
                userQuery.getMinAgeQuery(), userQuery.getMaxAgeQuery())
                // 只有userQuery.getMinUserPointQuery()不为null且userQuery.getMaxUserPointQuery()不为null才会拼接user_point NOT BETWEEN ? AND ?
                .notBetween(userQuery.getMinUserPointQuery() != null && userQuery.getMaxUserPointQuery() != null,
                    User::getUserPoint, userQuery.getMinUserPointQuery(), userQuery.getMaxUserPointQuery())
                // id NOT IN (select id from user where user_level in (1,3))
                .notInSql(User::getId, "select id from user where user_level in (1,3)");
            List<User> users = userMapper.selectList(queryWrapper);
            users.forEach(user -> logger.info(user.toString()));
        } else {
            List<User> users = userMapper.selectList(null);
            users.forEach(user -> logger.info(user.toString()));
        }
    }

    /**
     * 条件构造器测试4
     * like(boolean condition, R column, Object val)：LIKE '%值%'
     * notLike(boolean condition, R column, Object val)：NOT LIKE '%值%'
     * likeLeft(boolean condition, R column, Object val)：LIKE '%值'
     * likeRight(boolean condition, R column, Object val)：LIKE '值%'
     * 多个条件默认使用and连接，Lambda方式构造条件
     */
    @Test
    void selectByWrapperTest4() {
        // 手动模拟查询条件
        UserQuery userQuery =
            new UserQuery().setUsernameQuery("ao").setPasswordQuery("so").setRealnameQuery("乔").setEmailQuery("xi");
        if (userQuery != null) {
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            // 只有userQuery.getUsernameQuery()不为null或空才会拼接username LIKE ?，Parameters：%val%
            queryWrapper.like(!StringUtils.isNullOrEmpty(userQuery.getUsernameQuery()), User::getUsername,
                userQuery.getUsernameQuery())
                // 只有userQuery.getPasswordQuery()不为null或空才会拼接password NOT LIKE ?，Parameters：%val%
                .notLike(!StringUtils.isNullOrEmpty(userQuery.getPasswordQuery()), User::getPassword,
                    userQuery.getPasswordQuery())
                // 只有userQuery.getRealnameQuery()不为null或空才会拼接realname LIKE ?，Parameters：%val
                .likeLeft(!StringUtils.isNullOrEmpty(userQuery.getRealnameQuery()), User::getRealname,
                    userQuery.getRealnameQuery())
                // 只有userQuery.getEmailQuery()不为null或空才会拼接email LIKE ?，Parameters：val%
                .likeRight(!StringUtils.isNullOrEmpty(userQuery.getEmailQuery()), User::getEmail,
                    userQuery.getEmailQuery());
            List<User> users = userMapper.selectList(queryWrapper);
            users.forEach(user -> logger.info(user.toString()));
        } else {
            List<User> users = userMapper.selectList(null);
            users.forEach(user -> logger.info(user.toString()));
        }
    }

    /**
     * 条件构造器测试5
     * ge(boolean condition, R column, Object val)：大于等于 >=
     * lt(boolean condition, R column, Object val)：小于 <
     * eq(boolean condition, R column, Object val)：等于 =
     * or()：拼接 OR
     * or(boolean condition, Consumer<Children> consumer)：OR 嵌套
     * 主动调用or表示紧接着下一个方法不是用and连接，不调用or则默认为使用and连接
     * Lambda方式构造条件
     */
    @Test
    void selectByWrapperTest5() {
        // 手动模拟查询条件
        Byte[] bytes = {2, 3, 5, 6};
        List<Byte> user_levels = Arrays.stream(bytes).collect(Collectors.toList());
        UserQuery userQuery =
            new UserQuery().setMinUserPointQuery(5000).setMaxAgeQuery(20).setUserLevelsQuery(user_levels)
                .setGenderQuery("男");
        if (userQuery != null) {
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            // 只有userQuery.getMinUserPointQuery()不为null才会拼接user_point >= ?
            queryWrapper
                .ge(userQuery.getMinUserPointQuery() != null, User::getUserPoint, userQuery.getMinUserPointQuery())
                // OR
                .or()
                // 只有userQuery.getMaxAgeQuery()不为null才会拼接age < ?
                .lt(userQuery.getMaxAgeQuery() != null, User::getAge, userQuery.getMaxAgeQuery())
                // 只有userQuery.getUserLevelsQuery()不为null或空才会拼接OR (user_level NOT IN (?,?,?,?))
                // 同时只有userQuery.getGenderQuery()不为null或空才会拼接OR (user_level NOT IN (?,?,?,?) AND gender = ?)
                .or(userQuery.getUserLevelsQuery() != null && !userQuery.getUserLevelsQuery().isEmpty(),
                    i -> i.notIn(User::getUserLevel, userQuery.getUserLevelsQuery())
                        .eq(!StringUtils.isNullOrEmpty(userQuery.getGenderQuery()), User::getGender,
                            userQuery.getGenderQuery()));
            List<User> users = userMapper.selectList(queryWrapper);
            users.forEach(user -> logger.info(user.toString()));
        } else {
            List<User> users = userMapper.selectList(null);
            users.forEach(user -> logger.info(user.toString()));
        }
    }

    /**
     * 条件构造器测试6
     * orderByAsc(R column)：ORDER BY 字段, ... ASC
     * orderByDesc(R column)：ORDER BY 字段, ... DESC
     * orderBy(boolean condition, boolean isAsc, R... columns)：ORDER BY 字段, ...
     * 部分参数说明：
     * isAsc：是否升序排序，true表示是，false表示否
     * Lambda方式构造条件
     */
    @Test
    void selectByWrapperTest6() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        // ORDER BY age ASC
//        queryWrapper.orderByAsc(User::getAge);
//        // ORDER BY user_point DESC
//        queryWrapper.orderByDesc(User::getUserPoint);
        // ORDER BY birthday ASC
        queryWrapper.orderBy(true, true, User::getBirthday);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(user -> logger.info(user.toString()));
    }

    /**
     * 条件构造器测试7
     * select(String... columns)：设置查询字段
     * select(SFunction<T, ?>... columns)：Lambda方式设置查询字段
     */
    @Test
    void selectByWrapperTest7() {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select("id", "realname", "age").in(true, "id", 1L, 3L, 5L);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        // SELECT id,realname,age FROM user WHERE (id IN (?,?,?))
        queryWrapper.select(User::getId, User::getRealname, User::getAge).in(true, User::getId, 2L, 4L, 6L);
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(map -> logger.info(map.toString()));
    }

    /**
     * 条件构造器测试8
     * select(Class<T> entityClass, Predicate<TableFieldInfo> predicate)：过滤查询字段，主键除外
     * Lambda方式构造条件
     */
    @Test
    void selectByWrapperTest8() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        // SELECT id,username,realname,gender,age,email,user_point,user_level FROM user WHERE (id IN (?,?,?))
        queryWrapper.select(User.class, i -> !i.getColumn().equals("password") && !i.getColumn().equals("birthday"))
            .in(true, User::getId, 3L, 6L, 9L);
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(map -> logger.info(map.toString()));
    }

    /**
     * 条件构造器测试9
     * groupBy(R column)：GROUP BY 字段, ...
     * having(boolean condition, String sqlHaving, Object... params)：HAVING ( sql语句 )
     */
    @Test
    void selectByWrapperTest9() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // SELECT user_level,GROUP_CONCAT(realname) AS realname_concat,COUNT(*) AS count,SUM(user_point) AS point_sum,MAX(age) AS max_age FROM user GROUP BY user_level HAVING point_sum < 5000 OR max_age > 30
        queryWrapper.select("user_level", "GROUP_CONCAT(realname) AS realname_concat", "COUNT(*) AS count",
            "SUM(user_point) AS point_sum", "MAX(age) AS max_age").groupBy("user_level")
            .having(true, "point_sum < 5000 OR max_age > 30");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(map -> logger.info(map.toString()));
    }

    /**
     * 逻辑删除后查询测试
     * 查询时会追加where条件过滤掉已删除数据且使用entity生成的where条件会忽略逻辑删除字段
     */
    @Test
    void selectAfterLogicDeleteTest() {
        // SELECT id,username,password,realname,gender,age,email,user_point,user_level,birthday,deleted FROM user WHERE gender=? AND age=? AND deleted=0
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(new User().setGender("女").setAge(22).setDeleted(1));
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(user -> logger.info(user.toString()));
    }

    /**
     * 乐观锁测试
     */
    @Test
    void optimisticLockerTest() {
        User user = userMapper.selectById(11L);
        user.setPassword("123edf321").setAge(29);
        int result = userMapper.updateById(user);
        logger.info("result: " + result);
    }
}