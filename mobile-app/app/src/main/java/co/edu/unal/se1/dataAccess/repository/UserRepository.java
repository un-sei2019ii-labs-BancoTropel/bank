package co.edu.unal.se1.dataAccess.repository;

import android.content.Context;
import androidx.room.Room;
import java.util.List;

import co.edu.unal.se1.dataAccess.dao.AccDao;
import co.edu.unal.se1.dataAccess.db.Database;
import co.edu.unal.se1.dataAccess.model.UserAcc;
import co.edu.unal.se1.dataAccess.model.UserInfo;
import co.edu.unal.se1.dataAccess.model.Transaction;


public class UserRepository {

    private String DB_NAME = "se1_db_bank";

    private Database database;
    public UserRepository(Context context) {
        database = Room.databaseBuilder(context, Database.class, DB_NAME).
                allowMainThreadQueries().build();
    }

    public List<UserInfo> getAllUsers() {
        return database.userDao().getAllUsers();
    }

    public UserInfo getUserById(int id) {
        return database.userDao().getUserById(id);
    }

    public void createUser(final UserInfo user) {
        database.userDao().createUser(user);
    }

    public void updateUser(UserInfo user) {
        database.userDao().updateUser(user);
    }

    public void deleteUser(int id) {
        UserInfo user = database.userDao().getUserById(id);
        database.userDao().deleteUser(user);
    }

    //--------------------------------------------------------------------------

    public List<UserAcc> getAllAcc() {
        return database.accDao().getAllAcc();
    }

    public UserAcc getAccById(int id) {
        return database.accDao().getAccById(id);
    }

    public void createAcc(final UserAcc user) {
        database.accDao().createAcc(user);
    }

    public void updateAcc(UserAcc user) {
        database.accDao().updateAcc(user);
    }

    public void deleteAcc(int id) {
        UserAcc user = database.accDao().getAccById(id);
        database.accDao().deleteAcc(user);
    }
    public int getMaxAccId(){return database.accDao().getMaxAccId(); }

    //-------------------------------------------------------------------------

    public List<Transaction> getAllTrans() {
        return database.transDao().getAllTrans();
    }

    public Transaction getTransByAcc(int accone, int acctwo){ return database.transDao().getTransByAcc(accone,acctwo); }

    public List<Transaction> getTransOfAcc(int accone) { return database.transDao().getTransOfAcc(accone); }

    public Transaction getTransById(int id) {
        return database.transDao().getTransById(id);
    }

    public void createTrans(final Transaction trans) {
        database.transDao().createTrans(trans);
    }

    public void updateTrans(Transaction trans) {
        database.transDao().updateTrans(trans);
    }

    public void deleteTrans(int id) {
        Transaction trans = database.transDao().getTransById(id);
        database.transDao().deleteTrans(trans);
    }
    public int getMaxTransId(){ return database.transDao().getMaxTransId(); }

}

