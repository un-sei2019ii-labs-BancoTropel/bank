package co.edu.unal.se1.tests;

import org.junit.Test;

import co.edu.unal.se1.businessLogic.controller.AccController;
import co.edu.unal.se1.dataAccess.model.UserAcc;

import static org.junit.Assert.*;

public class AccControllerTest {

    @Test
    public void sendMoneyCorrect() {

        System.out.println("--------------------------------------------------------");

        //se declara la cuenta de origen
        UserAcc origin = new UserAcc();

        origin.setId(1);
        origin.setBalance(150000);
        origin.setUserId(132730527);
        origin.setAdmin(false);
        origin.setPassword(123456);
        System.out.println("balance inicial cuenta de origen: "+origin.getBalance());

        //se declara la cuenta de destino
        UserAcc target = new UserAcc();

        target.setId(2);
        target.setAdmin(false);
        target.setUserId(300);
        target.setBalance(50000);
        target.setPassword(234567);
        System.out.println("balance inicial cuenta de destino: "+target.getBalance());

        //se declara el valor de la tranferencia el cual es menor al balance actual
        //de la cuenta de origen
        double ValueOfTrans = 50000;

        //se verifica si la funcion realiza la transaccion correctamente
        assertEquals(true, AccController.sendMoneyMod(origin,target,ValueOfTrans));

        System.out.println("--------------------------------------------------------");

    }

    @Test
    public void sendMoneyIncorrect() {

        System.out.println("--------------------------------------------------------");

        //se declara la cuenta de origen
        UserAcc origin = new UserAcc();

        origin.setId(1);
        origin.setBalance(5000);
        origin.setUserId(132730527);
        origin.setAdmin(false);
        origin.setPassword(123456);
        System.out.println("balance inicial cuenta de origen: "+origin.getBalance());

        //se declara la cuenta de destino
        UserAcc target = new UserAcc();

        target.setId(2);
        target.setAdmin(false);
        target.setUserId(300);
        target.setBalance(50000);
        target.setPassword(234567);
        System.out.println("balance inicial cuenta de destino: "+target.getBalance());

        //se declara el valor de la tranferencia el cual es MAYOR al balance actual
        //de la cuenta de origen
        double ValueOfTrans = 50000;

        //se verifica si la funcion realiza la transaccion correctamente,
        //en este caso como el valor de la transeferencia es mayor al balance de la cuenta origen
        //se espera que no se realice la transaccion
        assertEquals(false, AccController.sendMoneyMod(origin,target,ValueOfTrans));

        System.out.println("--------------------------------------------------------");

    }

    @Test
    public void sendMoneyError() {

        System.out.println("--------------------------------------------------------");

        //se declara la cuenta de origen
        UserAcc origin = new UserAcc();

        origin.setId(1);
        origin.setBalance(5000);
        origin.setUserId(132730527);
        origin.setAdmin(false);
        origin.setPassword(123456);
        System.out.println("balance inicial cuenta de origen: "+origin.getBalance());

        //se declara la cuenta de destino
        UserAcc target = new UserAcc();

        target.setId(2);
        target.setAdmin(false);
        target.setUserId(300);
        target.setBalance(50000);
        target.setPassword(234567);
        System.out.println("balance inicial cuenta de destino: "+target.getBalance());

        //se declara el valor de la tranferencia el cual es MAYOR al balance actual
        //de la cuenta de origen
        double ValueOfTrans = -50000;

        //se verifica si la funcion realiza la transaccion correctamente,
        //en este caso como el valor de la transeferencia es mayor al balance de la cuenta origen
        //se espera que no se realice la transaccion
        assertEquals(false, AccController.sendMoneyMod(origin,target,ValueOfTrans));

        System.out.println("--------------------------------------------------------");

    }


    @Test
    public void modifyUserBalanceApproved() {

        System.out.println("--------------------------------------------------------");

        UserAcc admin = new UserAcc();

        //se establece una cuenta como administrador
        admin.setAdmin(true);
        admin.setId(1);

        UserAcc acc = new UserAcc();

        //se establece una cuenta de un usuario cualquiera
        acc.setId(2);
        acc.setPassword(123456);
        acc.setAdmin(false);
        acc.setBalance(0);
        acc.setUserId(132730527);
        System.out.println("Balance inicial de la cuenta " + acc.getBalance());

        //se especifica la cantidad por la cual se remplazara el balance, en este caso se quiere aumentar 5000 pesos
        double cambio = (acc.getBalance()+5000);

        //se comprueba si la cuenta admin puede ser utilizada para cambiar el balance de la cuenta usuario
        // en este caso como la cuenta tiene marcado su tipo como true la transaccion deberia de ser realizada
        assertEquals(true, AccController.ModifyUserBalanceMod(acc,admin,cambio));

        System.out.println("--------------------------------------------------------");

    }


    @Test
    public void modifyUserBalanceDenny() {

        System.out.println("--------------------------------------------------------");

        UserAcc admin = new UserAcc();

        //se establece una cuenta como administrador, en este caso la cuenta
        //que se busca como admin no tiene tipo true, lo cual la marca como usuario total
        admin.setAdmin(false);
        admin.setId(1);

        UserAcc acc = new UserAcc();

        //se establece una cuenta de un usuario cualquiera
        acc.setId(2);
        acc.setPassword(123456);
        acc.setAdmin(false);
        acc.setBalance(0);
        acc.setUserId(132730527);
        System.out.println("Balance inicial de la cuenta " + acc.getBalance());

        //se especifica la cantidad por la cual se remplazara el balance, en este caso se quiere aumentar 5000 pesos
        double cambio = (acc.getBalance()+5000);

        //se comprueba si la cuenta admin puede ser utilizada para cambiar el balance de la cuenta usuario
        // en este caso como la cuenta tiene marcado su tipo como false la transaccion No deberia de ser realizada
        assertEquals(false, AccController.ModifyUserBalanceMod(acc,admin,cambio));

        System.out.println("--------------------------------------------------------");

    }


    @Test
    public void modifyUserBalanceError() {

        System.out.println("--------------------------------------------------------");

        UserAcc admin = new UserAcc();

        //se establece una cuenta como administrador
        admin.setAdmin(true);
        admin.setId(1);

        UserAcc acc = new UserAcc();

        //se establece una cuenta de un usuario cualquiera
        acc.setId(2);
        acc.setPassword(123456);
        acc.setAdmin(false);
        acc.setBalance(0);
        acc.setUserId(132730527);
        System.out.println("Balance inicial de la cuenta " + acc.getBalance());

        //se especifica la cantidad por la cual se remplazara el balance, en este caso se quiere reducir 5000 pesos
        double cambio = (acc.getBalance()-5000);

        //se comprueba si la cuenta admin puede ser utilizada para cambiar el balance de la cuenta usuario
        // en este caso como la cuenta tiene marcado su tipo como true la transaccion deberia de ser realizada
        assertEquals(false, AccController.ModifyUserBalanceMod(acc,admin,cambio));

        System.out.println("--------------------------------------------------------");

    }
}