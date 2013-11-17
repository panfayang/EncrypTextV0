///**
// * @author Kevin Davison, Sam Evans-Golden, Fayang Pan
// * 
// * This class handles deletion of a key.
// * 
// */
//package com.example.encryptextv0;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.View;
//import android.widget.EditText;
//
//public class Delete_Keys extends Activity {
//
//	private Key_Manager keyManager; 
//	private String name;
//	EditText delete; 
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_delete__keys);
//		delete = (EditText) findViewById(R.id.enterDelete);
//		
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.delete__keys, menu);
//		return true;
//	}
//		
//	
//	
//	public void deleteButton(View v)
//	{
//		
//		keyManager = new Key_Manager(this);
//		keyManager.deleteKey(name);;
//	}
//
//}
