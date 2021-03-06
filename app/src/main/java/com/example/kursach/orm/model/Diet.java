package com.example.kursach.orm.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "diet")
public class Diet{
    public Diet() {
    }

    public Diet(String name, String time, String weight, String description, String neuron) {
        this.name = name;
        this.time = time;
        this.weight = weight;
        this.description = description;
        this.neuron = neuron;
    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String name;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String time;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String weight;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String description;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String neuron;

    public int getId() {
        return id;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getNeuron() {
        return neuron;
    }

    public void setNeuron(String neuron) {
        this.neuron = neuron;
    }
}
