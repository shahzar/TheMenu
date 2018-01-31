package com.shzlabs.themenu.ui.base;

/**
 * Created by shaz on 18/9/17.
 */

public class BasePresenter<T extends BaseView> {

    private T view;

    public void attachView(T view) {
        this.view = view;
    }

    public void detachView() {
        view= null;
    }

    public T getView() {
        return view;
    }
}
