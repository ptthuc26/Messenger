package com.example.messenger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Messages extends AppCompatActivity {

    List<Message> messages;
    RecyclerView recyclerView;
    View vSend;
    AdapterMess adapterMess;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        recyclerView = findViewById(R.id.rvMess);
        vSend = findViewById(R.id.vSend);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        editor = sharedPreferences.edit();

        messages = new ArrayList<>();

        messages.add(new Message("Mr A: ","How are you?"));
        messages.add(new Message("Mr B: ","I'm fine"));
        messages.add(new Message("Mr C: ","What time is it?"));
        messages.add(new Message("Mr D: ","I love you"));

        Intent intent = getIntent();
        messages.add(new Message(intent.getStringExtra("name1") + ": ",intent.getStringExtra("sms")));


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL,false);
        adapterMess = new AdapterMess(messages,getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterMess);

        vSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getBaseContext(),view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_send,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.mnSms:
                                Intent intent = new Intent(getBaseContext(),SendMess.class);
                                startActivity(intent);
                                break;
                            case R.id.mnEmail:
                                Intent intent1 = new Intent(getBaseContext(),SendMess.class);
                                startActivity(intent1);
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_popup,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnFriend:
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
                break;
            case R.id.mnMessages:
//                Intent intent1 = new Intent(getBaseContext(),Messages.class);
//                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
