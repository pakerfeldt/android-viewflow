/*
 * Copyright (C) 2011 Patrik Åkerfeldt
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

import org.taptwo.android.widget.TitleProvider;
import org.taptwo.android.widget.viewflow.example.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AndroidVersionAdapter extends BaseAdapter implements TitleProvider {

	private LayoutInflater mInflater;

	private static final String[] versions = {"1.5","1.6","2.1","2.2","2.3","3.0","x.y" };
	private static final String[] names = {"Cupcake","Donut","Eclair","Froyo","Gingerbread","Honeycomb","IceCream Sandwich" };
	
	public AndroidVersionAdapter(Context context) {
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
}
	
	@Override
	public int getCount() {
		return names.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position; 
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.flow_item, null);
		}
		((TextView) convertView.findViewById(R.id.textLabel)).setText(versions[position]);
		return convertView;
	}

	/* (non-Javadoc)
	 * @see org.taptwo.android.widget.TitleProvider#getTitle(int)
	 */
	@Override
	public String getTitle(int position) {
		return names[position];
	}

}
