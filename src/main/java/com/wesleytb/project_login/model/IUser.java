package com.wesleytb.project_login.model;

public interface IUser {

    Long getId();
    void setId(Long id);

    String getName();
    void setName(String name);

    Short getAge();
    void setAge(Short age);

    String getEmail();
    void setEmail(String email);

    String getPassword();
    void setPassword(String password);
}
