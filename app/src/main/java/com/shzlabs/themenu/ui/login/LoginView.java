package com.shzlabs.themenu.ui.login;

import com.shzlabs.themenu.ui.base.BaseView;

/**
 * Created by shaz on 18/9/17.
 */

public interface LoginView extends BaseView{
    void displayWelcomeMsg(String message);
    void displaySigningInProgress(boolean status);
}
