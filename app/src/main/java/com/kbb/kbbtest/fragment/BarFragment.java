package com.kbb.kbbtest.fragment;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kbb.kbbtest.R;

/**
 * Created by 龙龙同学 on 2017/8/4.
 *
 * @ClassName: BarFragment
 * @Description:
 * @Date 2017/8/4
 */

public class BarFragment extends Fragment {

    private Context context;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.bar_fragment_layout,container,false);

        ValueAnimator.ofFloat();

        return view;
    }
}
