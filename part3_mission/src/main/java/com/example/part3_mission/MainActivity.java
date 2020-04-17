package com.example.part3_mission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText nameView;
    EditText phoneView;
    EditText emailView;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameView=findViewById(R.id.edit_name);
        phoneView=findViewById(R.id.edit_phone);
        emailView=findViewById(R.id.edit_email);

        addBtn=findViewById(R.id.btn_add);

        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==addBtn){
            String name=nameView.getText().toString();
            String email=emailView.getText().toString();
            String phone=phoneView.getText().toString();

            if(name==null || name.equals("")){
                Toast toast=Toast.makeText(this, "이름이 입력되지 않았습니다. ", Toast.LENGTH_SHORT);
                toast.show();
            }else {
                DBHelper helper=new DBHelper(this);
                SQLiteDatabase db=helper.getWritableDatabase();
                db.execSQL("insert into tb_contact (name, phone, email) values (?,?,?)",
                        new String[] {name, phone, email});
                db.close();

                Toast toast=Toast.makeText(this, "새로운 주소록이 등록되었습니다.", Toast.LENGTH_SHORT);
                toast.show();

                Intent intent=new Intent(this, Mission1ResultActivity.class);
                startActivity(intent);
            }
        }
    }
}
