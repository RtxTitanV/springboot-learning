package com.rtxtitanv.repository;

import com.rtxtitanv.model.User;
import com.rtxtitanv.projections.NameOnly;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.repository.UserRepository
 * @description UserRepository用来操作用户集合，此处的集合是MongoDB中的术语
 * @date 2021/5/26 18:34
 */
public interface UserRepository extends MongoRepository<User, ObjectId> {

    /**
     * 方法命名规则之按性别和年龄查询
     *
     * @param gender 性别
     * @param age    年龄
     * @return 查询结果集
     */
    List<User> findByGenderAndAge(String gender, Integer age);

    /**
     * 方法命名规则之查询真名不等于且年龄大于且积分小于等于且id在指定集合中的文档
     *
     * @param realname  用户真实姓名
     * @param age       年龄
     * @param userPoint 用户积分
     * @param ids       id列表
     * @return 查询结果集
     */
    List<User> findByRealnameNotAndAgeGreaterThanAndUserPointLessThanEqualAndIdIn(String realname, Integer age,
        Integer userPoint, List<ObjectId> ids);

    /**
     * 方法命名规则之分页查询出生日期大于指定日期并且用户积分在指定开区间与按用户名模糊查询的并集
     *
     * @param birthday     出生日期
     * @param minUserPoint 最小用户积分
     * @param maxUserPoint 最大用户积分
     * @param username     用于模糊查询用户名的字符串
     * @param pageable     分页参数
     * @return 查询结果
     */
    Page<User> findByBirthdayAfterAndUserPointBetweenOrUsernameLike(LocalDateTime birthday, Integer minUserPoint,
        Integer maxUserPoint, String username, Pageable pageable);

    /**
     * 方法命名规则之按年龄查询单条文档，如果发现多条文档，则会抛出异常
     *
     * @param age 年龄
     * @return 查询结果
     */
    User findByAge(Integer age);

    /**
     * 方法命名规则之统计密码以指定后缀结尾的文档总数
     *
     * @param password 用于与密码后缀匹配的字符串
     * @return 密码以指定后缀结尾的文档总数
     */
    Long countByPasswordEndingWith(String password);

    /**
     * 方法命名规则之限制查询，返回按用户LV降序排序的第一条文档
     *
     * @return 查询结果
     */
    User findFirstByOrderByUserLevelDesc();

    /**
     * 方法命名规则之限制查询，返回按用户LV升序排序的第一条文档
     *
     * @return 查询结果
     */
    User findTopByOrderByUserLevelAsc();

    /**
     * 方法命名规则之限制查询，返回查询结果中的前五条文档并分页
     *
     * @param gender   性别
     * @param pageable 分页参数
     * @return 查询结果
     */
    Page<User> findFirst5ByGender(String gender, Pageable pageable);

    /**
     * 方法命名规则之删除按密码模糊查询出的文档
     *
     * @param password 用于模糊查询密码的字符串
     * @return 删除的结果集
     */
    @Transactional(rollbackFor = Exception.class)
    List<User> deleteByPasswordNotLike(String password);

    /**
     * 方法命名规则之删除年龄不在指定集合中的文档
     *
     * @param ages 年龄列表
     * @return 删除文档数
     */
    @Transactional(rollbackFor = Exception.class)
    Long deleteByAgeNotIn(List<Integer> ages);

    /**
     * 方法命名规则之删除邮箱以指定前缀开头的第一条文档
     *
     * @param email 用于与邮箱前缀匹配的字符串
     * @return 删除的结果
     */
    @Transactional(rollbackFor = Exception.class)
    Optional<User> deleteByEmailStartingWith(String email);

    /**
     * 查询用户积分大于等于指定值的文档并排序
     *
     * @param userPoint 用户积分
     * @param sort      排序参数
     * @return 查询结果集
     */
    List<User> findByUserPointGreaterThanEqual(Integer userPoint, Sort sort);

    /**
     * 基于MongoDB JSON的查询方法，查询指定性别并且年龄小于指定值的文档，并限制映射到Java对象的字段集
     *
     * @param gender 性别
     * @param age    年龄
     * @return 查询结果集
     */
    @Query(value = "{ 'gender' : ?0, 'age' : { '$lt' : ?1 } }", fields = "{ 'realname' : 1, 'gender' : 1, 'age' : 1 }")
    List<User> findByTheUserGenderAndAgeLessThan(String gender, Integer age);

    /**
     * 基于MongoDB JSON的查询方法，查询指定用户LV的文档并按年龄降序排序
     *
     * @param userLevel 用户LV
     * @return 查询结果集
     */
    @Query(value = "{ 'user_level' : ?0 }", sort = "{ 'age' : -1 }")
    List<User> findByTheUserLevelAndSortDescByAge(Byte userLevel);

    /**
     * 用SpEL表达式进行基于JSON的查询，查询年龄在指定开区间中的文档
     *
     * @param age0 年龄参数1
     * @param age1 年龄参数2
     * @return 查询结果集
     */
    @Query(value = "{ 'age' : { '$gt' : ?#{[0]}, '$lt' : ?#{[1]} } }")
    List<User> findByQueryWithExpression(Integer age0, Integer age1);

    /**
     * 用SpEL表达式进行基于JSON的查询，查询在指定用户LV集合的文档的用户名和用户真实姓名
     *
     * @param userLevels0 用户积分列表
     * @return List<NameOnly> 名称列表，projections接口NameOnly只包含用户名和用户真实姓名
     */
    @Query(value = "{ 'user_level' : { '$in' : ?#{[0]} } }")
    List<NameOnly> findNameByUserLevelIn(List<Byte> userLevels0);
}