package com.saman.zhihuad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void click(View view) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, MainActivity.class);
        if (view.getId() == R.id.b1) {
            bundle.putInt("index", 0);
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (view.getId() == R.id.b2) {
            bundle.putInt("index", 1);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
