package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContact extends AppCompatActivity {

    String mName;
    EditText edName;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        edName = findViewById(R.id.edName);
        btnAdd = findViewById(R.id.btnAdd);

        final Intent intent = getIntent();
        mName = intent.getStringExtra("name");
        if (mName.equals("")) {
            btnAdd.setText("ADD");
        } else {
            btnAdd.setText("UPDATE");
            edName.setText(mName);
        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                intent1.putExtra("nameAdd",edName.getText().toString());

                setResult(RESULT_OK, intent1);
                finish();
            }
        });
    }
}
