/**
 * @author Kevin Davison, Sam Evans-Golden, Fayang Pan
 * 
 * This class handles addition of a new key.
 */
package com.example.encryptextv0;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.algo.KeyParser;

public class Enter_Key extends Activity {

	EditText name;
	EditText key;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter__key);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enter__key, menu);
		return true;
	}

	public void addAKey(View v)
	{
		addKeyToDb();
	}

	public void addKeyToDb()
	{	
		name =((EditText)findViewById(R.id.enterName));
		key = ((EditText)findViewById(R.id.PasteKeyEdit));
		KeyParser kp = new KeyParser();

		if (name.getText().toString().length()<1 || (!kp.checkValidKey(key.getText().toString()))){
			Toast.makeText(getApplicationContext(), "Invalid key name or key, key not added", Toast.LENGTH_SHORT).show();
		}
		else{
			Key_Manager keyManager = new Key_Manager(this);
			keyManager.addKey(name.getText().toString(), key.getText().toString());
			Toast.makeText(getApplicationContext(), "Key added", Toast.LENGTH_SHORT).show();
		} 
	}

	public String newKey(){
		KeyParser kp = new KeyParser();
		return kp.parseToString(kp.randomKeyGenerator());
	}

	public void randomKey (View view){
		((EditText) findViewById(R.id.PasteKeyEdit)).setText(newKey());
	}
}
