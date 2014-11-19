package by.bsuir.forlabs.utilits;

import java.util.Date;

public final class Functions {

    private Functions () {

    }

    public static String concatName(String firstname, String lastname) {
        return firstname + " " + lastname;
    }

    public static boolean isBeforeToday(Date rentalDate) {
        boolean isBeforeToday = false;

        if (rentalDate != null) {
            if (rentalDate.getTime() < new Date().getTime()) {
                isBeforeToday = true;
            }
        }
        return isBeforeToday;
    }
}
