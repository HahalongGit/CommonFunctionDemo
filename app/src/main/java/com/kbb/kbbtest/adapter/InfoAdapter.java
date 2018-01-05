package com.kbb.kbbtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kbb.kbbtest.R;
import com.kbb.kbbtest.bean.ProductInfo;

import java.util.List;

/**
 * Created by 龙龙同学 on 2017/8/9.
 *
 * @ClassName: InfoAdapter
 * @Description:
 * @Date 2017/8/9
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.TestViewHolder>{

    private List<ProductInfo> list ;

    private Context context;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public InfoAdapter(List<ProductInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_layout,parent,false);

        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TestViewHolder holder, int position) {
        holder.tv_showInfo.setText(list.get(position).getProductInfo());
        holder.tv_title.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(holder.itemView,holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class TestViewHolder2 extends BaseViewHolder{

        protected TestViewHolder2(View itemView, Context context) {
            super(itemView);
        }
    }


    public class TestViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_image;

        private TextView tv_title;

        private TextView tv_showInfo;

        public TestViewHolder(View itemView) {
            super(itemView);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_showInfo = (TextView) itemView.findViewById(R.id.tv_showInfo);
        }
    }

    public interface OnItemClickListener{

        void onItemClick(View view,int pos);

    }

}
