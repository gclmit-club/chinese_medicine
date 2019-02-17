package club.gclmit.mc.test;

import club.gclmit.mc.entity.User;
import club.gclmit.mc.service.UserService;
import club.gclmit.mc.service.impl.UserServiceImpl;

import java.util.List;

public class UserServiceTest {

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        List<User> users = userService.getUsers();

        users.forEach(user -> {
           System.out.println("用户列表"+user);
        });
////
//        System.out.println("创建用户判断："+userService.createUser(new User(System.currentTimeMillis(), "www", "123456")));
//        System.out.println("创建用户判断："+userService.createUser(new User(System.currentTimeMillis(), "admin", "123456")));
////
//        User user = userService.userLogin("admin4", "123456");
//        System.out.println("登陆成功用户： "+user);

        String s = userService.updatePassword("gclmit", "123456", "gclmit");

        System.out.println("修改密码提示："+s);

//        System.out.println("创建用户判断："+userService.createUser(new User(System.currentTimeMillis(), "test1", "123456")));

        List<User> users2 = userService.getUsers();

        users2.forEach(user1 -> {
            System.out.println("修改后的用户列表： "+user1);
        });

    }
}
