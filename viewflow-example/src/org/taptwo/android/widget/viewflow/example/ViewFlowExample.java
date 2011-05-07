/*
 * Copyright (C) 2011 Tap2 AB <http://taptwo.se>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.taptwo.android.widget.viewflow.example;

import org.taptwo.android.widget.ViewFlow;
import org.taptwo.android.widget.ViewFlow.ViewSwitchListener;
import se.taptwo.android.widget.viewflow.example.R;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewFlowExample extends Activity {

	private ViewFlow viewFlow;
	private Button[] buttons;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		LinearLayout header = (LinearLayout) findViewById(R.id.header_layout);
		buttons = new Button[header.getChildCount()];
		for (int i = 0; i < header.getChildCount(); i++) {
			buttons[i] = (Button) header.getChildAt(i);
		}

		viewFlow = (ViewFlow) findViewById(R.id.viewflow);
		viewFlow.setOnViewSwitchListener(new ViewSwitchListener() {

			@Override
			public void onSwitched(View view) {
				int position = Integer.parseInt(((TextView) view
						.findViewById(R.id.textLabel)).getText().toString());
				for(int i = 0; i < buttons.length; i++) {
					if(i != position)
						buttons[i].setTextColor(Color.BLACK);
					else
						buttons[i].setTextColor(Color.MAGENTA);
				}
			}
		});
		viewFlow.setAdapter(new MyAdapter(this));
	}

	public void setCurrentView(View v) {
		Integer position = Integer
				.parseInt(((TextView) v).getText().toString());
		viewFlow.setSelection(position);
	}
}