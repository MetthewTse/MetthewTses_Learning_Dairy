package skill.stream;

import bean.Role;
import bean.User;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * jdk1.8 stream usage
 */
public class StreamUsage {
    public static void main(String[] args) {
        List<Role> roles =  new ArrayList<>();
        Role role = new Role();
        role.setId("1");
        role.setName("role1");
        role.setDesc("desc");
        roles.add(role);
        role = new Role();
        role.setId("2");
        role.setName("role2");
        role.setDesc("desc");
        roles.add(role);

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId("1");
        user.setName("test");
        user.setPhone("123456789");
        user.setEmail(null);
        users.add(user);
        user = new User();
        user.setId("2");
        user.setName("test2");
        user.setPhone("123456789");
        user.setEmail(null);
        user.setRoles(roles);
        users.add(user);
        predicateAndFilterUsage(users);
        optionalUsage(users);
    }

    public static void predicateAndFilterUsage(List<User> users){
        System.out.println("before filter predicate result"+ JSONUtil.toJsonStr(users));
        Predicate<User> predicate = userPredicate -> userPredicate.getId().equals("1");
        List<User> collect = users.stream().filter(predicate).collect(Collectors.toList());
        System.out.println("after filter predicate result"+ JSONUtil.toJsonStr(collect));
    }

    public static void optionalUsage(List<User> users){
        User user = users.get(1);
        Optional.ofNullable(user)
                .map(User::getEmail)
                .orElseThrow(()->new RuntimeException("email is null"));
    }
}
