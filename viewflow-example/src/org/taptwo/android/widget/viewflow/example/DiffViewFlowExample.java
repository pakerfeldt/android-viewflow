package org.taptwo.android.widget.viewflow.example;

import org.taptwo.android.widget.TitleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DiffViewFlowExample extends Activity {

	private ViewFlow viewFlow;
	private ListView listView;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.diff_title);
        setContentView(R.layout.title_layout);
        
		viewFlow = (ViewFlow) findViewById(R.id.viewflow);
        DiffAdapter adapter = new DiffAdapter(this);
        viewFlow.setAdapter(adapter);
		TitleFlowIndicator indicator = (TitleFlowIndicator) findViewById(R.id.viewflowindic);
		indicator.setTitleProvider(adapter);
		viewFlow.setFlowIndicator(indicator);
		
		/** To populate ListView in diff_view1.xml */
		listView = (ListView) findViewById(R.id.listView1);
		String[] names = new String[] { "Cupcake", "Donut", "Eclair", "Froyo",
				"Gingerbread", "Honeycomb", "IceCream Sandwich"};
		// Create an ArrayAdapter, that will actually make the Strings above
		// appear in the ListView
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, names));
		
    }
}
