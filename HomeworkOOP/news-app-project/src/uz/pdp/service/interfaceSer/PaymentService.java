package uz.pdp.service.interfaceSer;

import uz.pdp.model.payment.PaymentMethod;

import java.util.List;

public interface PaymentService {

    void paymentMethodList(List<PaymentMethod> paymentMethods);

    void addPaymentMethod(List<PaymentMethod> paymentMethods);

    void editPaymentMethod(List<PaymentMethod> paymentMethods);

    void showPaymentMethod(List<PaymentMethod> paymentMethods);

    void deletePaymentMethod(List<PaymentMethod> paymentMethods);


}
