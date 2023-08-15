package uz.pdp.service.adminService;

import uz.pdp.model.User;

public interface AdminService {
    void adminMenu(User user);
    void clientsMenu();
    void supplierMenu();
    void chiefsMenu();
    void foodsMenu();
    void payTypeMenu();
    void orderHistory();
    void showBalance();
}
