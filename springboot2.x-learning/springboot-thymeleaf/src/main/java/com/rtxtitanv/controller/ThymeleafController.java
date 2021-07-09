package com.rtxtitanv.controller;

import com.rtxtitanv.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.controller.ThymeleafController
 * @description ThymeleafController
 * @date 2021/7/3 19:23
 */
@Controller
public class ThymeleafController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("hello", "<h1>Hello Thymeleaf</h1>");
        return "hello";
    }

    @GetMapping("/variable")
    public String variable(Model model) {
        model.addAttribute("user", new User(1L, "赵云", "qwe123"));
        return "variable";
    }

    @GetMapping("/asterisk")
    public String star(Model model) {
        model.addAttribute("user", new User(1L, "赵云", "qwe123"));
        return "asterisk";
    }

    @GetMapping("/url")
    public String url(Model model) {
        model.addAttribute("user", new User(1L, "赵云", "qwe123"));
        model.addAttribute("url", "/user/update");
        return "url";
    }

    @GetMapping("/literal")
    public String literal(Model model) {
        model.addAttribute("flag", false);
        return "literal";
    }

    @GetMapping("/text")
    public String text(Model model) {
        model.addAttribute("user", new User(1L, "赵云", "qwe123"));
        return "text";
    }

    @GetMapping("/operation")
    public String operator(Model model) {
        model.addAttribute("x", 10);
        model.addAttribute("y", 3);
        return "operation";
    }

    @GetMapping("/conditional/expr")
    public String conditionExpr(Model model) {
        model.addAttribute("grade", 85);
        model.addAttribute("age", null);
        return "conditional-expr";
    }

    @GetMapping("/attr")
    public String attr(Model model) {
        model.addAttribute("success", "成功");
        model.addAttribute("active", true);
        return "attr";
    }

    @GetMapping("/conditional")
    public String condition(Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("age", 10);
        map.put("userLevel", 6);
        map.put("rank", 5);
        model.addAllAttributes(map);
        return "conditional";
    }

    @GetMapping("/each/list")
    public String eachList(ModelMap model) {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "刘备", "123132"));
        users.add(new User(2L, "关羽", "321231"));
        users.add(new User(3L, "张飞", "213312"));
        model.addAttribute("users", users);
        return "each-list";
    }

    @GetMapping("/each/map")
    public String eachMap(Model model) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("user1", new User(1L, "刘备", "123132"));
        map.put("user2", new User(2L, "关羽", "321231"));
        map.put("user3", new User(3L, "张飞", "213312"));
        model.addAttribute("userMap", map);
        return "each-map";
    }

    @GetMapping("/each/array")
    public String eachArray(Model model) {
        User[] users = {new User(1L, "刘备", "123132"), new User(2L, "关羽", "321231"), new User(3L, "张飞", "213312")};
        model.addAttribute("users", users);
        return "each-array";
    }

    @GetMapping("/layout")
    public String layout(Model model) {
        model.addAttribute("flag", true);
        model.addAttribute("var1", "参数1");
        model.addAttribute("var2", "参数2");
        model.addAttribute("user", new User(1L, "赵云", "qwe123"));
        return "layout";
    }

    @GetMapping("/layout/home")
    public String layoutHome(Model model) {
        model.addAttribute("condition", false);
        return "layout-home";
    }

    @GetMapping("/local")
    public String local(Model model) {
        User[] users = {new User(1L, "刘备", "123132"), new User(2L, "关羽", "321231"), new User(3L, "张飞", "213312")};
        model.addAttribute("users", users);
        return "local";
    }

    @GetMapping("/block")
    public String block(Model model) {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "刘备", "123132"));
        users.add(new User(2L, "关羽", "321231"));
        users.add(new User(3L, "张飞", "213312"));
        model.addAttribute("users", users);
        return "block";
    }

    @GetMapping("/inline")
    public String inline(Model model) {
        model.addAttribute("user", new User(1L, "<b>赵云</b>", "this is \"pass\" word"));
        Map<String, Object> map = new HashMap<>(16);
        map.put("user1", new User(1L, "刘备", "123132"));
        map.put("user2", new User(2L, "关羽", "321231"));
        map.put("user3", new User(3L, "张飞", "213312"));
        model.addAttribute("userMap", map);
        model.addAttribute("element", "h1");
        model.addAttribute("align", "center");
        model.addAttribute("color", "#2876A7");
        return "inline";
    }

    @GetMapping(value = {"/index", "/"})
    public String index() {
        return "i18n";
    }

    @GetMapping("/dates")
    public String dates(Model model) {
        model.addAttribute("date", new Date());
        return "dates";
    }

    @GetMapping("/numbers")
    public String numbers(Model model) {
        Integer[] numArray = {1000, 666, 88888};
        model.addAttribute("num", 99999);
        model.addAttribute("num2", 66.6658932);
        model.addAttribute("nums", numArray);
        return "numbers";
    }

    @GetMapping("/strings")
    public String strings(Model model) {
        model.addAttribute("user", new User(1L, "SpringBoot-Thymeleaf-Strings-Test", ""));
        model.addAttribute("str", "SpringBoot Thymeleaf Strings Test");
        model.addAttribute("strs", new String[] {"SpringBoot", "Thymeleaf", "Strings", "Test"});
        return "strings";
    }
}