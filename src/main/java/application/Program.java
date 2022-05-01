package application;

import model.dao.DaoFactory;
import model.entities.Department;
import model.entities.Seller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Program {
    public static void main(String[] args) {
        //get seller by id test
        Integer id = 6;
        Seller seller =  DaoFactory.createSellerDao().findById(id);
        System.out.println(seller);
   }
}

