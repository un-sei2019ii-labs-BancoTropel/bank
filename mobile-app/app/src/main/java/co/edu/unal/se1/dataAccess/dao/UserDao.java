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
public interface UserDao {

    @Query("SELECT * FROM UserInfo")
    List<UserInfo> getAllUsers();

    @Query("SELECT * FROM UserInfo WHERE id = :id")
    UserInfo getUserById(int id);

    @Insert
    void createUser(UserInfo user);

    @Update
    void updateUser(UserInfo user);

    @Delete
    void deleteUser(UserInfo user);

}

