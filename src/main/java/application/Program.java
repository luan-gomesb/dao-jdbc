package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;
import java.util.function.Consumer;

public class Program {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.print("--------- ");
        System.out.print("All Sellers ");
        System.out.print("---------\n");

        List<Seller> allSellers = sellerDao.findAll();
        allSellers.stream().forEach(System.out::println);
    }
    public static void testFindByDep(){
        Integer id = 2;
        Department dep = new Department();
        dep.setId(id);

        SellerDao sellerDao = DaoFactory.createSellerDao();
        List<Seller> depSellers = sellerDao.findByDepartment(dep);

        Consumer<Seller> showSeller = seller -> System.out.printf("%d - %s - %.2f\n",
                seller.getId(),seller.getName(),seller.getBaseSalary());

        System.out.print("--------- ");
        System.out.printf("%s Department Sellers ", dep.getName());
        System.out.print("---------\n");

        depSellers.stream().forEach(System.out::println);
        // depSellers.stream().forEach(showSeller);

    }
}
