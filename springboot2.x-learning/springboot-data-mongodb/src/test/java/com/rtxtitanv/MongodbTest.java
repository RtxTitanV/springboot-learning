package com.rtxtitanv;

import com.rtxtitanv.model.User;
import com.rtxtitanv.projections.NameOnly;
import com.rtxtitanv.repository.UserRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.MongodbTest
 * @description SpringDataMongoDB单元测试类
 * @date 2021/5/26 18:11
 */
@SpringBootTest
class MongodbTest {

    @Resource
    private UserRepository userRepository;
    private static Logger logger = LoggerFactory.getLogger(MongodbTest.class);

    /**
     * 保存测试，这里保存5条测试文档，一次插入一条文档
     * 使用方法 <S extends T> S save(S var1)
     */
    @Test
    void testSave() {
        User user;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        user = userRepository.save(new User().setUsername("guanyuchang123").setPassword("a123456").setRealname("关羽")
            .setGender("男").setAge(28).setEmail("yunchang@xxx.com").setUserPoint(100).setUserLevel(Byte.valueOf("1"))
            .setBirthday(LocalDateTime.parse("1992-10-01 17:15:20", dateTimeFormatter)));
        logger.info(user.toString());
        user = userRepository.save(new User().setUsername("qiaolaoda888").setPassword("ss0635gh").setRealname("大乔")
            .setGender("女").setAge(20).setEmail("daqiao@xxx.com").setUserPoint(360).setUserLevel(Byte.valueOf("1"))
            .setBirthday(LocalDateTime.parse("2000-12-25 13:22:32", dateTimeFormatter)));
        logger.info(user.toString());
        user = userRepository.save(new User().setUsername("feige45").setPassword("qwer1234aa").setRealname("张飞")
            .setGender("男").setAge(25).setEmail("yide@xxx.com").setUserPoint(1000).setUserLevel(Byte.valueOf("3"))
            .setBirthday(LocalDateTime.parse("1995-05-16 08:10:15", dateTimeFormatter)));
        logger.info(user.toString());
        user = userRepository.save(new User().setUsername("zilongzhao01").setPassword("qscrdx265").setRealname("赵云")
            .setGender("男").setAge(21).setEmail("zilong@xxx.com").setUserPoint(666).setUserLevel(Byte.valueOf("2"))
            .setBirthday(LocalDateTime.parse("1999-11-27 22:15:25", dateTimeFormatter)));
        logger.info(user.toString());
        user = userRepository.save(new User().setUsername("qiaoxiaomei886").setPassword("123wwqqs36").setRealname("小乔")
            .setGender("女").setAge(18).setEmail("xiaoqiao@xxx.com").setUserPoint(2500).setUserLevel(Byte.valueOf("4"))
            .setBirthday(LocalDateTime.parse("2002-09-06 12:28:33", dateTimeFormatter)));
        logger.info(user.toString());
    }

