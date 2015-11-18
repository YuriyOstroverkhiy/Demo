package com.unomy.tests;

import com.qatestlab.base.BaseTest;
import com.unomy.actions.Actions;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Issue;

public class RegisterTest extends BaseTest {

    @Issue("1123038")
    @Test()
    public void registerNewUser(){
        Actions.loginActions().createNewFreeAccount();
    }
}
