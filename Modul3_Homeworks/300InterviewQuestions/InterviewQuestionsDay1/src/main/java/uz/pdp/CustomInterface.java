package uz.pdp;

import java.lang.reflect.Method;

interface CustomInterface {

     String key = "qwerty";

    void method1();

    default String method2(){
        return "hello world";
    }

    static String method3(){
        return "method 3 is called";

    }







 }
