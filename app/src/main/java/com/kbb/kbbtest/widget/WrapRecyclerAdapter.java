package com.kbb.kbbtest.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by longlong on 2017/11/7.
 *
 * @ClassName: WrapRecyclerAdapter
 * @Description:
 * @Date 2017/11/7
 */

public class WrapRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // 包装 adapter 是原来的 RecyclerView.Adapter 是并不支持添加头部和底部的
    private RecyclerView.Adapter mRealAdapter;
    ArrayList<View> mHeaderViews; // 头部
    ArrayList<View> mFooterViews; // 底部

    public WrapRecyclerAdapter(RecyclerView.Adapter mRealAdapter) {
        this.mRealAdapter = mRealAdapter;
        mHeaderViews = new ArrayList<>();
        mFooterViews = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        // Header (negative positions will throw an IndexOutOfBoundsException)
        //viewType --> position position 就是viewType
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return createFooterHeaderViewHolder(mHeaderViews.get(position));
        }
        // Adapter
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mRealAdapter != null) {
            adapterCount = mRealAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mRealAdapter.onCreateViewHolder(parent, mRealAdapter.getItemViewType(adjPosition));
            }
        }

        // Footer (off-limits positions will throw an IndexOutOfBoundsException)
        return createFooterHeaderViewHolder(mFooterViews.get(adjPosition - adapterCount));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return;
        }
        // Adapter
        final int adjPosition = position - numHeaders;
        if (mRealAdapter != null) {
            int adapterCount = mRealAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mRealAdapter.onBindViewHolder(holder, adjPosition);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mRealAdapter.getItemCount() + mHeaderViews.size() + mFooterViews.size();
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    private RecyclerView.ViewHolder createFooterHeaderViewHolder(View view) {
        return new RecyclerView.ViewHolder(view){
        };
    }

    /**
     * 添加底部View
     * @param view
     */
    public void addFooterView(View view) {
        if (!mFooterViews.contains(view)) {
            mFooterViews.add(view);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加头部View
     * @param view
     */
    public void addHeaderView(View view) {
        if (!mHeaderViews.contains(view)) {
            mHeaderViews.add(view);
            notifyDataSetChanged();
        }
    }

    /**
     * 移除底部View
     * @param view
     */
    public void removeFooterView(View view) {
        if (mFooterViews.contains(view)) {
            mFooterViews.remove(view);
            notifyDataSetChanged();
        }
    }

    /**
     * 移除头部View
     * @param view
     */
    public void removeHeaderView(View view) {
        if (mHeaderViews.contains(view)) {
            mHeaderViews.remove(view);
            notifyDataSetChanged();
        }
    }

}
