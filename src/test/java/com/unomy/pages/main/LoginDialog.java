package com.unomy.pages.main;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;
import ru.yandex.qatools.allure.annotations.Step;

public class LoginDialog extends BasePage {

    private Locator dialog = new Locator(LocatorTypes.ID, "modal-log-in");
    private Locator email = dialog.concat(new Locator(LocatorTypes.ID, "email-form"));
    private Locator password = dialog.concat(new Locator(LocatorTypes.ID, "password-form"));
    private Locator loginButton = dialog.concat(new Locator(LocatorTypes.XPATH, "//button[text()='Login']"));
    private Locator forgotPassword = dialog.concat(new Locator(LocatorTypes.XPATH, "//a[text()='Forgot password?']"));


    protected LoginDialog(){}

    @Step
    public void waitForLoginDialogLoad(){
        waitForVisibility(dialog);
    }

    @Step
    public void typeEmail(String value){
        type(value, email);
    }

    @Step
    public void typePassword(String value){
        type(value, password);
    }

    @Step
    public void clickLoginButton(){
        click(loginButton);
    }

    @Step
    public String getDisplayedEmail(){
        return getAttributeValue("value", email);
    }

    @Step
    public boolean checkDisplayedPassword(){
        boolean passwordReplaced = getAttributeValue("type", password).equals("password");
        return passwordReplaced;
    }
}
