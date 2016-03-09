package com.example.systembartintdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void openNew(View v){
		Intent intent =new Intent(getActivity(),MainActivity.class);
		startActivity(intent);
	}
}
