package in.hideandseek.vaxvision.common.lib;


import in.hideandseek.vaxvision.BuildConfig;

public class ApiConstants {


    public static final String URL_API = BuildConfig.API_URL;
    public static final String MOBILE_USER_AGENT = BuildConfig.MOBILE_USER_AGENT;


    public static final String HEADER_KEY_ACCEPT_LANG = "Accept-Language";
    public static final String HEADER_LANG = "en_US";

    // API Endpoints
    public static final String GET_STATES = "v2/admin/location/states";
    public static final String GET_DISTRICT = "v2/admin/location/districts/{state_id}";
    public static final String FIND_BY_DISTRICT = "v2/appointment/sessions/public/findByDistrict";
    public static final String GENERATE_OTP = "v2/auth/public/generateOTP";
    public static final String CONFIRM_OTP = "v2/auth/public/confirmOTP";


}
