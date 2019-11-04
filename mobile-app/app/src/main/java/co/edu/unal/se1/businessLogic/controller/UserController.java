package co.edu.unal.se1.businessLogic.controller;

import android.content.Context;

import co.edu.unal.se1.dataAccess.model.UserInfo;
import co.edu.unal.se1.dataAccess.model.UserAcc;
import co.edu.unal.se1.dataAccess.model.Transaction;
import co.edu.unal.se1.dataAccess.repository.UserRepository;
import java.util.List;

public class UserController {

    private UserRepository userRepository;

    public UserController() {

    }

    public void createUser(UserInfo user, Context context, UserAcc acc) {

        userRepository = new UserRepository(context);
        userRepository.createUser(user);
        int max = userRepository.getMaxAccId();
        acc.setId(max);
        userRepository.createAcc(acc);
        System.out.println("¡Usuario creado satisfactoriamente!");
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

            //se registra la transaccion
            userRepository.createTrans(trans);

            //se informa de la transaccion
            System.out.println("transacción realizada satisfactoriamente");

            return true;

        } else {

            return false;
        }

    }

    public void DeleteUser (UserInfo user, Context context, UserAcc acc){


    }

    public void ShowTransaction(Transaction trans){

        String name = userRepository.getUserById(trans.getAccone()).getName();
        String nametwo = userRepository.getUserById(trans.getAcctwo()).getName();
        System.out.println("Source Account - ID: " + trans.getAccone() +
                ", Name Source: "+ name +
                ", Amount Send: " + trans.getAmount()+
                ", Name Target: " + nametwo +
                "Target Account - ID: " + trans.getAcctwo());

    }

    public void ShowHistory (UserAcc acc){


        List<Transaction> AllTrans = userRepository.getTransOfAcc(acc.getId());


    }

}