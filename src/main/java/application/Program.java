package application;

import model.entities.Department;
import model.entities.Seller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Program {
    public static void main(String[] args) {
        SimpleDateFormat dmy = new SimpleDateFormat("dd/MM/yyy");
        Date birthDate = null;
        Department dep = new Department(1,"Computers");
        try{
            birthDate = dmy.parse("26/03/1994");
            Seller seller = new Seller(1,
                    "Luan Gomes",
                    "luan.gomesb@live.com",
                    birthDate,
                    3000.00,
                    dep);
            System.out.println(seller);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

