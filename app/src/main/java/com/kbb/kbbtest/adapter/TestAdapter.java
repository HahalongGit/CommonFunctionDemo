package com.kbb.kbbtest.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kbb.kbbtest.R;
import com.kbb.kbbtest.bean.ProductInfo;

import java.util.List;

/**
 * Created by 龙龙同学 on 2017/8/9.
 *
 * @ClassName: TestAdapter
 * @Description:
 * @Date 2017/8/9
 */

public class TestAdapter extends BaseAdapter<ProductInfo,TestAdapter.MyViewHolder> {

    private List<ProductInfo> infoList;

    private Context context;

    public TestAdapter(Context context, List<ProductInfo> datas) {
        super(datas);
        this.context = context;
        this.infoList = datas;
    }

    @Override
    public void bindViewHlder(MyViewHolder holder, int pos) {
        holder.title.setText(infoList.get(pos).getName());
        holder.content.setText(infoList.get(pos).getProductInfo());
    }

    @Override
    public int getAdapterLayout() {
        return R.layout.recycler_item_layout;
    }


    public static class MyViewHolder extends BaseViewHolder{

        private ImageView imageView;

        private TextView title;

        private TextView content;

        protected MyViewHolder(View itemView) {
            super(itemView);
            imageView = getView(R.id.iv_image);
            title = getView(R.id.tv_title);
            title = getView(R.id.tv_showInfo);
        }
    }

}
