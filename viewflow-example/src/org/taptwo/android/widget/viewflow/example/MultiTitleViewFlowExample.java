package org.taptwo.android.widget.viewflow.example;

import org.taptwo.android.widget.TitleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

public class MultiTitleViewFlowExample extends Activity {
	
	private ViewFlow viewFlow;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.multi_title_title);
		setContentView(R.layout.multi_title_layout);

		viewFlow = (ViewFlow) findViewById(R.id.viewflow);
		AlphabetAdapter adapter = new AlphabetAdapter(this);
		viewFlow.setAdapter(adapter);
		TitleFlowIndicator indicator = (TitleFlowIndicator) findViewById(R.id.viewflowindic);
		indicator.setTitleProvider(adapter);
		viewFlow.setFlowIndicator(indicator);

	}

	/*
	 * If your min SDK version is < 8 you need to trigger the
	 * onConfigurationChanged in ViewFlow manually, like this
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		viewFlow.onConfigurationChanged(newConfig);
	}
	
}
