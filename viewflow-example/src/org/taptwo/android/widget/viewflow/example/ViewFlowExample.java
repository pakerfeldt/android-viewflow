/**
 * 
 */
package org.taptwo.android.widget.viewflow.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author etaix
 * 
 */
public class ViewFlowExample extends Activity {

	ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listView = (ListView) findViewById(R.id.menu);
		String[] listeStrings = { "Circle indicator...", "Title indicator..." };
		listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeStrings));
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
				switch (position) {
				case 0:
					startActivity(new Intent(ViewFlowExample.this, CircleViewFlowExample.class));
					break;
				case 1:
					startActivity(new Intent(ViewFlowExample.this, TitleViewFlowExample.class));
					break;
				}
			}
		});
	}

}
