package pl.grogowski.util;

public class UtilityClass {

    public static String getTextBasedOnNumberOfBags(Integer numberOfBags) {
        if (numberOfBags == 1) {
            return numberOfBags + " worek:";
        } else if (numberOfBags<5) {
            return numberOfBags + " worki:";
        } else {
            return numberOfBags + " worków:";
        }
    }

    public static String mergeAddress(String street, String city, String zip, String phone) {
        return street.trim()+", "+zip.trim()+" "+city.trim() + " tel. "+phone.trim();
    }

    public static String merdeDateTime(String time, String date) {
        return "Godzina "+ time.trim() + " dnia " + date.trim();
    }
}
