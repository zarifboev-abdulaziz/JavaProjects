package uz.pdp.service.clientService;

import uz.pdp.model.Cloth;
import uz.pdp.model.OrderItem;
import uz.pdp.model.StoreItem;
import uz.pdp.model.abs.User;
import uz.pdp.service.clientService.interfaces.ClientSeeClothesMenu;

import static uz.pdp.utils.Db.clothList;
import static uz.pdp.utils.Db.store;
import static uz.pdp.utils.Util.*;


public class ClientSeeClothesMenuImpl implements ClientSeeClothesMenu {
    @Override
    public void seeClothesMenu(User customer) {
        for (int i = 0; i < clothList.size(); i++) {
            Cloth cloth = clothList.get(i);
            String discount = cloth.getDiscount() > 0 ?
                    BLACK_CROSSED_OUT + cloth.getPrice() + RESET +
                            " " + printInRed(cloth.getPrice() - cloth.getPrice() * cloth.getDiscount() / 100 +
                            " so'm")

                    : String.valueOf(cloth.getPrice());
            print(CYAN, (i + 1) + ". "
                    + cloth.getProductCode() + ", "
                    + cloth.getName() + ", "
                    + cloth.getColor().getName() + ", "
                    + cloth.getSize() + ", "
                    + discount
            );
        }
        print(GREEN_BOLD, "1=> Enter product code 2=> Prev 3=> Next 0=> Back");
        System.out.print("=> ");
        int option = scnInt();
        switch (option) {
            case 1:
                Cloth selectedCloth = selectClothByProductCode();
                if (selectedCloth == null) return;

                print(GREEN_BOLD, "Enter quantity: ");
                System.out.print("=> ");
                int quantity = scnInt();

                boolean isAdded = addToCart(customer, selectedCloth, quantity);
                break;
            case 2:
                break;
            case 0:
                return;
            default:
                print(RED, "Wrong option!!");
                break;
        }
    }

    private Cloth selectClothByProductCode() {
        System.out.print(GREEN + "Code: " + RESET);
        int inputProductCode = scnInt();
        if (inputProductCode == 0) {
            return null;
        }
        for (Cloth cloth : clothList) {
            if (cloth.getProductCode() == inputProductCode) {
                return cloth;
            }
        }
        System.out.println(printInRed("Wrong product code!!! Enter product code again (0=> Back):" +
                " "));
        return selectClothByProductCode();
    }

    private boolean addToCart(User customer, Cloth selectedCloth, int quantity) {
        for (StoreItem storeItem : store) {
            if (storeItem.getCloth().getId().equals(selectedCloth.getId())) {
                if (storeItem.getQuantity() < quantity) {
                    if (storeItem.getQuantity() == 0) {
                        System.out.println(printInRed(selectedCloth.getName() + " is out of " +
                                "stock!!!"));
                    } else {
                        System.out.println(printInRed("We have left only " + storeItem.getQuantity() + "!"));
                    }
                    return false;
                } else {

                    // TODO: 09.12.2021 ADD CLOTH AND QUANTITY (OrderItem) to customer's cart!! - DONE

                    boolean isFound = false;
                    for (OrderItem orderItem : customer.getMyCart()) {
                        if (orderItem.getCloth().getId().equals(selectedCloth.getId())){
                            isFound = true;
                            orderItem.setQuantity(orderItem.getQuantity() + quantity);
                        }
                    }

                    if (isFound){
                        print(BLUE_BOLD, selectedCloth.getName() + " has been added to the cart " +
                                "successfully!!");
                    }else {
                      customer.getMyCart().add(new OrderItem(selectedCloth, quantity));
                        print(BLUE_BOLD, selectedCloth.getName() + " has been added to the cart " +
                                "successfully!!");
                    }
                    return true;
                }
            }
        }

        return false;
    }

}
