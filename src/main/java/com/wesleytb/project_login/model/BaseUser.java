package com.wesleytb.project_login.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class BaseUser implements IUser {

    public void newObject(IUser newObject) {
        this.setId(newObject.getId());
        this.updateData(newObject);
    }

    public BaseUser(BaseUser object) {
        this.setId(object.getId());
        this.setName(object.getName());
        this.setAge(object.getAge());
        this.setEmail(object.getEmail());
        this.setPassword(object.getPassword());
    }

    public void updateData(IUser newData) {
        this.setName(this.getOrDefault(newData.getName(), this.getName()));
        this.setAge(this.getOrDefault(newData.getAge(), this.getAge()));
        this.setEmail(this.getOrDefault(newData.getEmail(), this.getEmail()));
        this.setPassword(this.getOrDefault(newData.getPassword(), this.getPassword()));
    }

    private <T> T getOrDefault(T value, T defaultValue) {
        return (value != null) ? value : defaultValue;
    }
}
