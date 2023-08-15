package uz.pdp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jndi.toolkit.url.UrlUtil;
import uz.pdp.model.Currency;
import uz.pdp.model.Post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        List<Currency> currencyList = null;

        try {
            URL url = new URL("https://cbu.uz/uz/arkhiv-kursov-valyut/json/");
            URLConnection connection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            currencyList = gson.fromJson(bufferedReader, new TypeToken<List<Currency>>(){}.getType());

//            for (Currency currency : currencyList) {
//                System.out.println(currency);
//            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Currency currencyUSD = null;
        Currency currencyEUR = null;
        Currency currencyRUB = null;
        double amount = 0;
        double result = 0;

        for (Currency currency : currencyList) {
            if (currency.getCcy().equals("USD")){
                currencyUSD = currency;
            }else if (currency.getCcy().equals("EUR")){
                currencyEUR = currency;
            }else if (currency.getCcy().equals("RUB")){
                currencyRUB = currency;
            }
        }

        System.out.println("Welcome to Converter Application");
        System.out.println("Please select currency: ");
        System.out.println("1.USD => UZS \n2.EUR => UZS \n3.RUB => UZS \n4.UZS => USD \n5.UZS => EUR \n6.UZS => RUB \n7.Other \n8.Back");
        int option = new Scanner(System.in).nextInt();
        switch (option){
            case 1:
                System.out.println("Enter amount: ");
                 amount =  new Scanner(System.in).nextDouble();
                 result = currencyUSD.getRate() * amount;
                System.out.println("Amount in Sum: " + result);
                break;
            case 2:
                System.out.println("Enter amount: ");
                 amount =  new Scanner(System.in).nextDouble();
                 result = currencyEUR.getRate() * amount;
                System.out.println("Amount in Sum: " + result);
                break;
            case 3:
                System.out.println("Enter amount: ");
                amount =  new Scanner(System.in).nextDouble();
                result = currencyRUB.getRate() * amount;
                System.out.println("Amount in Sum: " + result);
                break;

            case 4:
                System.out.println("Enter amount: ");
                amount =  new Scanner(System.in).nextDouble();
                result =  amount / currencyUSD.getRate();
                System.out.println("Amount in USD: " + result);
                break;
            case 5:
                System.out.println("Enter amount: ");
                amount =  new Scanner(System.in).nextDouble();
                result = amount / currencyEUR.getRate();
                System.out.println("Amount in EUR: " + result);
                break;
            case 6:
                System.out.println("Enter amount: ");
                amount =  new Scanner(System.in).nextDouble();
                result = amount / currencyRUB.getRate();
                System.out.println("Amount in Sum: " + result);
                break;
            case 7:
                System.out.println("Enter Currency Abbreviation to select: ");
                String inputCcy = new Scanner(System.in).nextLine();
                boolean isFound = false;
                for (Currency currency : currencyList) {
                    if (currency.getCcy().equals(inputCcy)){
                        isFound = true;
                        System.out.println("Enter amount: ");
                        amount =  new Scanner(System.in).nextDouble();
                        result = currency.getRate() * amount;
                        System.out.println("Amount in Sum: " + result);
                        break;
                    }
                }
                if (!isFound){
                    System.out.println("Currency not Found!!!");
                }
                break;

            case 8: return;
            default:
                System.out.println("Wrong option!!!");
                break;
        }

        main(args);
    }
}
