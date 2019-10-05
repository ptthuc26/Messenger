package com.example.messenger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterMess extends RecyclerView.Adapter<AdapterMess.ViewHolder> {


    List<Message> messageList;
    Context context;
    SharedPreferences sharedPreferences;

    public AdapterMess(List<Message> messageList, Context context) {
        this.messageList = messageList;
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @NonNull
    @Override
    public AdapterMess.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_mess, parent, false);

        ViewHolder viewhoder = new ViewHolder(view);
        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMess.ViewHolder holder, int position) {
        Message message = messageList.get(position);

        holder.tvName1.setText(message.getName());
        holder.tvMess.setText(message.getMess());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName1, tvMess;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName1 = itemView.findViewById(R.id.tvName1);
            tvMess = itemView.findViewById(R.id.tvMess);
        }
    }
}
