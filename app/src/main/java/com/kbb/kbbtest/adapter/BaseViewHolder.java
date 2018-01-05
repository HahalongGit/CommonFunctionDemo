package com.kbb.kbbtest.adapter;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 龙龙同学 on 2017/8/9.
 *
 * @ClassName: BaseViewHolder
 * @Description:
 * @Date 2017/8/9
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;

    private View convertView;

    protected BaseViewHolder(View itemView) {
        super(itemView);
        convertView = itemView;
        this.views = new SparseArray<>();

    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }


    protected BaseViewHolder setText(@IdRes int viewid, CharSequence value) {
        TextView view = getView(viewid);
        view.setText(value);
        return this;
    }

    protected BaseViewHolder setText(@IdRes int viewid, @StringRes int strid) {
        TextView textView = getView(viewid);
        textView.setText(strid);
        return this;
    }

    protected BaseViewHolder setImageResource(@IdRes int resid, @DrawableRes int imageResid) {
        ImageView imageView = getView(resid);
        imageView.setImageResource(imageResid);
        return this;
    }


}
