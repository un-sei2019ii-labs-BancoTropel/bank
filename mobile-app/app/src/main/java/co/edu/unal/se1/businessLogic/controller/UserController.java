package co.edu.unal.se1.businessLogic.controller;

import android.content.Context;

import co.edu.unal.se1.dataAccess.model.UserInfo;
import co.edu.unal.se1.dataAccess.model.UserAcc;
import co.edu.unal.se1.dataAccess.model.Transaction;
import co.edu.unal.se1.dataAccess.repository.UserRepository;
import java.util.Calendar;
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

    public boolean DeleteUser (UserInfo user, Context context, UserAcc acc){

        userRepository = new UserRepository(context);

        double balance = acc.getBalance();

        if (balance==0){

            userRepository.deleteAcc(acc.getId());
            userRepository.deleteUser(user.getId());
            System.out.println("Usuario y cuenta borrados con exito");

            return true;

        }
        else{

            System.out.println("No se puede eliminar la cuenta porque el balance de la cuenta no es cero");
            return false;
        }
    }

    public void SetOrChangeEmail (int userId, Context context, String email){

        userRepository = new UserRepository(context);

        UserInfo user = userRepository.getUserById(userId);

        user.setEmail(email);

        userRepository.updateUser(user);

        System.out.println("Contraseña registrada con exito");

    }

    public void SetOrChangePhone (int userId, Context context, int Phone){

        userRepository = new UserRepository(context);

        UserInfo user = userRepository.getUserById(userId);

        user.setPhone(Phone);

        userRepository.updateUser(user);

        System.out.println("Telefono registrado con exito");

    }

    public void SetOrChangeAddress (int userId, Context context, String address){

        userRepository = new UserRepository(context);

        UserInfo user = userRepository.getUserById(userId);

        user.setAddress(address);

        userRepository.updateUser(user);

        System.out.println("Dirección registrada con exito");

    }

}