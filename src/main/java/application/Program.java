package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;
import java.util.function.Consumer;

public class Program {
    public static void main(String[] args) {
        // get seller by id test
        Integer id = 1;
        Department dep = new Department();
        dep.setId(id);
        dep.setName("Computers");

        SellerDao sellerDao = DaoFactory.createSellerDao();
        List<Seller> depSellers = sellerDao.findByDepartment(dep);

        Consumer<Seller> showSeller = seller -> System.out.printf("%d - %s - %.2f\n",
                seller.getId(),seller.getName(),seller.getBaseSalary());

        System.out.print("--------- ");
        System.out.printf("%s Department Sellers ", dep.getName());
        System.out.print("---------\n");

        depSellers.stream().forEach(showSeller);
    }

}
