package com.unomy.utils;

import com.qatestlab.DataPoolReader;
import org.testng.annotations.DataProvider;

public  class DataProviderPool {
    public static final String USER_CREDENTIALS = "userCredentials";



    private static DataPoolReader reader = new DataPoolReader();

    /**
     * @return User's login and password.
     */
    @DataProvider(name = USER_CREDENTIALS)
    public static Object[][] getUserLoginData() {
        return reader.GetCredentials(USER_CREDENTIALS);
    }


}
