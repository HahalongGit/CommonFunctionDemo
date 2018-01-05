package com.kbb.kbbtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kbb.kbbtest.R;
import com.kbb.kbbtest.widget.WrapRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by longlong on 2017/11/7.
 *
 * @ClassName: RecycleViewAdapter
 * @Description:
 * @Date 2017/11/7
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private List<String> list;

    public RecycleViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.recycle_wrap_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.tvTitle.setText("RecycleView item title-"+position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_content)
        TextView tvContent;


        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }

}