    /**
     * 批量保存测试，这里保存5条测试文档
     * 使用方法 <S extends T> List<S> saveAll(Iterable<S> var1)
     */
    @Test
    void testSaveAll() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        User user1 = new User().setUsername("shangxiang23").setPassword("asrf0325ss").setRealname("孙尚香").setGender("女")
            .setAge(22).setEmail("shangxiang@xxx.com").setUserPoint(10000).setUserLevel(Byte.valueOf("6"))
            .setBirthday(LocalDateTime.parse("1998-03-11 21:51:10", dateTimeFormatter));
        User user2 = new User().setUsername("liuxuande66").setPassword("zxcv456es").setRealname("刘备").setGender("男")
            .setAge(35).setEmail("xuande@xxx.com").setUserPoint(5000).setUserLevel(Byte.valueOf("5"))
            .setBirthday(LocalDateTime.parse("1985-12-25 13:22:32", dateTimeFormatter));
        User user3 = new User().setUsername("diaochan321").setPassword("asoplk66").setRealname("貂蝉").setGender("女")
            .setAge(22).setEmail("diaochan@xxx.com").setUserPoint(888).setUserLevel(Byte.valueOf("3"))
            .setBirthday(LocalDateTime.parse("1998-06-19 07:22:36", dateTimeFormatter));
        User user4 = new User().setUsername("xiahou360").setPassword("a1s2d3q6").setRealname("夏侯惇").setGender("男")
            .setAge(30).setEmail("xiahoudun@xxx.com").setUserPoint(1200).setUserLevel(Byte.valueOf("3"))
            .setBirthday(LocalDateTime.parse("1990-08-16 23:17:51", dateTimeFormatter));
        User user5 = new User().setUsername("jiangdongyige09").setPassword("637cvxs").setRealname("孙策").setGender("男")
            .setAge(25).setEmail("sunce@xxx.com").setUserPoint(3000).setUserLevel(Byte.valueOf("4"))
            .setBirthday(LocalDateTime.parse("1995-12-06 11:16:45", dateTimeFormatter));
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users = userRepository.saveAll(users);
        users.forEach(user -> logger.info(user.toString()));
    }

    /**
     * 查询所有测试
     * 使用方法 List<T> findAll()
     */
    @Test
    void testFindAll() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> logger.info(user.toString()));
    }

    /**
     * 根据id查询测试
     * 使用方法 Optional<T> findById(ID var1)
     */
    @Test
    void testFindById() {
        Optional<User> optional = userRepository.findById(new ObjectId("60b9d4ce3ddb3147679f2b20"));
        if (!optional.isPresent()) {
            logger.info("该用户不存在");
        } else {
            logger.info(optional.get().toString());
        }
    }

    /**
     * 根据id批量查询测试
     * 使用方法 Iterable<T> findAllById(Iterable<ID> var1)
     */
    @Test
    void testFindAllById() {
        ObjectId[] array = {new ObjectId("60b9d3db579bb97eea876e73"), new ObjectId("60b9d3dc579bb97eea876e77"),
            new ObjectId("60b9d4ce3ddb3147679f2b23")};
        List<ObjectId> ids = Arrays.stream(array).collect(Collectors.toList());
        Iterable<User> users = userRepository.findAllById(ids);
        users.forEach(user -> logger.info(user.toString()));
    }

    /**
     * 查询文档总数测试
     * 使用方法 long count()
     */
    @Test
    void testCount() {
        long count = userRepository.count();
        logger.info("count: " + count);
    }

    /**
     * 查询指定id文档是否存在测试
     * 使用方法 boolean existsById(ID var1)
     */
    @Test
    void testExistsById() {
        boolean exists = userRepository.existsById(new ObjectId("60b9d3dc579bb97eea876e75"));
        if (exists) {
            logger.info("存在");
        } else {
            logger.info("不存在");
        }
    }

    /**
     * 根据id删除测试
     * 使用方法 void deleteById(ID var1)
     */
    @Test
    void testDeleteById() {
        userRepository.deleteById(new ObjectId("60b9d4ce3ddb3147679f2b20"));
    }

    /**
     * 批量删除测试
     * 使用方法 void deleteAll(Iterable<? extends T> var1)
     */
    @Test
    void testDeleteInBatch() {
        ObjectId[] array = {new ObjectId("60b9d3db579bb97eea876e73"), new ObjectId("60b9d3dc579bb97eea876e75"),
            new ObjectId("60b9d4ce3ddb3147679f2b23")};
        List<ObjectId> ids = Arrays.stream(array).collect(Collectors.toList());
        Iterable<User> users = userRepository.findAllById(ids);
        userRepository.deleteAll(users);
    }

    /**
     * 删除单条文档测试
     * 使用方法 delete(T var1)
     */
    @Test
    void testDelete() {
        Optional<User> optional = userRepository.findById(new ObjectId("60b9d3dc579bb97eea876e77"));
        if (!optional.isPresent()) {
            logger.info("该用户不存在");
        } else {
            userRepository.delete(optional.get());
        }
    }

    /**
     * 删除所有测试
     * 使用方法 void deleteAll()
     */
    @Test
    void testDeleteAll() {
        userRepository.deleteAll();
    }

    /**
     * 方法命名规则测试之按性别和年龄查询
     */
    @Test
    void testFindByGenderAndAge() {
        List<User> users = userRepository.findByGenderAndAge("男", 25);
        users.forEach(user -> logger.info(user.toString()));
    }

    /**
     * 方法命名规则测试之查询真名不等于且年龄大于且积分小于等于且id在指定集合中的文档
     */
    @Test
    void testFindByRealnameNotAndAgeGreaterThanAndUserPointLessThanEqualAndIdIn() {
        ObjectId[] array = {new ObjectId("60b9d3db579bb97eea876e73"), new ObjectId("60b9d3dc579bb97eea876e77"),
            new ObjectId("60b9d4ce3ddb3147679f2b21"), new ObjectId("60b9d4ce3ddb3147679f2b23")};
        List<User> users = userRepository.findByRealnameNotAndAgeGreaterThanAndUserPointLessThanEqualAndIdIn("张飞", 20,
            5000, Arrays.stream(array).collect(Collectors.toList()));
        users.forEach(user -> logger.info(user.toString()));
    }

    /**
     * 方法命名规则测试之分页查询出生日期大于给定日期并且用户积分在指定开区间与按用户名模糊查询的并集
     */
    @Test
    void testFindByBirthdayAfterAndUserPointBetweenOrUsernameLike() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Sort sort = Sort.by(Sort.Direction.ASC, "age");
        Pageable pageable = PageRequest.of(1, 2, sort);
        Page<User> page = userRepository.findByBirthdayAfterAndUserPointBetweenOrUsernameLike(
            LocalDateTime.parse("1995-05-16 00:10:15", dateTimeFormatter), 1000, 5000, "ao", pageable);
        logger.info("查询到的文档总数：" + page.getTotalElements());
        logger.info("总页数：" + page.getTotalPages());
        List<User> users = page.getContent();
        users.forEach(user -> logger.info(user.toString()));
    }

    /**
     * 方法命名规则测试之按年龄查询单条文档，如果发现多条文档，则会抛出异常
     */
    @Test
    void testFindByAge() {
        User user = userRepository.findByAge(28);
        logger.info(user.toString());
    }

    /**
     * 方法命名规则测试之统计密码以指定后缀结尾的文档总数
     */
    @Test
    void testCountByPasswordEndingWith() {
        Long count = userRepository.countByPasswordEndingWith("6");
        logger.info("count: " + count);
    }

    /**
     * 方法命名规则测试之限制查询，返回按用户LV降序排序的第一条文档
     */
    @Test
    void testFindFirstByOrderByUserLevelDesc() {
        User user = userRepository.findFirstByOrderByUserLevelDesc();
        logger.info(user.toString());
    }

    /**
     * 方法命名规则测试之限制查询，返回按用户LV升序排序的第一条文档
     */
    @Test
    void testFindTopByOrderByUserLevelAsc() {
        User user = userRepository.findTopByOrderByUserLevelAsc();
        logger.info(user.toString());
    }

    /**
     * 方法命名规则测试之限制查询，返回查询结果中的前五条文档并分页
     */
    @Test
    void testFindFirst5ByGender() {
        Sort sort = Sort.by("age").descending();
        Pageable pageable = PageRequest.of(0, 2, sort);
        Page<User> page = userRepository.findFirst5ByGender("男", pageable);
        logger.info("查询到的文档总数：" + page.getTotalElements());
        logger.info("总页数：" + page.getTotalPages());
        List<User> users = page.getContent();
        users.forEach(user -> logger.info(user.toString()));
    }

    /**
     * 方法命名规则测试之删除按密码模糊查询出的文档
     */
    @Test
    void testDeleteByPasswordNotLike() {
        List<User> users = userRepository.deleteByPasswordNotLike("3");
        users.forEach(user -> logger.info(user.toString()));
    }

    /**
     * 方法命名规则测试之删除年龄不在指定集合中的文档
     */
    @Test
    void testDeleteByAgeNotIn() {
        Integer[] array = {20, 22, 25};
        Long count = userRepository.deleteByAgeNotIn(Arrays.stream(array).collect(Collectors.toList()));
        logger.info("删除的记录总数：" + count);
    }

    /**
     * 方法命名规则测试之删除邮箱以指定前缀开头的第一条文档
     */
    @Test
    void testDeleteByEmailStartingWith() {
        Optional<User> optional = userRepository.deleteByEmailStartingWith("s");
        if (!optional.isPresent()) {
            logger.info("该用户不存在");
        } else {
            logger.info(optional.get().toString());
        }
    }

    /**
     * 查询所有并排序测试
     * 使用方法 List<T> findAll(Sort var1)
     */
    @Test
    void testFindAllAndSort() {
        // 使用属性名称定义简单的排序表达式
        Sort sort = Sort.by(Sort.Direction.DESC, "age");
        List<User> users = userRepository.findAll(sort);
        users.forEach(user -> logger.info(user.toString()));
    }

    /**
     * 分页查询所有并排序测试
     * 使用方法 Page<T> findAll(Pageable var1)
     */
    @Test
    void testFindAllAndPageSort() {
        // 使用类型安全API定义排序表达式
        Sort.TypedSort<User> typedSort = Sort.sort(User.class);
        // 使用方法引用来定义排序的属性
        Sort sort = typedSort.by(User::getBirthday).ascending();
        Pageable pageable = PageRequest.of(0, 3, sort);
        Page<User> page = userRepository.findAll(pageable);
        logger.info("查询到的文档总数：" + page.getTotalElements());
        logger.info("总页数：" + page.getTotalPages());
        List<User> users = page.getContent();
        users.forEach(user -> logger.info(user.toString()));
    }

    /**
     * 查询用户积分大于等于给定值的文档并排序测试
     */
    @Test
    void testFindByUserPointGreaterThanEqual() {
        Sort sort = Sort.sort(User.class).by(User::getUserPoint).descending();
        List<User> users = userRepository.findByUserPointGreaterThanEqual(3000, sort);
        users.forEach(user -> logger.info(user.toString()));
    }

    /**
     * 基于MongoDB JSON的查询方法测试，查询指定性别并且年龄小于指定值的文档，并限制映射到Java对象的字段集
     */
    @Test
    void testFindByTheUserGenderAndAgeLessThan() {
        List<User> users = userRepository.findByTheUserGenderAndAgeLessThan("女", 25);
        users.forEach(user -> logger.info(user.toString()));
    }

    /**
     * 基于MongoDB JSON的查询方法测试，查询指定用户LV的文档并按年龄降序排序
     */
    @Test
    void testFindByTheUserLevelAndSortDescByAge() {
        List<User> users = userRepository.findByTheUserLevelAndSortDescByAge(Byte.valueOf("3"));
        users.forEach(user -> logger.info(user.toString()));
    }

    /**
     * 用SpEL表达式进行基于JSON的查询测试，查询年龄在指定开区间中的文档
     */
    @Test
    void testFindByQueryWithExpression() {
        List<User> users = userRepository.findByQueryWithExpression(22, 30);
        users.forEach(user -> logger.info(user.toString()));
    }

    /**
     * 用SpEL表达式进行基于JSON的查询测试，查询在指定用户LV集合的文档的用户名和用户真实姓名
     */
    @Test
    void testFindNameByUserLevelIn() {
        Byte[] array = {Byte.valueOf("1"), Byte.valueOf("3"), Byte.valueOf("5")};
        List<NameOnly> names = userRepository.findNameByUserLevelIn(Arrays.stream(array).collect(Collectors.toList()));
        names.forEach(
            nameOnly -> logger.info("username: " + nameOnly.getUsername() + ", realname: " + nameOnly.getRealname()));
    }
}