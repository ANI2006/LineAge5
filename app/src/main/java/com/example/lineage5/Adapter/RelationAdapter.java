package com.example.lineage5.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lineage5.OnClickItemInterface;
import com.example.lineage5.ProjectModel;
import com.example.lineage5.R;
import com.example.lineage5.RelationUser;
import com.example.lineage5.databinding.RelationItemLayoutBinding;
import com.example.lineage5.databinding.UserItemLayoutBinding;

import java.util.List;

public class RelationAdapter extends RecyclerView.Adapter<RelationAdapter.ViewHolder>{

    List<RelationUser> relationUserList;

    private OnClickItemInterface onClickItemInterface;

    public RelationAdapter(OnClickItemInterface onClickItemInterface) {
        this.onClickItemInterface = onClickItemInterface;
    }

    public RelationAdapter() {
        super();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RelationItemLayoutBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.relation_item_layout,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(relationUserList!=null){
            RelationUser relationUser=relationUserList.get(position);

            holder.binding.setRelationUser(relationUser);
            holder.binding.setListener(onClickItemInterface);
        }
    }

    @Override
    public int getItemCount() {
        if(relationUserList!=null)
            return relationUserList.size();
        else return 0;
    }

    public void setRelations(List<RelationUser> projects ){
        relationUserList=projects;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RelationItemLayoutBinding binding;

        public ViewHolder(@NonNull RelationItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }

}