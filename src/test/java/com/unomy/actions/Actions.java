package com.unomy.actions;

import com.qatestlab.base.BaseActions;
import com.qatestlab.base.ObjectsCollection;

public final class Actions {
    private static ObjectsCollection<BaseActions> actions = new ObjectsCollection<>();

    public static void clear() {
        actions.clear();
    }

    public static LoginActions loginActions(){return actions.getInstance(LoginActions.class);}

}
