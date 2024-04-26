package bean;

import lombok.Data;

import java.util.List;

/**
 * user bean
 * @author phile
 * @date 2024/04/26
 */
@Data
public class User {
    /**
     * primary key
     */
    private String id;
    /**
     * user name
     */
    private String name;
    /**
     * user password
     */
    private String password;
    /**
     * user phone
     */
    private String phone;
    /**
     * user email
     */
    private String email;

    private List<Role> roles;

    private Role role;
}
