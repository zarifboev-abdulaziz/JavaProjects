package uz.pdp.service.supplierService;

import uz.pdp.model.User;

public interface SupplierService {

    void supplierMenu(User supplier);

    void showReadyFoods(User supplier);

    void showOnRoadProducts(User supplier);

    void showReceivedProducts(User supplier);

    // log out

}
