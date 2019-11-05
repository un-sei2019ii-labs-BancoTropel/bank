package co.edu.unal.se1.dataAccess.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(foreignKeys = {@ForeignKey(entity = UserAcc.class,
        parentColumns = "accid",
        childColumns = "accone"),
        @ForeignKey(entity = UserAcc.class,
                parentColumns = "accid",
                childColumns = "acctwo")})

public class Transaction implements Serializable {

    @PrimaryKey
    public int tranid;

    @ColumnInfo(name = "accone") public int accone;

    @ColumnInfo(name = "acctwo") public int acctwo;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "amount")
    public double amount;

    public void setId(int tranid) {
        this.tranid = tranid;
    }

    public int getTranid() {
        return tranid;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public int getAccone() { return accone; }

    public int getAcctwo() { return acctwo; }

    public String setDate(String date) { return this.date=date; }

    public double setAmount(double amount) { return this.amount=amount; }

    public void setAccone(int accone) {
        this.accone = accone;
    }

    public void setAcctwo(int acctwo) {
        this.acctwo = acctwo;
    }

}


