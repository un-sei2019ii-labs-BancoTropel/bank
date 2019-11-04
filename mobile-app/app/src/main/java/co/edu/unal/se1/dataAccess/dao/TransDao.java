package co.edu.unal.se1.dataAccess.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

import co.edu.unal.se1.dataAccess.model.UserInfo;
import co.edu.unal.se1.dataAccess.model.UserAcc;
import co.edu.unal.se1.dataAccess.model.Transaction;

@Dao
public interface TransDao {

    @Query("SELECT * FROM `transaction`")
    List<Transaction> getAllTrans();

    @Query("SELECT * FROM `transaction` WHERE tranid = :id")
    Transaction getTransById(int id);

    @Query("SELECT * FROM `Transaction` WHERE tranid = ( SELECT max(tranid) FROM `transaction`)")
    int getMaxTransId();

    @Query("SELECT * FROM `transaction` WHERE accone = :accone AND acctwo = :acctwo")
    Transaction getTransByAcc(int accone, int acctwo);

    @Query("SELECT * FROM `transaction` WHERE accone = :accone")
    List<Transaction> getTransOfAcc(int accone);


    @Insert
    void createTrans(Transaction trans);

    @Update
    void updateTrans(Transaction trans);

    @Delete
    void deleteTrans(Transaction trans);

}

