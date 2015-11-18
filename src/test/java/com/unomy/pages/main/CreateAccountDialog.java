package com.unomy.pages.main;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;
import ru.yandex.qatools.allure.annotations.Step;

public class CreateAccountDialog extends BasePage {

    private Locator dialog = new Locator(LocatorTypes.ID, "modal-create-account");
    private Locator firstName = dialog.concat(new Locator(LocatorTypes.ID, "first-name-form"));
    private Locator lastName = dialog.concat(new Locator(LocatorTypes.ID, "last-name-form"));
    private Locator email = dialog.concat(new Locator(LocatorTypes.ID, "company-email-form"));
    private Locator phone = dialog.concat(new Locator(LocatorTypes.ID, "phone-number-form"));
    private Locator website = dialog.concat(new Locator(LocatorTypes.ID, "company-website-form"));
    private Locator password = dialog.concat(new Locator(LocatorTypes.ID, "password-form"));
    private Locator createAccountButton = dialog.concat(new Locator(LocatorTypes.XPATH, "//button[text()='Create an account']"));
    private Locator checkYourEmail = new Locator(LocatorTypes.XPATH, "//h3[text()='Please check your email']");


    protected CreateAccountDialog(){}

    @Step
    public void waitForTrialDialogLoad(){
        waitForVisibility(firstName);
    }

    @Step
    public void typeFirstName(String value){
        type(value, firstName);
    }

    @Step
    public void typeLastName(String value){
        type(value, lastName);
    }

    @Step
    public void typeEmail(String value){
        type(value, email);
    }

    @Step
    public void typePhone(String value){
        type(value, phone);
    }

    @Step
    public void typeWebsite(String value){
        type(value, website);
    }

    @Step
    public void typePassword(String value){
        type(value, password);
    }

    @Step
    public void clickCreateAccountButton(){
        click(createAccountButton);
    }

    @Step
    public String getDisplayedFirstName(){
        return getAttributeValue("value", firstName);
    }

    @Step
    public String getDisplayedLastName(){
        return getAttributeValue("value", lastName);
    }

    @Step
    public String getDisplayedEmail(){
        return getAttributeValue("value", email);
    }

    @Step
    public String getDisplayedPhone(){
        return getAttributeValue("value", phone);
    }

    @Step
    public String getDisplayedWebsite(){
        return getAttributeValue("value", website);
    }

    @Step
    public boolean checkDisplayedPassword(){
        boolean passwordReplaced = getAttributeValue("type", password).equals("password");
        return passwordReplaced;
    }

    @Step
    public boolean checkYourEmailMessagePresent(){
        return isPresent(checkYourEmail);
    }
}
