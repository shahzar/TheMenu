package com.shzlabs.themenu.ui.base;

import android.support.v4.app.Fragment;

import com.shzlabs.themenu.TheApplication;

/**
 * Created by shaz on 20/9/17.
 */

public class BaseFragment extends Fragment {
    public TheApplication getTheApplication(){
        return ((TheApplication)getActivity().getApplication());
    }
}
