package com.shzlabs.themenu.ui.login;

import com.shzlabs.themenu.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by shaz on 18/9/17.
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    @Inject
    public LoginPresenter() {
    }

    void loadWelcomeMsg() {
        getView().displayWelcomeMsg("Hello friend, please login now!");
    }

    void loadWelcomeMsg(String msg) {
        getView().displayWelcomeMsg(msg);
    }

    void loadSigningInScreen() {
        getView().displayWelcomeMsg("Signing in ...");
        getView().displaySigningInProgress(true);
    }
}
