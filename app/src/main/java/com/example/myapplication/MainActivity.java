package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    private  EditText name,password;
    private Button saveBtn,showBtn;
    private TextView showData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.nameEditText);
        password=findViewById(R.id.passwordEditText);
        saveBtn=findViewById(R.id.saveButton);
        showBtn=findViewById(R.id.showButton);
        showData=findViewById(R.id.showTextView);
        saveBtn.setOnClickListener(this);
        showBtn.setOnClickListener(this);
    }

    public void  onClick(View view){
        if(view.getId()==R.id.saveButton){
            String userName=name.getText().toString();
            String userPassword=password.getText().toString();
            if(userName.equals("")&&userPassword.equals("")){
                Toast.makeText(getApplicationContext(),"Please field-up data",Toast.LENGTH_SHORT);
                return;
            }
            SharedPreferences sharedPreferences=getSharedPreferences("userData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("userNameKey",userName);
            editor.putString("passwordKey",userPassword);
            editor.commit();
            Toast.makeText(getApplicationContext(),"data save successfully",Toast.LENGTH_SHORT);
            name.setText("");
            password.setText("");

        } else if (view.getId()==R.id.showButton) {
            SharedPreferences sharedPreferences=getSharedPreferences("userData", Context.MODE_PRIVATE);
            if(sharedPreferences.contains("userNameKey")&&sharedPreferences.contains("passwordKey")){
                showData.setText(sharedPreferences.getString("userNameKey","data not found")+"\n"+ sharedPreferences.getString("passwordKey","Data not found"));

            }
        }
    }
}