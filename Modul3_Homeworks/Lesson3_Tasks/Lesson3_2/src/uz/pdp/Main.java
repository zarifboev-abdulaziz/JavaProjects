package uz.pdp;

import java.sql.SQLSyntaxErrorException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalField;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);




        System.out.println("Soatni kiriting:  ");
        int hour = scanner.nextInt();

        System.out.println("Minutni kiriting: ");
        int minute = scanner.nextInt();

        System.out.println("Sukundni kiriting:");
        int second = scanner.nextInt();


        LocalTime localTime = LocalTime.now();
        LocalTime inputTime =  LocalTime.of(hour, minute, second);


        int resultHour = (Math.abs(localTime.getHour() - inputTime.getHour()));
        int resultMinute = (Math.abs(localTime.getMinute() - inputTime.getMinute()));
        int resultSecond = (Math.abs(localTime.getSecond() - inputTime.getSecond()));

        System.out.println(resultHour + "." + resultMinute + "." + resultSecond);



    }
}
