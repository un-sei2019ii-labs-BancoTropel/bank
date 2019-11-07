package co.edu.unal.se1.businessLogic.controller;

import android.content.Context;

import co.edu.unal.se1.dataAccess.model.UserInfo;
import co.edu.unal.se1.dataAccess.model.UserAcc;
import co.edu.unal.se1.dataAccess.model.Transaction;
import co.edu.unal.se1.dataAccess.repository.UserRepository;
import java.util.Calendar;
import java.util.List;

public class AccController {

    private UserRepository userRepository;

    public AccController() {

    }

    public boolean sendMoney(int sourceId, int targetId, double value, Context context) {

        userRepository = new UserRepository(context);

        Transaction trans = new Transaction();
        int MaxId = userRepository.getMaxTransId();
        //se establece la id como la mayor actual +1
        trans.setId(MaxId+1);

        final UserAcc sourceAcc = userRepository.getAccById(sourceId);
        final UserInfo sourceInfo = userRepository.getUserById(sourceAcc.userId);
        //se establece la cuenta de salida y el valor enviado
        trans.setAccone(sourceAcc.getId());
        trans.setAmount(value);

        if (sourceAcc.getBalance() >= value) {

            final UserAcc targetAcc = userRepository.getAccById(targetId);
            final UserInfo targetInfo = userRepository.getUserById(targetAcc.userId);
            //se establece la cuenta de destino
            trans.setAcctwo(targetAcc.getId());

            sourceAcc.setBalance(sourceAcc.getBalance() - value);
            targetAcc.setBalance(targetAcc.getBalance() + value);
            userRepository.updateAcc(sourceAcc);
            userRepository.updateAcc(targetAcc);

            final UserAcc updatedSourceAcc = userRepository.getAccById(sourceId);
            final UserInfo updatedSourceUser = userRepository.getUserById(updatedSourceAcc.userId);

            final UserAcc updatedTargetAcc = userRepository.getAccById(targetId);
            final UserInfo updatedTargetUser = userRepository.getUserById(updatedTargetAcc.userId);

            trans.setDate(Integer.toString(Calendar.DATE));

            //se registra la transaccion
            userRepository.createTrans(trans);

            //se informa de la transaccion
            System.out.println("transacción realizada satisfactoriamente");

            return true;

        } else {

            return false;
        }

    }

    public static boolean sendMoneyMod(UserAcc sourceAcc, UserAcc targetAcc, double value) {

        //userRepository = new UserRepository(context);

        Transaction trans = new Transaction();
        //int MaxId = userRepository.getMaxTransId();
        //se establece la id como la mayor actual +1
        trans.setId(1);

        //final UserAcc sourceAcc = userRepository.getAccById(sourceId);
        //final UserInfo sourceInfo = userRepository.getUserById(sourceAcc.userId);
        //se establece la cuenta de salida y el valor enviado
        trans.setAccone(sourceAcc.getId());
        trans.setAmount(value);

        if (sourceAcc.getBalance() >= value) {

            //final UserAcc targetAcc = userRepository.getAccById(targetId);
            //final UserInfo targetInfo = userRepository.getUserById(targetAcc.userId);
            //se establece la cuenta de destino
            trans.setAcctwo(targetAcc.getId());

            sourceAcc.setBalance(sourceAcc.getBalance() - value);
            targetAcc.setBalance(targetAcc.getBalance() + value);
            //userRepository.updateAcc(sourceAcc);
            //userRepository.updateAcc(targetAcc);

            //final UserAcc updatedSourceAcc = userRepository.getAccById(sourceId);
            //final UserInfo updatedSourceUser = userRepository.getUserById(updatedSourceAcc.userId);

            //final UserAcc updatedTargetAcc = userRepository.getAccById(targetId);
            //final UserInfo updatedTargetUser = userRepository.getUserById(updatedTargetAcc.userId);

            trans.setDate(Integer.toString(Calendar.DATE));

            //se registra la transaccion
            //userRepository.createTrans(trans);

            //se informa de la transaccion
            System.out.println("transacción realizada satisfactoriamente");

            System.out.println("Balance final cuenta origen: "+sourceAcc.getBalance());
            System.out.println("Balance final cuenta destino: "+targetAcc.getBalance());

            System.out.println("Registro de la transferencia: ");
            System.out.println("Cuenta origen registrada: " + trans.getAccone());
            System.out.println("Cuenta destino registrada: " + trans.getAcctwo());
            System.out.println("fecha registrada: " + trans.getDate());
            System.out.println("valor transaferido: " + trans.getAmount());

            return true;

        } else {

            System.out.println("transacción NO realizada");

            System.out.println("Balance final cuenta origen: "+sourceAcc.getBalance());
            System.out.println("Balance final cuenta destino: "+targetAcc.getBalance());

            System.out.println("Transferencia NO registrada");

            return false;
        }

    }

    public double SeeUserBalance (int userId, Context context){

        userRepository = new UserRepository(context);

        UserAcc acc = userRepository.getAccByUser(userId);

        System.out.println("El balance actual de la cuenta es: " + acc.getBalance());

        return acc.getBalance();

    }

    public boolean ModifyUserBalance (int userid, Context context, UserAcc admin, double mod){

        userRepository = new UserRepository(context);

        if (admin.type==true){

            UserAcc acc = userRepository.getAccByUser(userid);
            acc.setBalance(mod);
            userRepository.updateAcc(acc);

            System.out.println("El balance de la cuenta fue cambiado con exito");

            return true;

        }else{

            return false;
        }

    }

    public static boolean ModifyUserBalanceMod (UserAcc acc, UserAcc admin, double mod){

        //userRepository = new UserRepository(context);

        if (admin.type==true){

            //UserAcc acc = userRepository.getAccByUser(userid);
            acc.setBalance(mod);
            //userRepository.updateAcc(acc);

            System.out.println("El balance de la cuenta fue cambiado con exito");

            System.out.println("Balance final de la cuenta: "+acc.getBalance());

            return true;

        }else{

            System.out.println("No es Admin, por lo tanto no puede modificar el balance");

            return false;
        }

    }

    public boolean UserLogin (int userid, int password, Context context){

        userRepository = new UserRepository(context);

        UserAcc acc = userRepository.getAccByUser(userid);

        if (acc.getPassword()==password){

            return true;

        }else{

            return false;

        }

    }

    public void ShowTransaction(Transaction trans, Context context){

        userRepository = new UserRepository(context);

        String name = userRepository.getUserById(trans.getAccone()).getName();
        String nametwo = userRepository.getUserById(trans.getAcctwo()).getName();
        System.out.println("Source Account - ID: " + trans.getAccone() +
                ", Name Source: "+ name +
                ", Amount Send: " + trans.getAmount()+
                ", Name Target: " + nametwo +
                ", Target Account - ID: " + trans.getAcctwo() +
                ", Date: "+ trans.getDate());

    }

    public void ShowHistory (int accid, Context context){

        userRepository = new UserRepository(context);

        UserAcc acc = userRepository.getAccById(accid);

        List<Transaction> AllTrans = userRepository.getTransOfAcc(acc.getId());

        Transaction trans = new Transaction();

        while (AllTrans.isEmpty()==false){

            trans = AllTrans.get(1);

            ShowTransaction(trans, context);

            AllTrans.remove(1);

        }

    }

}
