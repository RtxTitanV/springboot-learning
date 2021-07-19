package com.rtxtitanv.controller;

import com.rtxtitanv.model.Account;
import com.rtxtitanv.model.Order;
import com.rtxtitanv.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.controller.FreeMarkerController
 * @description FreeMarkerController
 * @date 2021/7/4 13:52
 */
@RequestMapping("/fm")
@Controller
public class FreeMarkerController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("hello", "<h1>Hello FreeMarker</h1>");
        return "hello";
    }

    @GetMapping("/model/test1")
    public String dataModel1(Model model) {
        List<Order> orders = new ArrayList<>();
        Account account = new Account().setAccountId(1L).setAccountName("MyAccount").setAccountPassword("123456")
            .setUser(new User(1L, "RtxTitanV", "654321"));
        orders.add(new Order().setOrderId(1L).setOrderNumber("785698232657798568").setOrderDescription("二两麻辣牛肉面")
            .setAccount(account));
        orders.add(new Order().setOrderId(2L).setOrderNumber("785938232669132551").setOrderDescription("三两三鲜米线")
            .setAccount(account));
        orders.add(new Order().setOrderId(3L).setOrderNumber("793382623157348612").setOrderDescription("二两老麻抄手")
            .setAccount(account));
        account.setOrders(orders);
        model.addAttribute("account", account);
        return "model1";
    }

    @GetMapping("/model/test2")
    public String dataModel2(Map<String, Object> map) {
        List<Order> orders = new ArrayList<>();
        Account account = new Account().setAccountId(1L).setAccountName("MyAccount").setAccountPassword("123456")
            .setUser(new User(1L, "RtxTitanV", "654321"));
        orders.add(new Order().setOrderId(1L).setOrderNumber("785698232657798568").setOrderDescription("二两麻辣牛肉面")
            .setAccount(account));
        orders.add(new Order().setOrderId(2L).setOrderNumber("785938232669132551").setOrderDescription("三两三鲜米线")
            .setAccount(account));
        orders.add(new Order().setOrderId(3L).setOrderNumber("793382623157348612").setOrderDescription("二两老麻抄手")
            .setAccount(account));
        map.put("accountId", account.getAccountId());
        map.put("accountName", account.getAccountName());
        map.put("accountPassword", account.getAccountPassword());
        map.put("user", account.getUser());
        map.put("orders", orders);
        return "model2";
    }

    @GetMapping("/model/test3")
    public String dataModel3(Map<String, Object> map) {
        List<Order> orders = new ArrayList<>();
        Account account = new Account().setAccountId(1L).setAccountName("MyAccount").setAccountPassword("123456")
            .setUser(new User(1L, "RtxTitanV", "654321"));
        orders.add(new Order().setOrderId(1L).setOrderNumber("785698232657798568").setOrderDescription("二两麻辣牛肉面")
            .setAccount(account));
        orders.add(new Order().setOrderId(2L).setOrderNumber("785938232669132551").setOrderDescription("三两三鲜米线")
            .setAccount(account));
        orders.add(new Order().setOrderId(3L).setOrderNumber("793382623157348612").setOrderDescription("二两老麻抄手")
            .setAccount(account));
        account.setOrders(orders);
        map.put("account", account);
        return "model3";
    }

    @GetMapping("/directive/assign")
    public String assignDirective(Model model) {
        User user = new User(1L, "RtxTitanV", "654321");
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order().setOrderId(1L).setOrderNumber("785698232657798568").setOrderDescription("二两麻辣牛肉面"));
        orders.add(new Order().setOrderId(2L).setOrderNumber("785938232669132551").setOrderDescription("三两砂锅三鲜米线"));
        orders.add(new Order().setOrderId(3L).setOrderNumber("793382623157348612").setOrderDescription("二两老麻抄手"));
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        return "directive-assign";
    }

    @GetMapping("/directive/attempt-recover")
    public String attemptAndRecoverDirective(Model model) {
        User user = new User(1L, "RtxTitanV", "654321");
        model.addAttribute("user", user);
        return "directive-attempt-recover";
    }

    @GetMapping("/directive/compress")
    public String compressDirective() {
        return "directive-compress";
    }

    @GetMapping("/directive/escape-noescape")
    public String escapeAndNoescapeDirective(Model model) {
        model.addAttribute("hello", "<h1>Hello FreeMarker</h1>");
        return "directive-escape-noescape";
    }

    @GetMapping("/directive/autoesc-noautoesc")
    public String autoescDirective(Model model) {
        model.addAttribute("hello", "<h1>Hello FreeMarker</h1>");
        return "directive-autoesc-noautoesc";
    }

    @GetMapping("/directive/function-return")
    public String functionAndReturnDirective(Model model) {
        model.addAttribute("num", 5);
        return "directive-function-return";
    }

    @GetMapping("/directive/global")
    public String globalDirective(Model model) {
        model.addAttribute("username", "ZhaoYun");
        return "directive-global";
    }

    @GetMapping("/directive/if")
    public String ifDirective(Model model) {
        model.addAttribute("grade", 85);
        return "directive-if";
    }

    @GetMapping("/directive/import")
    public String importDirective(Model model) {
        model.addAttribute("user", new User(1L, "RtxTitanV", "654321"));
        return "directive-import";
    }

    @GetMapping("/directive/include")
    public String includeDirective(Model model) {
        model.addAttribute("user", new User(1L, "RtxTitanV", "654321"));
        return "directive-include";
    }

    @GetMapping("/directive/list")
    public String listDirective(Model model) {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "刘备", "123132"));
        users.add(new User(2L, "关羽", "321231"));
        users.add(new User(3L, "张飞", "213312"));
        users.add(new User(4L, "赵云", "132213"));
        users.add(new User(5L, "马超", "312123"));
        model.addAttribute("users", users);
        Map<String, Object> map = new HashMap<>(16);
        map.put("user1", new User(1L, "刘备", "123132"));
        map.put("user2", new User(2L, "关羽", "321231"));
        map.put("user3", new User(3L, "张飞", "213312"));
        map.put("user4", new User(4L, "赵云", "132213"));
        map.put("user5", new User(5L, "马超", "312123"));
        model.addAttribute("userMap", map);
        return "directive-list";
    }

    @GetMapping("/directive/macro")
    public String macroDirective(Model model) {
        model.addAttribute("num", 5);
        return "directive-macro";
    }

    @GetMapping("/directive/switch")
    public String switchDirective(Model model) {
        model.addAttribute("rank", 3);
        return "directive-switch";
    }

    @GetMapping("/expression/specify-value")
    public String specifyValue() {
        return "expression-specify-value";
    }

    @GetMapping("/expression/string-operations")
    public String stringOperations(Model model) {
        model.addAttribute("user", new User(1L, "RtxTitanV", "654321"));
        return "expression-string-operations";
    }

    @GetMapping("/expression/sequence-operations")
    public String sequenceOperations(Model model) {
        List<User> users1 = new ArrayList<>();
        users1.add(new User(1L, "刘备", "123132"));
        users1.add(new User(2L, "关羽", "321231"));
        users1.add(new User(3L, "张飞", "213312"));
        User[] users2 = {new User(4L, "赵云", "132213"), new User(5L, "马超", "312123")};
        model.addAttribute("users1", users1);
        model.addAttribute("users2", users2);
        return "expression-sequence-operations";
    }

    @GetMapping("/expression/hash-operations")
    public String hashOperations(Model model) {
        Map<String, Object> map1 = new HashMap<>(16);
        map1.put("user1", new User(1L, "刘备", "123132"));
        map1.put("user2", new User(2L, "关羽", "321231"));
        Map<String, Object> map2 = new HashMap<>(16);
        map2.put("user1", new User(3L, "张飞", "213312"));
        map2.put("user4", new User(4L, "赵云", "132213"));
        map2.put("user5", new User(5L, "马超", "312123"));
        model.addAttribute("map1", map1);
        model.addAttribute("map2", map2);
        return "expression-hash-operations";
    }

    @GetMapping("/expression/operation")
    public String operation(Model model) {
        model.addAttribute("x", 10);
        model.addAttribute("y", 3);
        return "expression-operation";
    }

    @GetMapping("/expression/missing")
    public String missing(Model model) {
        model.addAttribute("user", new User(1L, null, null));
        return "expression-missing";
    }

    @GetMapping("/built-ins/strings")
    public String builtInsStrings(Model model) {
        model.addAttribute("user",
            new User(1L, "SpringBoot-FreeMarker-Strings-Test", "   SpringBoot _Free-Marker  . Strings,  @Test ? !  "));
        model.addAttribute("str1", "springboot freemarker strings test");
        model.addAttribute("str2", "SpringBoot FreeMarker Strings Test");
        return "built-ins-strings";
    }

    @GetMapping("/built-ins/numbers")
    public String builtInsNumbers(Model model) {
        model.addAttribute("num", 888888);
        model.addAttribute("num2", 66.6658932);
        return "built-ins-numbers";
    }

    @GetMapping("/built-ins/date")
    public String builtInsDate(Model model) {
        model.addAttribute("date", new Date());
        return "built-ins-date";
    }

    @GetMapping("/built-ins/booleans")
    public String builtInsBooleans(Model model) {
        model.addAttribute("grade", 85);
        return "built-ins-booleans";
    }

    @GetMapping("/built-ins/sequences")
    public String builtInsSequences(Model model) {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "刘备", "123132"));
        users.add(new User(2L, "关羽", "321231"));
        users.add(new User(3L, "张飞", "213312"));
        users.add(new User(4L, "赵云", "132213"));
        users.add(new User(5L, "马超", "312123"));
        String[] colors = {"red", "blue", "green", "yellow", "blue", "green", "white", "purple", "cyan", "orange"};
        model.addAttribute("users", users);
        model.addAttribute("colors", colors);
        return "built-ins-sequences";
    }

    @GetMapping("/built-ins/hashes")
    public String builtInsHashes(Model model) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("user1", new User(1L, "刘备", "123132"));
        map.put("user2", new User(2L, "关羽", "321231"));
        map.put("user3", new User(3L, "张飞", "213312"));
        map.put("user4", new User(4L, "赵云", "132213"));
        map.put("user5", new User(5L, "马超", "312123"));
        model.addAttribute("userMap", map);
        return "built-ins-hashes";
    }
}