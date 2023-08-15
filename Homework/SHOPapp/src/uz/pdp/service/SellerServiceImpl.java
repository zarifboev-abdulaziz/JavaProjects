package uz.pdp.service;

import uz.pdp.model.User;
import uz.pdp.service.interfaces.SellerService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SellerServiceImpl implements SellerService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);
    ClothServiceImpl clothService = new ClothServiceImpl();

    @Override
    public void sellerMenu(User user) {
        while (true){
            System.out.println("1.Cloth Menu\n2.Show balance\n3.Log out");

            int option = 0;
            try {
                option = scannerInt.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Wrong input!!!shu chiqyabdi");
            }


                switch (option){
                    case 1:
                        clothService.clothMenu(user);
                        break;
                    case 2:
                        showBalance(user);
                        break;

                    case 3: return;
                    default:
                        System.out.println("Wrong option");
                        break;
                }

        }
    }

    @Override
    public void showBalance(User seller) {

        System.out.println(seller.getBalance());

    }
}
