package ru.klimakov.taj.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;;

public class Person implements Persistable<Integer> {
    @Id
    private Integer id;
    private String name;
    private int age;

    @Transient
    @JsonIgnore
    private boolean isNew = true;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
