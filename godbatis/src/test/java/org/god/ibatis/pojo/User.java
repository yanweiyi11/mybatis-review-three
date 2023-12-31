package org.god.ibatis.pojo;

/**
 * Package: org.god.ibatis.pojo
 * Date: 2023/8/4 14:05
 * Description: null
 */
public class User {
    private String id;
    private String name;
    private String age;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public User(String id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
