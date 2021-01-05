package com.example.basemvp.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<D> extends RecyclerView.Adapter {
    List<D> list;
    protected Context context;

    MyClick myClick;

    public void setMyClick(MyClick myClick) {
        this.myClick = myClick;
    }

    protected IItemViewClick iItemViewClick;

    public void addItemViewClick(IItemViewClick click) {
        this.iItemViewClick = click;
    }

    public BaseAdapter(List<D> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int layout = getLayout();

        if (layout <= 0) {
            throw new RuntimeException("布局非法");
        }
        View inflate = LayoutInflater.from(context).inflate(layout, parent, false);
        VH vh = new VH(inflate);

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myClick != null) {
                    myClick.click(vh.getLayoutPosition());
                }
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        bindData(list.get(position), (VH) holder);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected abstract int getLayout();

    protected abstract void bindData(D data, VH vh);

    //定义接口回调
    public interface MyClick {
        void click(int p);
    }

    public interface IItemViewClick<D> {
        //条目中的元素点击
        void itemViewClick(int viewid, D data);
    }

    public class VH extends RecyclerView.ViewHolder {
        SparseArray sparseArray = new SparseArray();

        public VH(@NonNull View itemView) {
            super(itemView);
        }

        //查找item的view
        public View getViewById(int id) {

            View view = (View) sparseArray.get(id);

            if (view == null) {
                view = itemView.findViewById(id);
                sparseArray.append(id, view);
            }
            return view;
        }
    }


}
