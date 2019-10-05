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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Contact> contacts;
    RecyclerView recyclerView;
    View vAdd;
    AdapterContact adapterContact;

    int mPosition = -1;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvList);
        vAdd = findViewById(R.id.vAdd);
        contacts = new ArrayList<>();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        editor = sharedPreferences.edit();

        contacts.add(new Contact("Mr.A"));
        contacts.add(new Contact("Mr.B"));
        contacts.add(new Contact("Mr.C"));
        contacts.add(new Contact("Mr.D"));
        contacts.add(new Contact("Mr.E"));
        contacts.add(new Contact("Mr.F"));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL,false);
        adapterContact = new AdapterContact(contacts,getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterContact);

        adapterContact.setiOnClickContact(new IOnClickContact() {
            @Override
            public void onClickName(String name, int i, View view) {
                mPosition = i;
                final Intent intent1 = new Intent(getBaseContext(),AddContact.class);
                intent1.putExtra("name", name);

                PopupMenu popupMenu = new PopupMenu(getBaseContext(),view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_itemcontact,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.mnSend:
                                Intent intent = new Intent(getBaseContext(),SendMess.class);
                                startActivity(intent);
                                break;
                            case R.id.mnEdit:
                                startActivityForResult(intent1,113);
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        vAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddContact.class);
                intent.putExtra("name", "");
                startActivityForResult(intent,115);
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
//                Intent intent = new Intent(getBaseContext(),MainActivity.class);
//                startActivity(intent);
                break;
            case R.id.mnMessages:
                Intent intent1 = new Intent(getBaseContext(),Messages.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode){
            case RESULT_OK:
                String name = data.getStringExtra("nameAdd");
                if(requestCode == 113){
                    contacts.set(mPosition, new Contact(name));
                    adapterContact.notifyDataSetChanged();
                }
                else if(requestCode == 115){
                    contacts.add(new Contact(name));
                    adapterContact.notifyDataSetChanged();
                }
        }
    }
}
