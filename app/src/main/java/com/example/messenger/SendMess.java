package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SendMess extends AppCompatActivity {

    Spinner spFriend;
    EditText edSMS;
    Button btnSend;
    ArrayList<String> chooseFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mess);

        spFriend = findViewById(R.id.spFriend);
        edSMS = findViewById(R.id.edSMS);
        btnSend = findViewById(R.id.btnSend);

        chooseFriend = new ArrayList<>();
        chooseFriend.add("Mr.A");
        chooseFriend.add("Mr.B");
        chooseFriend.add("Mr.C");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_list_item_1,chooseFriend);
        spFriend.setAdapter(arrayAdapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),Messages.class);
                intent.putExtra("sms",edSMS.getText().toString());
                intent.putExtra("name1",spFriend.getSelectedItem().toString());
                startActivity(intent);
            }
        });
    }
}
