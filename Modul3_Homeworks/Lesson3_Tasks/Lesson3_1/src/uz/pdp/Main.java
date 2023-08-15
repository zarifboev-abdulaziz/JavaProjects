package uz.pdp;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {

    public static void main(String[] args) {

        for (String availableZoneId : ZoneId.getAvailableZoneIds()) {
            ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of(availableZoneId));
            System.out.println(zonedDateTime);
        }

    }
}
