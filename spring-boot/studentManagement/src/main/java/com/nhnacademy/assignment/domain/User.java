package com.nhnacademy.assignment.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;

    private String password;

    private int age;

    private String name;

    public static User create(String id, String password) {
        return new User(id, password);
    }

    private User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    private static final String MASK = "*****";

    public static User constructPasswordMaskedUser(User user) {
        User newUser = User.create(user.getId(), MASK);
        newUser.setAge(user.getAge());
        newUser.setName(user.getName());

        return newUser;
    }

}
