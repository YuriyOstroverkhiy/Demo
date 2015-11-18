package com.unomy.pages.main;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.ObjectsCollection;

public class MainPage extends BasePage {

    private static ObjectsCollection<BasePage> regions = new ObjectsCollection<>();

    protected MainPage(){}

    public static void clearRegions() {
        regions.clear();
    }

    public static CreateAccountDialog createAccountDialog(){return regions.getInstance(CreateAccountDialog.class);}

    public static LoginDialog loginDialog(){return regions.getInstance(LoginDialog.class);}

}
