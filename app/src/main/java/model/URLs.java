package model;

public class URLs {

    private static final String ROOT_URL = "http://192.168.1.4:8080/Mobile/rest/";
    public static final String SIGNUP = ROOT_URL + "AddNewUser";
    public static final String VALIDATE_MOBILE= ROOT_URL + "CheckPhoneNo";
    public static final String USER_INVOICE_LIST = ROOT_URL + "fetchCustomerInvoices?cid=billt101";
    public static final String MERCHANT_LOGO = ROOT_URL + "GetMerchantLogo?mid=mid1001";
}
