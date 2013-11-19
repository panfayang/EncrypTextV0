package com.example.encryptextv0;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.swipedismiss.SwipeDismissListViewTouchListener;
import com.example.encryptextv0.Key_Contract.KeyEntry;

public class View_List extends Activity {
	private SQLiteDatabase db;
	private ArrayList<String> keyList = new ArrayList<String>();	
	ListView list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view__list);
		list = (ListView) findViewById(R.id.ListView1);
		openDatabase();
		final ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, keyList);
		list.setAdapter(mAdapter);
//		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//			public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id)
//			{
//
//				Key_Manager keyManager = new Key_Manager(getApplicationContext());
//				db = keyManager.getWritableDatabase();
//				db.execSQL("DELETE FROM "+ KeyEntry.TABLE_NAME + 
//						" WHERE " + KeyEntry.COLUMN_NAME_NAME + "=" + 
//						"\""+ ((String) list.getItemAtPosition(position)).split("\n")[0] + "\"");
//				Toast.makeText(View_List.this, "Key will be deleted", Toast.LENGTH_LONG).show();
//			}
//		});
		
		SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        list,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                	Key_Manager keyManager = new Key_Manager(getApplicationContext());
                    				db = keyManager.getWritableDatabase();
                    				db.execSQL("DELETE FROM "+ KeyEntry.TABLE_NAME + 
                    						" WHERE " + KeyEntry.COLUMN_NAME_NAME + "=" + 
                    						"\""+ ((String) list.getItemAtPosition(position)).split("\n")[0] + "\"");
                    				mAdapter.remove(mAdapter.getItem(position));
                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        });
        list.setOnTouchListener(touchListener);
        // Setting this scroll listener is required to ensure that during ListView scrolling,
        // we don't look for swipes.
        list.setOnScrollListener(touchListener.makeScrollListener());

	}

	private void openDatabase()
	{
		try{
			Key_Manager key_Manager = new Key_Manager(this.getApplicationContext());
			db = key_Manager.getWritableDatabase();
			String[] keys = {
					KeyEntry.COLUMN_NAME_NAME,
					KeyEntry.COLUMN_NAME_KEY
			};
			Cursor cursor = db.query(KeyEntry.TABLE_NAME, keys, null, null, null, null, null);
			if(cursor != null)
				cursor.moveToFirst();

			do
			{
				String keyName = cursor.getString(cursor.getColumnIndex(KeyEntry.COLUMN_NAME_NAME));
				String keyKey = cursor.getString(cursor.getColumnIndex(KeyEntry.COLUMN_NAME_KEY));
				keyList.add(keyName + "\n" + keyKey);
			}while(cursor.moveToNext());
		}

		catch (SQLiteException se)
		{
			Log.e("could not create key", null);	
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view__list, menu);
		return true;
	}

}
