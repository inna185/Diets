package com.example.kursach.orm.model;

import com.example.kursach.orm.model.Diet;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "user")
public class User {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String login;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String password;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String name;

    @DatabaseField(canBeNull = true, dataType = DataType.STRING)
    private String weight;

    @DatabaseField(canBeNull = true, dataType = DataType.STRING)
    private String growth;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String age;

    @DatabaseField(canBeNull = true, dataType = DataType.STRING)
    private String goal;

    @DatabaseField(foreign = true)
    private Diet diet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getGrowth() {
        return growth;
    }

    public void setGrowth(String growth) {
        this.growth = growth;
    }

    public Diet getDiet() {
        return diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}
