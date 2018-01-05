package com.kbb.kbbtest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kbb.kbbtest.R;

/**
 * Created by 龙龙同学 on 2017/8/9.
 *
 * @ClassName: ProductViewHolder
 * @Description:
 * @Date 2017/8/9
 */

public class ProductViewHolder extends RecyclerView.ViewHolder {

    private ImageView iv_image;

    private TextView tv_title;

    private TextView tv_showInfo;

    public ProductViewHolder(View itemView) {
        super(itemView);
        iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
        tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        tv_showInfo = (TextView) itemView.findViewById(R.id.tv_showInfo);
    }

}
