package com.unomy.pages;

import com.qatestlab.base.BasePage;
import com.qatestlab.base.ObjectsCollection;
import com.unomy.pages.main.MainPage;

public final class Pages {
    private static ObjectsCollection<BasePage> pages = new ObjectsCollection<>();

    public static void clear() {
        pages.clear();
    }

    public static MainPage mainPage(){return pages.getInstance(MainPage.class);}

    public static NavigationBar navigationBar(){return pages.getInstance(NavigationBar.class);}


}
