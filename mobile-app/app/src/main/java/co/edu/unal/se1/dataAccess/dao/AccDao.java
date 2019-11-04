package co.edu.unal.se1.dataAccess.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

import co.edu.unal.se1.dataAccess.model.UserInfo;
import co.edu.unal.se1.dataAccess.model.UserAcc;

@Dao
public interface AccDao {

    @Query("SELECT * FROM useracc")
    List<UserAcc> getAllAcc();

    @Query("SELECT * FROM useracc WHERE accid = :id")
    UserAcc getAccById(int id);

    @Query("SELECT * FROM useracc WHERE accid = ( SELECT max(accid) FROM useracc)")
    int getMaxAccId();

    @Query("SELECT * FROM useracc WHERE user_id = :id")
    UserAcc getAccByUser(int id);

    @Insert
    void createAcc(UserAcc user);

    @Update
    void updateAcc(UserAcc user);

    @Delete
    void deleteAcc(UserAcc user);

}

