package uz.pdp;


import uz.pdp.model.Cloth;
import uz.pdp.model.OrderHistory;
import uz.pdp.service.MainServiceImpl;
import uz.pdp.utils.Db;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//https://www.prada.com/content/dam/pradanux_products/2/2HC/2HC274/2DMIF0002/2HC274_2DMI_F0002_SLF.png

import static uz.pdp.utils.DataFromJson.*;

public class Main {

    public static void main(String[] args) {

        getAllDataFromJson();

        MainServiceImpl mainService = new MainServiceImpl();

        mainService.showMenu();

    }
}
