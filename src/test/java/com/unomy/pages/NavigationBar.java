package com.unomy.pages;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.Locator;
import com.qatestlab.base.LocatorTypes;
import ru.yandex.qatools.allure.annotations.Step;

public class NavigationBar extends BasePage {

    private Locator bar = new Locator(LocatorTypes.XPATH, "//div[contains(@class, 'top-bar')]");
    private Locator loginButton = bar.concat(new Locator(LocatorTypes.XPATH, "//a[text()='Login']"));
    private Locator tryItFreeButton = bar.concat(new Locator(LocatorTypes.XPATH, "//a[text()='Try it Free']"));

    protected NavigationBar(){}



    @Step
    public void waitForNavigationBarLoad(){
        waitToBeClickable(tryItFreeButton);
        waitToBeClickable(loginButton);
    }

    @Step
    public void clickLoginButton(){
        click(loginButton);
    }

    @Step
    public void clickTryItFreeButton(){
        click(tryItFreeButton);
    }
}
