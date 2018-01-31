package com.shzlabs.themenu.ui.main;

import com.google.firebase.auth.FirebaseAuth;
import com.shzlabs.themenu.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by shaz on 18/9/17.
 */

public class MainPresenter extends BasePresenter<MainView> {

    @Inject
    FirebaseAuth firebaseAuth;

    @Inject
    public MainPresenter() {
    }

    public void signOutUser() {
        firebaseAuth.signOut();
        getView().displayLoginScreen();
    }

}
