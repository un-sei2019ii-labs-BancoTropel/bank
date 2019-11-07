package co.edu.unal.se1.dataAccess.db;

import androidx.room.RoomDatabase;

import co.edu.unal.se1.dataAccess.dao.UserDao;
import co.edu.unal.se1.dataAccess.dao.AccDao;
import co.edu.unal.se1.dataAccess.dao.TransDao;

import co.edu.unal.se1.dataAccess.model.Transaction;
import co.edu.unal.se1.dataAccess.model.UserInfo;
import co.edu.unal.se1.dataAccess.model.UserAcc;

@androidx.room.Database(entities = {UserInfo.class, UserAcc.class, Transaction.class}, version = 1)

public abstract class Database extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract AccDao accDao();

    public abstract TransDao transDao();

}

