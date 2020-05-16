package com.rtxtitanv;

import com.mysql.cj.util.StringUtils;
import com.rtxtitanv.model.*;
import com.rtxtitanv.model.query.UserQuery;
import com.rtxtitanv.projections.NameOnly;
import com.rtxtitanv.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author rtxtitanv
 * @version v1.0.0
 * @name com.rtxtitanv.JpaTest
 * @description SpringDataJpa 单元测试类
 * @date 2019/12/31 20:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaApplication.class)
public class JpaTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MenuRepository menuRepository;
    private static Logger logger = LoggerFactory.getLogger(JpaTest.class);

    /**
     * 保存5条测试数据，一次插入一条数据
     * 使用方法 <S extends T> S save(S var1)
     * 实体中主键不存在时保存记录
     */
    @Test
    public void saveTest() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(date);
        logger.info("保存5条测试数据开始");
        userRepository.save(new User(null, "296ebeb46acd49aca54f0d5a5a1257c3", "qwer123", "123456", "aaa", 24,
                "qwer123@ss.com", "13915176512", formattedDate, "beijing"));
        userRepository.save(new User(null, "e6c133e338bb4b7c857be76104986acb", "asd6666", "qw23ss", "asc", 18,
                "asd6666@ss.com", "15736226963", formattedDate, "tianjin"));
        userRepository.save(new User(null, "179a91c205f84416b39347d714516c95", "tgh3er2", "11111", "r123er", 22,
                "advx@ss.com", "18956929863", formattedDate, "hangzhou"));
        userRepository.save(new User(null, "dddfa7b84b194ea5a62393ef8f211710", "erfgyhn", "567809a.", "rw23ed", 27,
                "ddfty@ss.com", "16389562477", formattedDate, "shanghai"));
        userRepository.save(new User(null, "7fc652d011e8448e99aee948f1af9187", "rty7ui81", "a2ef56.a", "asc", 18,
                "sdrtyhui@ss.com", "15966358996", formattedDate, "nanjing"));
        logger.info("保存5条测试数据结束");
    }

    /**
     * 批量保存5条测试数据 后台执行时仍是一条一条插入
     * 使用方法 <S extends T> List<S> saveAll(Iterable<S> var1)
     * 实体中主键不存在时保存记录
     */
    @Test
    public void saveInBatchTest() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(date);
        User user1 = new User(null, "8960a15b37d0552aa84483e24fc57b80", "23erg", "2234rf", "aaa", 15,
                "qadfgyty@ss.com", "16895635789", formattedDate, "beijing");
        User user2 = new User(null, "cc0106b175a6572e8d2967b3dd563193", "63serf", "2ww5t", "w323rrt", 36,
                "wer33@ss.com", "15766958245", formattedDate, "suzhou");
        User user3 = new User(null, "7669890a99c1581483edf72fa48d702c", "2w4r", "3345tt", "aaa", 24,
                "qert23@ss.com", "19725689756", formattedDate, "wuhan");
        User user4 = new User(null, "9a512c6ffe01565abb619e1199002603", "12er", "134rty", "aa23e54", 23,
                "qwer5@ss.com", "13858963144", formattedDate, "jinan");
        User user5 = new User(null, "f7e05429074b5db9a85d623377475ced", "yu2sd", "1w0978", "asc", 31,
                "wer123@ss.com", "18741569832", formattedDate, "xian");
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        logger.info("批量保存5条测试数据开始");
        userRepository.saveAll(list);
        logger.info("批量保存5条测试数据结束");
    }

    /**
     * 修改单条记录
     * 使用方法 <S extends T> S save(S var1)
     * 当实体中主键存在时先根据主键查询再根据主键更新
     */
    @Test
    public void updateOneTest() {
        Optional<User> userOptional = userRepository.findById(3L);
        if (!userOptional.isPresent()) {
            logger.info("该用户不存在");
        } else {
            User user = userOptional.get();
            logger.info("修改前的记录: " + user.toString());
            logger.info("修改一条用户记录开始");
            user.setNickName("6wer23a").setPassWord("123123");
            userRepository.save(user);
            logger.info("修改一条用户记录结束");
            logger.info("修改后的记录: " + userRepository.findById(3L).get().toString());
        }
    }

    /**
     * 批量修改
     * 使用方法 <S extends T> List<S> saveAll(Iterable<S> var1)
     * 当实体中主键存在时先根据主键查询再根据主键更新
     */
    @Test
    public void updateInBatchTest() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(3L);
        ids.add(5L);
        List<User> userList = userRepository.findAllById(ids);
        if (userList.isEmpty()) {
            logger.info("查询不到记录");
        } else {
            logger.info("修改前的记录");
            userList.forEach(user -> logger.info(user.toString()));
            logger.info("------------分割线-------------");
            userList.forEach(user -> user.setPassWord("666666"));
            logger.info("批量修改开始");
            userRepository.saveAll(userList);
            logger.info("批量修改结束");
            logger.info("修改后的记录");
            userRepository.findAllById(ids).forEach(user -> logger.info(user.toString()));

        }
    }

    /**
     * 根据id删除
     * 使用方法 void deleteById(ID var1)
     */
    @Test
    public void deleteByIdTest() {
        logger.info("根据id删除开始");
        userRepository.deleteById(3L);
        logger.info("根据id删除结束");
    }

    /**
     * 删除单条记录
     * 使用方法 void delete(T var1)
     */
    @Test
    public void deleteOneTest() {
        Optional<User> userOptional = userRepository.findById(1L);
        if (!userOptional.isPresent()) {
            logger.info("该用户不存在");
        } else {
            User user = userOptional.get();
            logger.info("删除一条用户记录开始");
            userRepository.delete(user);
            logger.info("删除一条用户记录结束");
        }
    }

    /**
     * 批量删除
     * 使用方法 void deleteInBatch(Iterable<T> var1)
     * 后台执行时生成一条sql语句删除
     */
    @Test
    public void deleteInBatch() {
        List<Long> ids = new ArrayList<>();
        ids.add(2L);
        ids.add(4L);
        ids.add(5L);
        ids.add(7L);
        List<User> userList = userRepository.findAllById(ids);
        if (userList.isEmpty()) {
            logger.info("用户数据不存在");
        } else {
            logger.info("批量删除开始");
            userRepository.deleteInBatch(userList);
            logger.info("批量删除结束");
        }
    }

    /**
     * 删除所有记录
     * 使用方法 void deleteAllInBatch()
     * 后台执行时生成一条sql语句删除
     */
    @Test
    public void deleteAllTest() {
        logger.info("删除所有开始");
        userRepository.deleteAllInBatch();
        logger.info("删除所有结束");
    }

    /**
     * 查询所有记录
     * 使用方法 List<T> findAll();
     */
    @Test
    public void findAllTest() {
        logger.info("查询所有开始");
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            logger.info("不存在用户数据");
        } else {
            userList.forEach(user -> logger.info(user.toString()));
        }
        logger.info("查询所有结束");
    }

    /**
     * 查询所有记录并排序
     * 使用方法 List<T> findAll(Sort var1)
     */
    @Test
    public void findAllAndSortTest() {
        Sort sort = Sort.by(Sort.Direction.DESC, "age");
        logger.info("查询所有并按年龄降序排序开始");
        List<User> userList = userRepository.findAll(sort);
        if (userList.isEmpty()) {
            logger.info("不存在用户数据");
        } else {
            userList.forEach(user -> logger.info(user.toString()));
        }
        logger.info("查询所有并按年龄降序排序结束");
    }

    /**
     * 分页查询所有记录
     * 使用方法 Page<T> findAll(Pageable var1)
     */
    @Test
    public void findAllAndPageTest() {
        Sort sort = Sort.by(Sort.Direction.ASC, "age");
        Pageable pageable = PageRequest.of(2, 3, sort);
        logger.info("分页查询所有开始");
        Page<User> page = userRepository.findAll(pageable);
        if (page.isEmpty()) {
            logger.info("不存在分页数据");
        } else {
            logger.info("总条数：" + page.getTotalElements());
            logger.info("总页数：" + page.getTotalPages());
            List<User> userList = page.getContent();
            userList.forEach(user -> logger.info(user.toString()));
        }
        logger.info("分页查询所有结束");
    }

    /**
     * 根据id查询
     * 使用方法 Optional<T> findById(ID var1)
     */
    @Test
    public void findByIdTest() {
        logger.info("根据id查询开始");
        Optional<User> userOptional = userRepository.findById(1L);
        if (!userOptional.isPresent()) {
            logger.info("查询的用户不存在");
        } else {
            User user = userOptional.get();
            String userInfo = user.toString();
            logger.info(userInfo);
        }
        logger.info("根据id查询结束");
    }

    /**
     * 根据id批量查询
     * 使用方法 List<T> findAllById(Iterable<ID> var1)
     */
    @Test
    public void findInBatchByIdTest() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(3L);
        ids.add(5L);
        logger.info("根据id批量查询开始");
        List<User> userList = userRepository.findAllById(ids);
        if (userList.isEmpty()) {
            logger.info("查询的用户不存在");
        } else {
            userList.forEach(user -> logger.info(user.toString()));
        }
        logger.info("根据id批量查询结束");
    }

    /**
     * 方法命名规则之按昵称查询
     */
    @Test
    public void findByNickNameTest() {
        logger.info("方法命名规则查询之按昵称查询开始");
        List<User> userList = userRepository.findByNickName("aaa");
        if (userList.isEmpty()) {
            logger.info("昵称为aaa的用户不存在");
        } else {
            userList.forEach(user -> logger.info(user.toString()));
        }
        logger.info("方法命名规则查询之按昵称查询结束");
    }

    /**
     * 方法命名规则之按昵称和年龄查询
     */
    @Test
    public void findByNickNameAndAgeTest() {
        logger.info("方法命名规则之按昵称和年龄查询开始");
        List<User> userList = userRepository.findByNickNameAndAge("asc", 18);
        if (userList.isEmpty()) {
            logger.info("昵称为asc并且年龄为18的用户不存在");
        } else {
            userList.forEach(user -> logger.info(user.toString()));
        }
        logger.info("方法命名规则之按昵称和年龄查询结束");
    }

    /**
     * 方法命名规则之按昵称模糊查询
     */
    @Test
    public void findByNickNameLikeTest() {
        logger.info("方法命名规则之按昵称模糊查询开始");
        List<User> userList = userRepository.findByNickNameLike("%23%");
        if (userList.isEmpty()) {
            logger.info("昵称包含23的用户不存在");
        } else {
            userList.forEach(user -> logger.info(user.toString()));
        }
        logger.info("方法命名规则之按昵称模糊查询结束");
    }

    /**
     * 方法命名规则之按年龄条件统计
     */
    @Test
    public void countByAgeLessThanEqualTest() {
        logger.info("方法命名规则之按年龄条件统计开始");
        Long count = userRepository.countByAgeLessThanEqual(24);
        logger.info("年龄不超过24岁的用户总数: " + count);
        logger.info("方法命名规则之按年龄条件统计结束");
    }

    /**
     * 方法命名规则之按邮箱模糊查询名称并按年龄升序
     */
    @Test
    public void findByEmailContainingOrderByAgeTest() {
        logger.info("方法命名规则之按邮箱模糊查询名称并按年龄升序开始");
        List<NameOnly> nameOnly = userRepository.findByEmailContainingOrderByAgeAsc("er");
        if (nameOnly.isEmpty()) {
            logger.info("不存在满足条件记录");
        } else {
            nameOnly.forEach(name -> logger.info("userName: " + name.getUserName() + ", " + "nickName: " + name.getNickName()));
        }
        logger.info("方法命名规则之按邮箱模糊查询名称并按年龄升序结束");
    }

    /**
     * 方法命名规则之限制查询
     */
    @Test
    public void findFirstByOrderByAgeAscTest() {
        logger.info("方法命名规则之限制查询开始");
        User user = userRepository.findFirstByOrderByAgeAsc();
        if (user == null) {
            logger.info("用户数据不存在");
        } else {
            String userInfo = user.toString();
            logger.info(userInfo);
        }
        logger.info("方法命名规则之限制查询结束");
    }

    /**
     * 方法命名规则之按昵称（忽略大小写）删除
     */
    @Test
    public void deleteByNickNameIgnoreCaseTest() {
        logger.info("方法命名规则之按昵称删除开始");
        int result = userRepository.deleteByNickNameIgnoreCase("AAa");
        logger.info("总共删除了" + result + "条记录");
        logger.info("方法命名规则之按昵称删除结束");
    }

    /**
     * 方法命名规则之按年龄批量删除
     */
    @Test
    public void deleteByAgeInTest() {
        List<Integer> ages = new ArrayList<>();
        ages.add(18);
        ages.add(23);
        ages.add(30);
        logger.info("方法命名规则之按年龄批量删除开始");
        int result = userRepository.deleteByAgeIn(ages);
        logger.info("总共删除了" + result + "条记录");
        logger.info("方法命名规则之按年龄批量删除结束");
    }

    /**
     * 使用sql语句插入一条记录
     */
    @Test
    public void insertUserTest() {
        logger.info("sql插入一条记录开始");
        int result = userRepository.insertUser("f0ff89db-aa72-55dc-aaba-e1ec11fa2fec", "dfgyytvb2", "123456", "rty235", 18, "miopl@ss.com", "1256698463", "2020-01-02 11:51:16");
        logger.info("sql插入一条记录结束");
        if (result == 1) {
            logger.info("插入成功");
        } else {
            logger.info("插入失败");
        }
    }

    /**
     * 使用JPQL语句按年龄批量查询名称并排序
     */
    @Test
    public void findNameByAgeInTest() {
        Sort sort = Sort.by("userName").descending();
        List<Integer> ages = new ArrayList<>();
        ages.add(18);
        ages.add(24);
        ages.add(27);
        logger.info("JPQL按年龄批量查询名称并按用户名降序开始");
        List<NameOnly> names = userRepository.findNameByAgeIn(ages, sort);
        if (names.isEmpty()) {
            logger.info("满足条件记录不存在");
        } else {
            names.forEach(name -> logger.info("userName: " + name.getUserName() + ", " + "nickName: " + name.getNickName()));
        }
        logger.info("JPQL按年龄批量查询名称并按用户名降序结束");
    }

    /**
     * 使用sql语句按用户名和昵称模糊分页查询名称
     */
    @Test
    public void findNameByNickNameAndUserNameLikeTest() {
        Pageable pageable = PageRequest.of(1,2);
        logger.info("sql语句按用户名和昵称模糊分页查询名称开始");
        Page<NameOnly> names = userRepository.findNameByNickNameAndUserNameLike("a", "r", pageable);
        if (names.isEmpty()) {
            logger.info("满足条件的查询记录不存在");
        } else {
            logger.info("总条数: " + names.getTotalElements());
            logger.info("总页数: " + names.getTotalPages());
            names.getContent().forEach(name -> logger.info("userName: " + name.getUserName() + ", " + "nickName: " + name.getNickName()));
        }
        logger.info("sql语句按用户名和昵称模糊分页查询名称结束");
    }


    /**
     * 使用JPQL语句根据年龄更新昵称
     */
    @Test
    public void updateUserNameByAgeTest() {
        logger.info("JPQL根据年龄更新昵称开始");
        int result = userRepository.updateUserNameByAge("nickname-01", 18);
        logger.info("更新了"+ result + "条记录");
        logger.info("JPQL根据年龄更新昵称结束");
    }


    /**
     * 使用sql语句更新单条记录
     */
    @Test
    public void updateUserTest() {
        Optional<User> userOptional = userRepository.findById(1L);
        if (!userOptional.isPresent()) {
            logger.info("查询用户不存在");
        } else {
            User user = userOptional.get();
            user.setPassWord("6543215622").setNickName("ava33");
            logger.info("sql语句更新单条记录开始");
            userRepository.updateUser(user);
            logger.info("sql语句更新单条记录结束");
        }
    }

    /**
     * 使用JPQL语句根据id批量删除
     */
    @Test
    public void deleteInBacthByIdTest() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(3L);
        ids.add(6L);
        ids.add(9L);
        logger.info("使用JPQL语句根据id批量删除开始");
        int result = userRepository.deleteInBacthById(ids);
        logger.info("总共删除了" + result + "条记录");
        logger.info("使用JPQL语句根据id批量删除结束");
    }


    /**
     * JpaSpecificationExecutor单条件查询测试
     */
    @Test
    public void findByOneConditionTest() {
        logger.info("单条件查询测试开始");
        /**
         * root：查询的根对象，可以通过get方法获取实体属性
         * criteriaQuery：代表一个顶层查询对象，可以构建自定义查询，包含select、where、orderBy、groupBy等
         * criteriaBuilder：查询条件构造器
         */
        List<User> userList = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            //user_name = "qwer123"
            Predicate predicate1 = criteriaBuilder.equal(root.get("userName"), "qwer123");
            //email like %er%
            Predicate predicate2 = criteriaBuilder.like(root.get("email"), "%er%");
            //age between 15 and 25
            Predicate predicate3 = criteriaBuilder.between(root.get("age"), 15, 25);
            //age >= 18
            Predicate predicate4 = criteriaBuilder.ge(root.get("age"), 18);
            //age <= 25
            Predicate predicate5 = criteriaBuilder.le(root.get("age"), 25);
            return predicate5;
        });
        if (userList.isEmpty()) {
            logger.info("没有满足条件的数据");
        } else {
            userList.forEach(user -> logger.info(user.toString()));
        }
        logger.info("单条件查询测试结束");
    }


    /**
     * JpaSpecificationExecutor动态拼接多条件查询测试
     * 拼接方式1：每个条件均为and连接
     * 实际开发中可以根据实际的动态条件灵活处理
     */
    @Test
    public void findByConditionsTest() {
        //手动模拟查询条件
//        UserQuery userQuery = null;
        UserQuery userQuery = new UserQuery();
        List<Long> ids = new ArrayList<>();
        List<Integer> ages = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(5L);
        ages.add(18);
        ages.add(24);
        userQuery.setUserNameQuery("r").setNickNameQuery("a")
                .setMinAgeQuery(null).setMaxAgeQuery(25).setIdQuery(null)
                .setTelQuery("135").setEmailQuery("rt").setPassWordQuery("123")
                .setIdsQuery(ids).setAgesQuery(ages);
        logger.info("动态拼接多条件查询测试开始");
        List<User> userList = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            //如果userQuery为空或者userQuery所有属性均为空会自动生成where 1 = 1
            Predicate predicate = criteriaBuilder.conjunction();
            //如果userQuery为空或者userQuery所有属性均为空会查询所有记录
            if (userQuery != null) {
                if (userQuery.getIdQuery() != null) {
                    //notEqual查询
                    predicate.getExpressions().add(criteriaBuilder.notEqual(root.get("id"), userQuery.getIdQuery()));
                }
                if (!StringUtils.isNullOrEmpty(userQuery.getUserNameQuery())) {
                    //like查询
                    predicate.getExpressions().add(criteriaBuilder.like(root.get("userName"), "%" + userQuery.getUserNameQuery() + "%"));
                }
                if (!StringUtils.isNullOrEmpty(userQuery.getNickNameQuery())) {
                    //notLike查询
                    predicate.getExpressions().add(criteriaBuilder.notLike(root.get("nickName"), "%" + userQuery.getNickNameQuery() + "%"));
                }
                if (!StringUtils.isNullOrEmpty(userQuery.getPassWordQuery())) {
                    //equal查询
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("passWord"), userQuery.getPassWordQuery()));
                }
                if (!StringUtils.isNullOrEmpty(userQuery.getEmailQuery())) {
                    //notEqual查询
                    predicate.getExpressions().add(criteriaBuilder.notEqual(root.get("email"), userQuery.getEmailQuery()));
                }
                if (!StringUtils.isNullOrEmpty(userQuery.getTelQuery())) {
                    //like查询
                    predicate.getExpressions().add(criteriaBuilder.like(root.get("tel"), "%" + userQuery.getTelQuery()));
                }
                if (userQuery.getMinAgeQuery() != null && userQuery.getMaxAgeQuery() != null) {
                    //between查询
                    predicate.getExpressions().add(criteriaBuilder.between(root.get("age"), userQuery.getMinAgeQuery(), userQuery.getMaxAgeQuery()));
                } else if (userQuery.getMinAgeQuery() != null) {
                    //>=查询 gt为>
                    predicate.getExpressions().add(criteriaBuilder.ge(root.get("age"), userQuery.getMinAgeQuery()));
                } else if (userQuery.getMaxAgeQuery() != null) {
                    //<=查询 lt为<
                    predicate.getExpressions().add(criteriaBuilder.le(root.get("age"), userQuery.getMaxAgeQuery()));
                }
                if (userQuery.getIdsQuery() != null && !userQuery.getIdsQuery().isEmpty()) {
                    //in 批量查询
                    predicate.getExpressions().add(criteriaBuilder.and(root.get("id").in(userQuery.getIdsQuery())));
                }
                if (userQuery.getAgesQuery() != null && !userQuery.getAgesQuery().isEmpty()) {
                    predicate.getExpressions().add(criteriaBuilder.and(root.get("age").in(userQuery.getAgesQuery())));
                }

            }
            return predicate;
        });
        if (userList.isEmpty()) {
            logger.info("查询不到满足条件的数据");
        } else {
            userList.forEach(user -> logger.info(user.toString()));
        }
        logger.info("动态拼接多条件查询测试结束");
    }


    /**
     * JpaSpecificationExecutor动态拼接多条件查询测试
     * 拼接方式2：与方式1效果相同
     */
    @Test
    public void findByConditionsTest2() {
        //手动模拟查询条件
//        UserQuery userQuery = null;
        UserQuery userQuery = new UserQuery();
        List<Long> ids = new ArrayList<>();
        List<Integer> ages = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(5L);
        ages.add(18);
        ages.add(24);
        userQuery.setUserNameQuery("r").setNickNameQuery("a")
                .setMinAgeQuery(null).setMaxAgeQuery(25).setIdQuery(6L)
                .setTelQuery("135").setEmailQuery("rt").setPassWordQuery("123")
                .setIdsQuery(ids).setAgesQuery(ages);
        logger.info("动态拼接多条件查询测试开始");
        List<User> userList = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            //sql语句会自动生成where 1 = 1
            predicates.add(criteriaBuilder.conjunction());
            //如果userQuery为空或者userQuery所有属性均为空会查询所有记录
            if (userQuery != null) {
                if (userQuery.getIdQuery() != null) {
                    //notEqual查询
                    predicates.add(criteriaBuilder.notEqual(root.get("id"), userQuery.getIdQuery()));
                }
                if (!StringUtils.isNullOrEmpty(userQuery.getUserNameQuery())) {
                    //like查询
                    predicates.add(criteriaBuilder.like(root.get("userName"), "%" + userQuery.getUserNameQuery() + "%"));
                }
                if (!StringUtils.isNullOrEmpty(userQuery.getNickNameQuery())) {
                    //notLike查询
                    predicates.add(criteriaBuilder.notLike(root.get("nickName"), "%" + userQuery.getNickNameQuery() + "%"));
                }
                if (!StringUtils.isNullOrEmpty(userQuery.getPassWordQuery())) {
                    //equal查询
                    predicates.add(criteriaBuilder.equal(root.get("passWord"), userQuery.getPassWordQuery()));
                }
                if (!StringUtils.isNullOrEmpty(userQuery.getEmailQuery())) {
                    //notEqual查询
                    predicates.add(criteriaBuilder.notEqual(root.get("email"), userQuery.getEmailQuery()));
                }
                if (!StringUtils.isNullOrEmpty(userQuery.getTelQuery())) {
                    //like查询
                    predicates.add(criteriaBuilder.like(root.get("tel"), "%" + userQuery.getTelQuery()));
                }
                if (userQuery.getMinAgeQuery() != null && userQuery.getMaxAgeQuery() != null) {
                    //between查询
                    predicates.add(criteriaBuilder.between(root.get("age"), userQuery.getMinAgeQuery(), userQuery.getMaxAgeQuery()));
                } else if (userQuery.getMinAgeQuery() != null) {
                    //>=查询 gt为>
                    predicates.add(criteriaBuilder.ge(root.get("age"), userQuery.getMinAgeQuery()));
                } else if (userQuery.getMaxAgeQuery() != null) {
                    //<=查询 lt为<
                    predicates.add(criteriaBuilder.le(root.get("age"), userQuery.getMaxAgeQuery()));
                }
                if (userQuery.getIdsQuery() != null && !userQuery.getIdsQuery().isEmpty()) {
                    //in 批量查询
                    predicates.add(criteriaBuilder.and(root.get("id").in(userQuery.getIdsQuery())));
                }
                if (userQuery.getAgesQuery() != null && !userQuery.getAgesQuery().isEmpty()) {
                    predicates.add(criteriaBuilder.and(root.get("age").in(userQuery.getAgesQuery())));
                }
            }
            /**
             * Predicate and(Predicate... var1)：and连接查询条件，可传入Predicate[] and连接数组内所有条件
             * Predicate or(Predicate... var1)：or连接查询条件，可传入Predicate[] or连接数组内所有条件
             * 此处所有条件均为and连接，如果有更复杂的条件连接，比如：
             * where ((a=? and b=? and c=?) or (e=? and f=?) or g=?) and x=? and y=?
             * 先and连接abc、ef，再or连接abc、ef和g作为一个条件z，再and连接xyz
             */
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
        if (userList.isEmpty()) {
            logger.info("查询不到满足条件的数据");
        } else {
            userList.forEach(user -> logger.info(user.toString()));
        }
        logger.info("动态拼接多条件查询测试结束");
    }

    /**
     * 多条件排序分页查询
     */
    @Test
    public void findByConditionsPageAndSortTest() {
        //手动模拟查询条件
//        UserQuery userQuery = null;
        UserQuery userQuery = new UserQuery();
        List<Long> ids = new ArrayList<>();
        List<Integer> ages = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(5L);
        ages.add(18);
        ages.add(24);
        userQuery.setUserNameQuery("r").setNickNameQuery("a")
                .setMinAgeQuery(null).setMaxAgeQuery(25).setIdQuery(null)
                .setTelQuery("135").setEmailQuery("rt").setPassWordQuery("123")
                .setIdsQuery(ids).setAgesQuery(ages);
        //定义排序规则 先按age降序，再按tel升序，再按id降序
        Sort.Order orderAge = new Sort.Order(Sort.Direction.DESC,"age");
        Sort.Order orderTel = new Sort.Order(Sort.Direction.ASC,"tel");
        Sort.Order orderId = new Sort.Order(Sort.Direction.DESC,"id");
        Sort sort = Sort.by(orderAge, orderTel, orderId);
        //定义分页参数，由于是测试第几页和每页记录数写死
        int pageNum = 3;
        int pageSize = 3;
        //jpa分页从0页开始，页码需要-1
        Pageable pageable = PageRequest.of(pageNum - 1 , pageSize, sort);
        logger.info("多条件排序分页查询测试开始");
        Page<User> page = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            //如果userQuery为空或者userQuery所有属性均为空会自动生成where 1 = 1
            Predicate predicate = criteriaBuilder.conjunction();
            //如果userQuery为空或者userQuery所有属性均为空会查询所有记录
            if (userQuery != null) {
                if (userQuery.getIdQuery() != null) {
                    //notEqual查询
                    predicate.getExpressions().add(criteriaBuilder.notEqual(root.get("id"), userQuery.getIdQuery()));
                }
                if (!StringUtils.isNullOrEmpty(userQuery.getUserNameQuery())) {
                    //like查询
                    predicate.getExpressions().add(criteriaBuilder.like(root.get("userName"), "%" + userQuery.getUserNameQuery() + "%"));
                }
                if (!StringUtils.isNullOrEmpty(userQuery.getNickNameQuery())) {
                    //notLike查询
                    predicate.getExpressions().add(criteriaBuilder.notLike(root.get("nickName"), "%" + userQuery.getNickNameQuery() + "%"));
                }
                if (!StringUtils.isNullOrEmpty(userQuery.getPassWordQuery())) {
                    //equal查询
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("passWord"), userQuery.getPassWordQuery()));
                }
                if (!StringUtils.isNullOrEmpty(userQuery.getEmailQuery())) {
                    //notEqual查询
                    predicate.getExpressions().add(criteriaBuilder.notEqual(root.get("email"), userQuery.getEmailQuery()));
                }
                if (!StringUtils.isNullOrEmpty(userQuery.getTelQuery())) {
                    //like查询
                    predicate.getExpressions().add(criteriaBuilder.like(root.get("tel"), "%" + userQuery.getTelQuery()));
                }
                if (userQuery.getMinAgeQuery() != null && userQuery.getMaxAgeQuery() != null) {
                    //between查询
                    predicate.getExpressions().add(criteriaBuilder.between(root.get("age"), userQuery.getMinAgeQuery(), userQuery.getMaxAgeQuery()));
                } else if (userQuery.getMinAgeQuery() != null) {
                    //>=查询 gt为>
                    predicate.getExpressions().add(criteriaBuilder.ge(root.get("age"), userQuery.getMinAgeQuery()));
                } else if (userQuery.getMaxAgeQuery() != null) {
                    //<=查询 lt为<
                    predicate.getExpressions().add(criteriaBuilder.le(root.get("age"), userQuery.getMaxAgeQuery()));
                }
                if (userQuery.getIdsQuery() != null && !userQuery.getIdsQuery().isEmpty()) {
                    //in 批量查询
                    predicate.getExpressions().add(criteriaBuilder.and(root.get("id").in(userQuery.getIdsQuery())));
                }
                if (userQuery.getAgesQuery() != null && !userQuery.getAgesQuery().isEmpty()) {
                    predicate.getExpressions().add(criteriaBuilder.and(root.get("age").in(userQuery.getAgesQuery())));
                }

            }
            return predicate;
        }, pageable);
        if (page.isEmpty()) {
            logger.info("查询不到满足条件的数据");
        } else {
            logger.info("总条数：" + page.getTotalElements());
            logger.info("总页数：" + page.getTotalPages());
            List<User> userList = page.getContent();
            userList.forEach(user -> logger.info(user.toString()));
        }
        logger.info("多条件排序分页查询测试结束");
    }


    /**
     * 多表关联一对多保存
     */
    @Transactional //开始事务支持
    @Rollback(false) //设置不回滚
    @Test
    public void oneToManySaveTest() {
        //封装保存数据
        Account account = new Account();
        account.setAccountName("rtx_titan_v");
        account.setAccountPassword("123456");
        account.setAccountAlias("cdf_dv");
        account.setAccountRank(7L);
        account.setAccountTel("13313313311");
        account.setAccountLocation("china");
        account.setAccountAddr("北京西城区");
        Order order1 = new Order();
        Order order2 = new Order();
        Order order3 = new Order();
        order1.setOrderName("索尼ps5次世代游戏主机");
        order1.setOrderDescription("索尼ps5,无索不玩");
        order1.setOrderStatus("等待卖家发货");
        order1.setOrderTotalPrice("5000");
        order1.setOrderItemCount(1);
        order1.setOrderAddr("北京西城区");
        order2.setOrderName("XBox Edit 2代");
        order2.setOrderDescription("微软精英2代,无线蓝牙手柄国行");
        order2.setOrderStatus("卖家已发货");
        order2.setOrderTotalPrice("1390");
        order2.setOrderItemCount(1);
        order2.setOrderAddr("北京西城区");
        order3.setOrderName("XBox Edit 3代");
        order3.setOrderDescription("微软精英3代,无线蓝牙手柄国行");
        order3.setOrderStatus("卖家已发货");
        order3.setOrderTotalPrice("1390");
        order3.setOrderItemCount(1);
        order3.setOrderAddr("北京西城区");
        //关联操作
        account.getOrders().add(order1);
        account.getOrders().add(order2);
        order1.setAccount(account);
        order2.setAccount(account);
        //保存操作
        //由于account和order实体设置了级联保存
        //此处任意保存其中一个order，后台会自动保存order1、order2和关联的account
        logger.info("保存开始");
        orderRepository.save(order1);
        orderRepository.save(order2);
        //保存account，会自动保存关联的order1和order2
        accountRepository.save(account);
        //此处为account关联order1和order2保存之后再关联一个新的order3保存
        account.getOrders().add(order3);
        order3.setAccount(account);
        orderRepository.save(order3);
        logger.info("保存结束");
    }

    /**
     * 多表关联一对多查询
     */
    //解决延迟加载时Session已关闭出现的LazyInitializationException
    @Transactional
    @Rollback(false)
    @Test
    public void oneToManyFindTest() {
        //查询一个账户，并获取账户的所有订单
        Optional<Account> accountOptional = accountRepository.findById(1L);
        if (!accountOptional.isPresent()) {
            logger.info("账户不存在");
        } else {
            Account account = accountOptional.get();
            logger.info("----------------账户信息----------------");
            String accountInf = account.toString();
            logger.info(accountInf);
            logger.info("----------------订单信息----------------");
            account.getOrders().forEach(order -> logger.info(order.toString()));
        }
        //查询一个订单，并获取订单所对应的账户
        Optional<Order> orderOptional = orderRepository.findById(1L);
        if (!orderOptional.isPresent()) {
            logger.info("订单不存在");
        } else {
            Order order = orderOptional.get();
            logger.info("----------------订单信息----------------");
            String orderInfo = order.toString();
            logger.info(orderInfo);
            logger.info("----------------账户信息----------------");
            String accountInfo = order.getAccount().toString();
            logger.info(accountInfo);
        }
        //Specification多表关联查询
        List<Account> accounts = accountRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            //orders为account关联对象名称，JoinType：连接方式，LEFT：左外连接，RIGHT：右外连接，INNER：内连接
            Join<Account, Order> join = root.join("orders", JoinType.LEFT);
            //distinct(true)：去除重复记录
            criteriaQuery.distinct(true);
            return criteriaBuilder.equal(join.get("orderId").as(Long.class), 3L);
        });
        if (accounts.isEmpty()) {
            logger.info("账户不存在");
        } else {
            logger.info("-------------所有账户信息包含订单-------------");
            accounts.forEach(account -> {
                String acc = account.toString();
                logger.info(acc);
                account.getOrders().forEach(order -> logger.info(order.toString()));
            });
        }
    }

    /**
     * 多表关联一对多更新
     */
    @Transactional
    @Rollback(false)
    @Test
    public void oneToManyUpdateTest() {
        //通过一方更新多方的记录
        Optional<Account> accountOpt = accountRepository.findById(1L);
        if (!accountOpt.isPresent()) {
            logger.info("账号不存在");
        } else {
            Account account = accountOpt.get();
            account.getOrders().forEach(order -> {
                if (order.getOrderId().equals(1L)) {
                    order.setOrderTotalPrice("1590");
                    order.setOrderAddr("重庆渝北区");
                }
            });
        }
        //通过多方更新一方记录
        Optional<Order> orderOpt = orderRepository.findById(1L);
        if (!orderOpt.isPresent()) {
            logger.info("订单不存在");
        } else {
            Order order = orderOpt.get();
            order.getAccount().setAccountAddr("重庆江北区");
            order.getAccount().setAccountRank(8L);
        }
    }

    /**
     * 多表关联一对多删除
     */
    @Transactional
    @Rollback(false)
    @Test
    public void oneToManyDeleteTest() {
        //account设置了级联删除，有多方关联时删除account将会同时删除关联的所有order
        //如果account没有设置级联删除，有多方关联删除一方时，默认置外键为null，如果外键不允许
        //为空，会报错，如果配置了放弃维护关联关系则不能删除
        //accountRepository.deleteById(1L);
        //只删除多方记录，直接删除会有问题，删除后再关联查询多方记录时没有生成删除语句
        //orderRepository.deleteById(3L);
        Optional<Account> accountOptional = accountRepository.findById(1L);
        if (!accountOptional.isPresent()) {
            logger.info("账号不存在");
        } else {
            Account account = accountOptional.get();
            Set<Order> orders = account.getOrders();
            logger.info("删除开始");
            for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext(); ) {
                //由于orphanRemoval = true，在一方关联多方的集合中移除多方，将会在多方删除这些记录
                if (iterator.next().getOrderId().equals(1L)) {
                    iterator.remove();
                }
            }
            accountRepository.save(account);
            logger.info("删除结束");
        }
    }

    /**
     * 多表关联多对多保存
     */
    @Transactional
    @Rollback(false)
    @Test
    public void manyToManySaveTest() {
        Role role1 = new Role();
        Role role2 = new Role();
        Role role3 = new Role();
        role1.setRoleName("admin");
        role1.setRoleType("A");
        role2.setRoleName("user");
        role2.setRoleType("U");
        role3.setRoleName("system");
        role3.setRoleType("S");
        Menu menu1 = new Menu();
        Menu menu2 = new Menu();
        Menu menu3 = new Menu();
        menu1.setMenuName("导航栏");
        menu1.setMenuHidden(false);
        menu1.setMenuPath("http://ascd/sddf/kk.img");
        menu2.setMenuName("下拉框");
        menu2.setMenuParentId(1L);
        menu2.setMenuIcon("下拉图标");
        menu3.setMenuName("确认按钮");
        menu3.setMenuIcon("方块");
        menu3.setMenuPath("http://123.123.44.56/xx");
        //关联操作
        role1.getMenus().add(menu1);
        role1.getMenus().add(menu3);
        role2.getMenus().add(menu2);
        role2.getMenus().add(menu3);
        role3.getMenus().add(menu1);
        role3.getMenus().add(menu2);
        menu1.getRoles().add(role1);
        menu1.getRoles().add(role3);
        menu2.getRoles().add(role2);
        menu2.getRoles().add(role3);
        menu3.getRoles().add(role1);
        menu3.getRoles().add(role2);
        //保存，role和menu均设置了级联保存
        menuRepository.save(menu1);
        //roleRepository.save(role1); 和save menu一样
    }

    /**
     * 多表关联多对多查询
     */
    @Transactional
    @Rollback(false)
    @Test
    public void manyToManyFindTest() {
        //查询一个角色，并获取角色的所有菜单
        Optional<Role> roleOptional = roleRepository.findById(1L);
        if (!roleOptional.isPresent()) {
            logger.info("角色不存在");
        } else {
            Role role = roleOptional.get();
            logger.info("----------------角色信息----------------");
            String roleInf = role.toString();
            logger.info(roleInf);
            logger.info("----------------菜单信息----------------");
            role.getMenus().forEach(menu -> logger.info(menu.toString()));
        }
        //查询一个菜单，并获取菜单的角色
        Optional<Menu> menuOptional = menuRepository.findById(1L);
        if (!menuOptional.isPresent()) {
            logger.info("菜单不存在");
        } else {
            Menu menu = menuOptional.get();
            logger.info("----------------菜单信息----------------");
            String menuInfo = menu.toString();
            logger.info(menuInfo);
            logger.info("----------------角色信息----------------");
            menu.getRoles().forEach(role -> logger.info(role.toString()));
        }
        //Specification多表关联查询
        List<Role> roles = roleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            //menus为role关联对象名称，JoinType：连接方式，LEFT：左外连接，RIGHT：右外连接，INNER：内连接
            Join<Role, Menu> join = root.join("menus", JoinType.LEFT);
            //distinct(true)：去除重复记录
            criteriaQuery.distinct(true);
            return criteriaBuilder.lt(join.get("menuId").as(Long.class), 3L);
        });
        if (roles.isEmpty()) {
            logger.info("角色不存在");
        } else {
            logger.info("-------------所有角色信息包含菜单-------------");
            roles.forEach(role -> {
                String rol = role.toString();
                logger.info(rol);
                role.getMenus().forEach(menu -> logger.info(menu.toString()));
            });
        }
    }

    /**
     * 多表关联多对多更新
     */
    @Transactional
    @Rollback(false)
    @Test
    public void manyToManyUpdateTest() {
        Optional<Role> roleOptional = roleRepository.findById(1L);
        if (!roleOptional.isPresent()) {
            logger.info("角色不存在");
        } else {
            Role roles = roleOptional.get();
            //通过id为1的role修改role关联的id为1的menu
            //根据id为1的menu修改menu关联的id为3的role
            roles.getMenus().forEach(menu -> {
                if (menu.getMenuId().equals(1L)) {
                    menu.setMenuHidden(true);
                    menu.setMenuName("左侧导航栏");
                    menu.getRoles().forEach(role -> {
                        if (role.getRoleId().equals(3L)) {
                            role.setRoleName("vip");
                            role.setRoleType("V");
                        }
                    });
                }
            });
        }
    }

    /**
     * 多表关联多对多删除
     */
    @Transactional
    @Rollback(false)
    @Test
    public void manyToManyDeleteTest() {
        //准备删除的role
        Optional<Role> roleOptional = roleRepository.findById(1L);
        if (!roleOptional.isPresent()) {
            logger.info("角色不存在");
        } else {
            Role role1 = roleOptional.get();
            //关联关系解除（id为1的role-->id为1的menu，id为2的menu）
            //删除中间表的关联记录
            role1.getMenus().forEach(menu -> {
                menu.getRoles().remove(role1);
            });
            //不删除role的情况，重新关联（id为1的role-->id为3的menu）
            /*Optional<Menu> menuOptional = menuRepository.findById(3L);
            if (!menuOptional.isPresent()) {
                logger.info("菜单不存在");
            } else {
                Menu menu3 = menuOptional.get();
                role1.getMenus().add(menu3);
                menu3.getRoles().add(role1);
                //更新关联，可以省略，省略也会更新中间表关联关系
                menuRepository.save(menu3);
            }*/
            //删除role
            roleRepository.delete(role1);
        }
    }
}
