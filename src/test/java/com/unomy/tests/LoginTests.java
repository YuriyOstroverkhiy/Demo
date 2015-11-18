package com.unomy.tests;

import com.qatestlab.Random;
import com.qatestlab.base.BaseTest;
import com.unomy.actions.Actions;
import com.unomy.utils.DataProviderPool;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Issue;

public class LoginTests extends BaseTest {

    String firstName = "test" + Random.genRandNumber();
    String lastName = "test" + Random.genRandNumber();
    String email = Random.genEmail();
    String phone = Random.genPhone();
    String website = Random.genWebsite();
    String password = "test" + Random.genRandNumber();




    @Issue("1123039")
    @Test(dataProvider = DataProviderPool.USER_CREDENTIALS, dataProviderClass = DataProviderPool.class)
    public void loginTest(String login, String password){
        Actions.loginActions().login(login, password);
    }
}
