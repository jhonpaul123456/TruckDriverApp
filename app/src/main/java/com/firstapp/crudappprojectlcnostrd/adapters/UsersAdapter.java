package com.firstapp.crudappprojectlcnostrd.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.crudappprojectlcnostrd.R;
import com.firstapp.crudappprojectlcnostrd.databinding.UserLayoutBinding;
import com.firstapp.crudappprojectlcnostrd.model.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    List<User> users;
    Context context;

    public UsersAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(UserLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder. binding.id.setText(users.get(position).getId());
        holder.binding.driverName.setText(context.getResources().getString(R.string.driver_name_formatted, users.get(position).getDriverName()));
        holder.binding.plateNumber.setText(context.getResources().getString(R.string.plate_number_formatted, users.get(position).getPlateNumber()));
        holder.binding.truckCondition.setText(context.getResources().getString(R.string.truck_condition_formatted, users.get(position).getTruckCondition()));
        holder.binding.truckType.setText(context.getResources().getString(R.string.truck_type_formatted, users.get(position).getTruckType()));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        UserLayoutBinding binding;
        public UserViewHolder(@NonNull UserLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
