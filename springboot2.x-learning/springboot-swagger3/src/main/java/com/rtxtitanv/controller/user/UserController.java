package com.rtxtitanv.controller.user;

import com.rtxtitanv.model.CommonResult;
import com.rtxtitanv.model.User;
import com.rtxtitanv.service.UserService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.controller.user.UserController
 * @description UserController
 * @date 2021/6/7 12:47
 */
@Api(value = "用户模块", tags = "用户管理", description = "用户的增删改查", protocols = "http")
@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "创建用户", notes = "根据User对象保存用户")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(value = "用户id", name = "id", required = true, defaultValue = "1", dataTypeClass = Long.class,
            paramType = "query"),
        @ApiImplicitParam(value = "用户名", name = "username", required = true, defaultValue = "admin",
            dataTypeClass = String.class, paramType = "query"),
        @ApiImplicitParam(value = "密码", name = "password", required = true, defaultValue = "admin",
            dataTypeClass = String.class, paramType = "query")})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "保存用户成功"),
        @ApiResponse(code = 400, message = "无效参数", response = CommonResult.class)})
    @PostMapping("/save")
    public CommonResult<User> saveUser(@ApiIgnore User user) {
        return userService.saveUser(user);
    }

    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "查询所有用户成功")})
    @GetMapping("/findAll")
    public CommonResult<List<User>> findUserAll() {
        return userService.findUserAll();
    }

    @ApiOperation(value = "根据id查询用户", notes = "查询指定id用户")
    @ApiImplicitParam(value = "用户id", name = "id", required = true, defaultValue = "1", dataTypeClass = Long.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "根据id查询用户成功"),
        @ApiResponse(code = 400, message = "无效参数", response = CommonResult.class)})
    @GetMapping("/findById/{id}")
    public CommonResult<User> findUserById(@PathVariable(value = "id") Long id) {
        return userService.findUserById(id);
    }

    @ApiOperation(value = "根据id更新用户", notes = "更新指定id用户")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "根据id更新用户成功"),
        @ApiResponse(code = 400, message = "无效参数", response = CommonResult.class),
        @ApiResponse(code = 404, message = "用户不存在", response = CommonResult.class)})
    @PutMapping("/updateById")
    public CommonResult<User> updateUserById(@RequestBody User user) {
        return userService.updateUserById(user);
    }

    @ApiOperation(value = "根据id删除用户", notes = "删除指定id用户")
    @ApiImplicitParam(value = "用户id", name = "id", required = true, defaultValue = "1", dataTypeClass = Long.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "根据id删除用户成功"),
        @ApiResponse(code = 400, message = "无效参数", response = CommonResult.class),
        @ApiResponse(code = 404, message = "用户不存在", response = CommonResult.class)})
    @DeleteMapping("/deleteById/{id}")
    public CommonResult<User> deleteUserById(@PathVariable(value = "id") Long id) {
        return userService.deleteUserById(id);
    }

    @ApiOperation(value = "删除所有用户", notes = "删除所有用户")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "删除所有用户成功"),
        @ApiResponse(code = 404, message = "用户不存在", response = CommonResult.class)})
    @DeleteMapping("/deleteAll")
    public CommonResult<List<User>> deleteUserAll() {
        return userService.deleteUserAll();
    }
}