package co.edu.unal.se1.dataAccess.model;

import android.provider.ContactsContract;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.io.Serializable;
import java.lang.annotation.Annotation;

@Entity(foreignKeys = @ForeignKey(entity = UserInfo.class,
                parentColumns = "id",
                childColumns = "user_id"))

public class UserAcc implements Serializable {

    @PrimaryKey
    public int accid;

    @ColumnInfo(name = "balance")
    public double balance;

    @ColumnInfo(name = "user_id") public int userId;

    @ColumnInfo(name = "Type") public boolean type;

    public int getId() {
        return accid;
    }

    public void setId(int accid) {
        this.accid = accid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAdmin(boolean type){ this.type = type; }

    public void setUserId(int userId){ this.userId = userId; }

}

