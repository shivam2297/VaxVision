package in.hideandseek.vaxvision.screens.calendar.viewmodel;

public class Filter {
    public static final int AGE_FILTER_18 = 18;
    public static final int AGE_FILTER_45 = 45;

    public static final String VACCINE_FILTER_COVISHIELD = "COVISHIELD";
    public static final String VACCINE_FILTER_COVAXIN = "COVAXIN";
    public static final String VACCINE_FILTER_SPUTNIK = "SPUTNIK V";
    public static final String FEE_FILTER_FREE = "Free";
    public static final String FEE_FILTER_PAID = "Paid";

    /*values: AGE_FILTER_18, AGE_FILTER_45, 0*/
    public int age;

    /*values: VACCINE_FILTER_COVISHIELD
    VACCINE_FILTER_COVAXIN
    VACCINE_FILTER_SPUTNIK
    EMPTY*/
    public String vaccine;

    /*values: FEE_FILTER_FREE, FEE_FILTER_PAID, EMPTY */
    public String feeType;
}
