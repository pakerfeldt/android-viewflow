package org.taptwo.android.widget.viewflow.example;

import org.taptwo.android.widget.TitleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import android.app.Activity;
import android.os.Bundle;

public class DiffViewFlowExample extends Activity {

	private ViewFlow viewFlow;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_title);;
        
        setContentView(R.layout.title_layout);
        
		viewFlow = (ViewFlow) findViewById(R.id.viewflow);

        DiffAdapter adapter = new DiffAdapter(this);

        viewFlow.setAdapter(adapter);
		TitleFlowIndicator indicator = (TitleFlowIndicator) findViewById(R.id.viewflowindic);
		indicator.setTitleProvider(adapter);
		viewFlow.setFlowIndicator(indicator);
    }

 
}
