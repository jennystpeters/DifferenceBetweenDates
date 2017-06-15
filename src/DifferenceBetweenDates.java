import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.lang.String;

public class DifferenceBetweenDates {

    public static void main(String[] args) {

        //Initialize booleans to check validity of dates input by user (in dateValidityCheck method)
        boolean startValid = true;
        boolean endValid = true;

        //Initialize formatter for the pattern MM/dd/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        //Initialize scanner to accept user input
        Scanner startScan = new Scanner(System.in);
        //Ask user to enter a start date (must be MM/dd/yyy format)
        System.out.print("Enter a start date in the format MM/dd/yyyy: ");
        String startString = startScan.nextLine();

        //Initialize scanner to accept user input
        Scanner endScan = new Scanner(System.in);
        //Ask user to enter an end date (must be MM/dd/yyy format)
        System.out.print("Enter an end date in the format MM/dd/yyyy: ");
        String endString = endScan.nextLine();

        //Send input strings to dateValidityCheck method to determine if the program should continue to calculate delta
        startValid = dateValidityCheck(startString);
        endValid = dateValidityCheck(endString);

        //Determine date difference if inputs are valid dates and in accordance with specified pattern MM/dd/yyyy
        if (startValid && endValid) {

            //Use LocalDate class to obtain instance of immutable date-time object in ISO-8601 calendar system from input string
            LocalDate startDate = LocalDate.parse(startString, formatter);
            LocalDate endDate = LocalDate.parse(endString, formatter);

            //Use Period class to determine difference between date-based values (years, months, days)
            Period differenceBetween = Period.between(startDate, endDate);
            long days = ChronoUnit.DAYS.between(startDate, endDate);

            //Output number of years, months, and days (and total days) between the input dates
            System.out.println("You are " + differenceBetween.getYears() + " years, "
                    + differenceBetween.getMonths() + " months, and "
                    + differenceBetween.getDays() + " days old. (" + days + " days total)");
        }
        //Inform user if any validity checks indicate an invalid entry
        else {
            System.out.println("Sorry! That is an invalid entry.");
        }
    }

    //Validate the user dates are in the specified date format MM/dd/yyyy
    static boolean dateValidityCheck (String dateEntry) {

        //Initialize boolean variables for comprehensive input validity
        boolean digitValidity = false;
        boolean lengthValidity = false;
        boolean contentValidity = false;
        boolean result = false;

        //Iterate through each character accepting only digits and "/"
        for (int i = 0; i < dateEntry.length(); i++) {
            if ((Character.isDigit(dateEntry.charAt(i))) || ((Character.toString(dateEntry.charAt(i)).equals("/")))) {
                digitValidity = true;
            }
            else {
                digitValidity = false;
                return false;
            }

        }

        //Separate dates into month, day, and year based on location of "/"
        String[] dateParts = dateEntry.split("/");

        String month = dateParts[0];
        String day = dateParts[1];
        String year = dateParts[2];

        //Verify valid length of month (2), day (2), and year (4)
        if (month.length() == 2) {
            if (day.length() == 2) {
                if (year.length() == 4) {
                    lengthValidity = true;
                }
            }
        }
        else {
            lengthValidity = false;
        }

        //Create integers equal to value of month, day, and year strings
        int monthNum = Integer.parseInt(month);
        int dayNum = Integer.parseInt(day);
        int yearNum = Integer.parseInt(year);

        //Verify number of days per month
        if (yearNum > 0) {
            if ((monthNum == 1) || (monthNum == 3) || (monthNum == 5) || (monthNum == 7) || (monthNum == 8) || (monthNum == 10) || (monthNum == 12)) {
                if ((dayNum > 0) && (dayNum < 32)) {
                    contentValidity = true;
                }
            } else if ((monthNum == 4) || (monthNum == 6) || (monthNum == 9) || (monthNum == 11)) {
                if ((dayNum > 0) && (dayNum < 31)) {
                    contentValidity = true;
                }
            } else if (monthNum == 2) {
                if ((dayNum > 0) && (dayNum < 30)) {
                    contentValidity = true;
                }
            }
        }
        else {
            contentValidity = false;
        }

        //If all validity checks show true - result is set to true (main method will proceed with calculating delta)
        if ((digitValidity) && (lengthValidity) && (contentValidity)) {
            result = true;
        }

        //Return result of validity checks to main method
        return result;

    }
}