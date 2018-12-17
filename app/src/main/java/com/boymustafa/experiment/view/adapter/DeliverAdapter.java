package com.boymustafa.experiment.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.boymustafa.experiment.R;
import com.boymustafa.experiment.databinding.ItemDeliverBinding;
import com.boymustafa.experiment.model.Delivers;
import com.boymustafa.experiment.viewModel.ItemDeliverViewModel;

import java.util.Collections;
import java.util.List;

public class DeliverAdapter extends RecyclerView.Adapter<DeliverAdapter.DeliverAdapterViewHolder> {

    private List<Delivers> deliversList;
    private final String TAG = DeliverAdapter.class.getSimpleName();

    public DeliverAdapter(){
        this.deliversList = Collections.emptyList();
    }

    @NonNull
    @Override
    public DeliverAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        ItemDeliverBinding itemDeliverBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_deliver,parent,false);
        Log.d(TAG,"masuk DeliverAdapter");
        return new DeliverAdapterViewHolder(itemDeliverBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliverAdapterViewHolder holder, int position) {
        holder.bindDeliver(deliversList.get(position));
    }

    @Override
    public int getItemCount() {
        return deliversList.size();
    }

    public void setDeliverList(List<Delivers> deliverList) {
        this.deliversList = deliverList;
        notifyDataSetChanged();
    }

    static class DeliverAdapterViewHolder extends RecyclerView.ViewHolder{
        ItemDeliverBinding mItemDeliverBinding;

       DeliverAdapterViewHolder(ItemDeliverBinding itemDeliverBinding){
           super(itemDeliverBinding.itemDeliver);
           this.mItemDeliverBinding = itemDeliverBinding;
       }

       void bindDeliver(Delivers delivers) {
           if (mItemDeliverBinding.getDeliverViewModel() == null) {
               mItemDeliverBinding.setDeliverViewModel(new ItemDeliverViewModel(delivers,itemView.getContext()));

           } else {
               mItemDeliverBinding.getDeliverViewModel().setDeliver(delivers);
           }
       }
    }

}
