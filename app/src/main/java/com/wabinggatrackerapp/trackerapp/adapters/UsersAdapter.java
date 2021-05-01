package com.wabinggatrackerapp.trackerapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wabinggatrackerapp.trackerapp.R;
import com.wabinggatrackerapp.trackerapp.databinding.UserLayoutBinding;
import com.wabinggatrackerapp.trackerapp.model.Shizuka;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    List<Shizuka> shizukas;
    Context context;

    public UsersAdapter(List<Shizuka> shizukas, Context context) {
        this.shizukas = shizukas;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(UserLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder. binding.id.setText(String.valueOf(shizukas.get(position).getId()));
        holder.binding.name.setText(context.getResources().getString(R.string.name_formatted, shizukas.get(position).getName()));
        holder.binding.mobile.setText(context.getResources().getString(R.string.mobile_formatted, shizukas.get(position).getMobile()));
        holder.binding.street.setText(context.getResources().getString(R.string.street_formatted, shizukas.get(position).getStreet()));
        holder.binding.total.setText(context.getResources().getString(R.string.total_formatted, shizukas.get(position).getTotal()));
        holder.binding.status.setText(context.getResources().getString(R.string.status_formatted, shizukas.get(position).getStatus()));
        holder.binding.dates.setText(context.getString(R.string.dates_formatted, shizukas.get(position).getDates()));



    }

    @Override
    public int getItemCount() { return shizukas.size(); }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        UserLayoutBinding binding;
        public UserViewHolder(@NonNull UserLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
