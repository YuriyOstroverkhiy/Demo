package com.qatestlab.base;

import com.qatestlab.properties.Properties;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BasePage extends DriverContainer {

    public static int ELEMENT_MICRO_TIMEOUT_SECONDS = 1;
    public static int ELEMENT_EXTRASMALL_TIMEOUT_SECONDS = 5;
    public static int ELEMENT_SMALL_TIMEOUT_SECONDS = 15;
    public static int ELEMENT_TIMEOUT_SECONDS = 30;
    public static int ELEMENT_LONG_TIMEOUT_SECONDS = 60;
    public static int ELEMENT_EXTRALONG_TIMEOUT_SECONDS = 120;
    public static int ELEMENT_MEGA_EXTRALONG_TIMEOUT_SECONDS = 300;
    public static int FOUR_MINUTES = 240;

    public static String BASE_URL = Properties.getBaseUrl();

    public static boolean isBeta = BASE_URL.contains("beta");

    @Step
    protected void click(Locator locator, Object... args) {
       
        WebElement element = driver().findElement(locator.getLocator(args));
        driver().switchTo().defaultContent();
        element.click();
    }

    @Step
    protected void click( WebElement element) {
        element.click();
    }

    @Step
    protected void clickJS( Locator locator, Object... args) {
        ((JavascriptExecutor) driver()).executeScript("arguments[0].click()", driver().findElement(locator.getLocator(args)));
    }

    @Step
    protected void clickJS( WebElement element) {
        ((JavascriptExecutor) driver()).executeScript("arguments[0].click()", element);
    }

    @Step
    protected void type(String value, Locator locator, Object... args) {
        WebElement input = driver().findElement(locator.getLocator(args));
        input.clear();
        input.sendKeys(value);
    }

    @Step
    protected void typeNewline( Locator locator, Object... args) {
        WebElement input = driver().findElement(locator.getLocator(args));
        input.clear();
        input.sendKeys(Keys.ENTER);
    }

    @Step
    protected Dimension getDimension( Locator locator, Object... args){
        WebElement element = driver().findElement(locator.getLocator(args));
        return element.getSize();
    }

    @Step
    protected List<WebElement> getElements( Locator locator, Object... args){
        List<WebElement> elements = driver().findElements(locator.getLocator(args));
        return elements;
    }

    @Step
    protected String getText( Locator locator, Object... args) {
        WebElement element = driver().findElement(locator.getLocator(args));
        String type = element.getTagName().toLowerCase();

        if (type.equals("input") || type.equals("textarea")) {
            String placeholder = element.getAttribute("placeholder");
            return (placeholder != null && placeholder.length() > 0)
                    ? element.getAttribute("value").replace(placeholder, "")
                    : element.getAttribute("value");
        }
        if (type.equals("select")) {
            return new Select(element).getFirstSelectedOption().getText();
        }
        return element.getText();
    }

    @Step
    protected void waitForPresence( Locator locator, Object... args) {
        waitForPresence(ELEMENT_TIMEOUT_SECONDS, locator, args);
    }

    @Step
    protected void waitForPresence(int timeout,  Locator locator, Object... args) {
        driver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver(), timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator.getLocator(args)));
        driver().manage().timeouts().implicitlyWait(ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    @Step
    protected void waitForVisibility( Locator locator, Object... args) {
        waitForVisibility(ELEMENT_TIMEOUT_SECONDS, locator, args);
    }

    @Step
    protected void waitForVisibility(int timeout,  Locator locator, Object... args) {
        driver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver(), timeout);
        /*Workaround to IEDriver bug with WebDriverException*/
        if (BaseTest.isDriverIE){
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator.getLocator(args)));
            } catch (WebDriverException e){
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator.getLocator(args)));
            }
        } else {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator.getLocator(args)));
        }
        driver().manage().timeouts().implicitlyWait(ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    @Step
    protected void waitForInvisibility( Locator locator, Object... args) {
        waitForInvisibility(ELEMENT_MICRO_TIMEOUT_SECONDS, ELEMENT_TIMEOUT_SECONDS,locator, args);
    }

    @Step
    protected void waitForInvisibility(int waitElementTimeout, int timeout,
            Locator locator, Object... args) {

        driver().manage().timeouts().implicitlyWait(waitElementTimeout, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver(), timeout);
        /*Workaround to IEDriver bug with WebDriverException*/
        if (BaseTest.isDriverIE){
            try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(locator.getLocator(args)));
            } catch (WebDriverException e){
                wait.until(ExpectedConditions.invisibilityOfElementLocated(locator.getLocator(args)));
            }
        } else {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator.getLocator(args)));
        }
        driver().manage().timeouts().implicitlyWait(ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    @Step
    protected void waitToBeClickable( Locator locator, Object... args) {
        waitToBeClickable(ELEMENT_TIMEOUT_SECONDS, locator, args);
    }

    @Step
    protected void waitToBeClickable(int timeout,  Locator locator, Object... args) {
        driver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver(), timeout);
        wait.until(ExpectedConditions.elementToBeClickable(locator.getLocator(args)));
        driver().manage().timeouts().implicitlyWait(ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);

        // wait until the element on the same place
        WebElement element = driver().findElement(locator.getLocator(args));
        Point location;
        do {
            location = element.getLocation();
            BaseActions.wait(0, 500);
        } while (!location.equals(element.getLocation()));
    }

    /*
    * Checkboxes
    */
    @Step
    protected boolean isCheckboxChecked(Locator locator, Object... args) {
        return driver().findElement(locator.getLocator(args)).isSelected();
    }

    @Step
    protected boolean isCheckboxChecked(WebElement element) {
        return element.isSelected();
    }

    @Step
    protected void setCheckboxState( boolean checked, Locator locator, Object... args) {
        if (checked ^ this.isCheckboxChecked(locator, args)) {
            this.click(locator, args);
        }
    }

    @Step
    protected void setCheckboxStateForAll( boolean checked, Locator locator, Object... args) {
        List<WebElement> elements = driver().findElements(locator.getLocator(args));
        for (WebElement element : elements) {
            if (checked && !isCheckboxChecked(element)) {
                //this.click(message, element);
                clickJS(element);
            }
        }


    }

    @Step
    protected int getCount( Locator locator, Object... args) {
        return this.getCount(0, locator, args);
    }

    @Step
    protected int getCount(int waitInSeconds,  Locator locator, Object... args) {
        driver().manage().timeouts().implicitlyWait(waitInSeconds, TimeUnit.SECONDS);
        int size = driver().findElements(locator.getLocator(args)).size();
        driver().manage().timeouts().implicitlyWait(ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        return size;
    }

    @Step
    protected boolean isPresent( Locator locator, Object... args) {
        return getCount(locator, args) > 0;
    }

    @Step
    protected boolean isPresent(int waitInSeconds,  Locator locator, Object... args) {
        return getCount(waitInSeconds, locator, args) > 0;
    }

    @Step
    protected void executeJS( String script, Locator locator, Object... args) {
        ((JavascriptExecutor) driver()).executeScript(script, driver().findElement(locator.getLocator(args)));
    }

    @Step
    protected void checkAlert(String message) {
        WebDriverWait wait = new WebDriverWait(driver(), 10);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver().switchTo().alert();
        alert.accept();
    }

    @Step
    protected String getAttributeValue( String attribute, Locator locator, Object... args) {
        WebElement element = driver().findElement(locator.getLocator(args));
        return getAttributeValue(attribute, element);
    }

    @Step
    protected String getAttributeValue( String attribute, WebElement element){
        String value = element.getAttribute(attribute);
        return value;
    }

    @Step
    protected boolean isAttributePresent( String attribute, Locator locator, Object... args) {
        boolean result = false;
        WebElement element = driver().findElement(locator.getLocator(args));
        String value = element.getAttribute(attribute);
        if (value != null)
            result = true;
        return result;
    }

    @Step
    protected void pasteText( Locator locator, Object... args) {
        WebElement element = driver().findElement(locator.getLocator(args));
        element.sendKeys(Keys.CONTROL, "v");

    }

    @Step
    protected void uploadFile( String filePath, Locator locator, Object... args) {
        WebElement fileInput = driver().findElement(locator.getLocator(args));
        fileInput.sendKeys(filePath);
    }

    @Step
    protected void dragAndDrop( Locator from, Locator to, Object... args) {
        WebElement fromElement = driver().findElement(from.getLocator(args));
        WebElement toElement = driver().findElement(to.getLocator());
        (new Actions(driver()).dragAndDrop(fromElement, toElement)).perform();
    }

    @Step
    protected void selectDropDownListOptionByText( String selectItemText, Locator locator, Object... args) {
        Select dropDownList = new Select(driver().findElement(locator.getLocator(args)));
        // if element has wrong value we can try select item only by text
        try {
            dropDownList.selectByValue(selectItemText);
        } catch (NoSuchElementException e) {
            dropDownList.selectByVisibleText(selectItemText);
        }
    }

    @Step
    protected boolean isEnabled( Locator locator, Object... args) {
        return driver().findElement(locator.getLocator(args)).isEnabled();
    }

    @Step
    protected void clickWithJS(Locator locator, Object... args) {
        final String javaScript = "if(document.createEvent){" +
                "var evObj = document.createEvent('MouseEvents');" +
                "evObj.initEvent('click', true, false);" + "" +
                "arguments[0].dispatchEvent(evObj);" +
                "} else if(document.createEventObject){" +
                "arguments[0].fireEvent('onclick');" +
                "}";
        ((JavascriptExecutor) driver()).executeScript(javaScript, driver().findElement(locator.getLocator(args)));
    }

}
