package com.example.kursach.orm.dao;

import java.sql.SQLException;

import com.example.kursach.orm.model.Diet;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.util.List;

public class DietDAO extends BaseDaoImpl<Diet, Integer>{

    public DietDAO(ConnectionSource connectionSource,
                      Class<Diet> dataClass) throws SQLException{
        super(connectionSource, dataClass);
    }

    public List<Diet> getAllDiets() throws SQLException{
        return this.queryForAll();
    }

    public Diet getDietById(int id) throws SQLException {
        return this.queryForId(id);
    }

    public void deleteDiet(Diet diet) throws SQLException {
        this.delete(diet);
    }

    public void createDiet(Diet diet) throws SQLException {
        this.create(diet);
    }

    public void updateDiet(Diet diet) throws SQLException {
        this.update(diet);
    }
}