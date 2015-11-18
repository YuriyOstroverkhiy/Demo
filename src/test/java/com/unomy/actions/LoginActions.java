package com.unomy.actions;

import com.qatestlab.Random;
import com.qatestlab.base.BaseActions;
import com.qatestlab.base.BasePage;
import com.unomy.pages.Pages;
import com.unomy.pages.main.CreateAccountDialog;
import com.unomy.pages.main.LoginDialog;
import com.unomy.pages.main.MainPage;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

public class LoginActions extends BaseActions {
    
    MainPage mainPage;
    CreateAccountDialog createAccountDialog;
    LoginDialog loginDialog;
    
    
    public LoginActions(){
        mainPage = Pages.mainPage();
        createAccountDialog = mainPage.createAccountDialog();
        loginDialog = mainPage.loginDialog();
    }

    @Step
    public void openHomePage() {
        openPage(BasePage.BASE_URL);
    }

    @Step
    private void openPage(String url){
        driver().get(url);

    }

    @Step
    public void createNewFreeAccount() {
        clearSession();
        openHomePage();
        String firstName = "test" + Random.genRandNumber();
        String lastName = "test" + Random.genRandNumber();
        String email = Random.genEmail();
        String phone = Random.genPhone();
        String website = Random.genWebsite();
        String password = "test" + Random.genRandNumber();
        Pages.navigationBar().waitForNavigationBarLoad();
        Pages.navigationBar().clickTryItFreeButton();
        createAccountDialog.waitForTrialDialogLoad();
        createAccountDialog.typeFirstName(firstName);
        Assert.assertTrue(createAccountDialog.getDisplayedFirstName().equals(firstName), "Entered first name doesn't match displayed value");
        createAccountDialog.typeEmail(email);
        Assert.assertTrue(createAccountDialog.getDisplayedEmail().equals(email), "Entered email doesn't match displayed value");

        if (BasePage.isBeta) {
            createAccountDialog.typeLastName(lastName);
            Assert.assertTrue(createAccountDialog.getDisplayedLastName().equals(lastName), "Entered last name doesn't match displayed value");
            createAccountDialog.typePhone(phone);
            Assert.assertTrue(createAccountDialog.getDisplayedPhone().equals(phone),"Entered phone doesn't match displayed value");
            createAccountDialog.typeWebsite(website);
            Assert.assertTrue(createAccountDialog.getDisplayedWebsite().equals(website), "Entered website doesn't match displayed value");
        }

        createAccountDialog.typePassword(password);
        Assert.assertTrue(createAccountDialog.checkDisplayedPassword(), "Password wasn't replaced");
        createAccountDialog.clickCreateAccountButton();
        Assert.assertTrue(createAccountDialog.checkYourEmailMessagePresent(), "'Check your email' message is not present");
        //TODO insert verification

    }

    @Step
    public void login(String login, String pass){
        clearSession();
        openHomePage();
        Pages.navigationBar().waitForNavigationBarLoad();
        Pages.navigationBar().clickLoginButton();
        loginDialog.typeEmail(login);
        loginDialog.typePassword(pass);
        loginDialog.clickLoginButton();
        Assert.assertTrue(false);
    }
}
