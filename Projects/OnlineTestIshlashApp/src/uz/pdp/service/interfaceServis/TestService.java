package uz.pdp.service.interfaceServis;

import uz.pdp.model.Answer;
import uz.pdp.model.Subject;
import uz.pdp.model.Test;

public interface TestService {

    void showTestMenu();

    void showTestList();

    void addTest();

    void updateTest();

    void deleteTest();

    int solveTest(Test test);


}
