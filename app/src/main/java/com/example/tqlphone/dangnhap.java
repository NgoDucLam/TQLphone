package com.example.tqlphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tqlphone.DAO.NguoiDungDAO;

public class dangnhap extends AppCompatActivity {
    private NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        EditText txttendn = findViewById(R.id.txttendn);
        EditText txtmkdn = findViewById(R.id.txtmkdn);
        Button btndangnhap = findViewById(R.id.btndangnhap);
        TextView btnhuy = findViewById(R.id.btnhuy);

        nguoiDungDAO = new NguoiDungDAO(this);

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txttendn.getText().toString();
                String pass = txtmkdn.getText().toString();
                boolean check = nguoiDungDAO.CheckLogin(user, pass);

                if (check) {
                    Toast.makeText(dangnhap.this, "Đăng nhập succ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(dangnhap.this, Navigationdrawer.class));
                }else {
                    Toast.makeText(dangnhap.this, "Đăng nhập fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}