package com.example.tqlphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                Thread bamgio=new Thread(){
                    public void run()
                    {
                        try {
                            sleep(3000);
                        } catch (Exception e) {
                        }
                        finally
                        {
                            Intent intent1 = new Intent(MainActivity.this, dangnhap.class);
                            startActivity(intent1);
                        }
                    }
                };
                bamgio.start();
            }
            //sau khi chuyển sang màn hình chính, kết thúc màn hình chào
            protected void onPause(){
                super.onPause();
                finish();
            }
}