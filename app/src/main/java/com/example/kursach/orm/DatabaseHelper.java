package com.example.kursach.orm;

import android.content.Context;
import java.sql.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Created by 1 on 12.05.2016.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    private static final String DATABASE_NAME ="diet1.db";

    private static final int DATABASE_VERSION = 2;

    private DietDAO dietDao = null;

    private UserDAO userDao = null;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource){
        try
        {
            TableUtils.createTable(connectionSource, Diet.class);
            TableUtils.createTable(connectionSource, User.class);
        }
        catch (SQLException e){
            Log.e(TAG, "error creating DB " + DATABASE_NAME);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVer,
                          int newVer){
        try{
            TableUtils.dropTable(connectionSource, Diet.class, true);
            TableUtils.dropTable(connectionSource, User.class, true);
            onCreate(db, connectionSource);
        }
        catch (SQLException e){
            Log.e(TAG, "error upgrading db " + DATABASE_NAME + "from ver " + oldVer);
            throw new RuntimeException(e);
        }
    }

    public DietDAO getDietDAO() throws SQLException {
        if (dietDao == null) {
            dietDao = new DietDAO(getConnectionSource(), Diet.class);
        }
        return dietDao;
    }

    public UserDAO getUserDAO() throws SQLException {
        if (userDao == null) {
            userDao = new UserDAO(getConnectionSource(), User.class);
        }
        return userDao;
    }

    @Override
    public void close(){
        super.close();
        dietDao = null;
        userDao = null;
    }
}
