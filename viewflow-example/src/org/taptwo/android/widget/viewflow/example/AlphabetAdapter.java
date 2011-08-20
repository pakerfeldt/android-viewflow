package org.taptwo.android.widget.viewflow.example;

import org.taptwo.android.widget.TitleProvider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AlphabetAdapter extends BaseAdapter implements TitleProvider {
	
	private LayoutInflater mInflater;	
	private static final String[] ALPHABET = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

	public AlphabetAdapter(Context context) {
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}	
	
	@Override
	public int getCount() {
		return ALPHABET.length;
	}

	@Override
	public Object getItem(int position) {
		return ALPHABET[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.letter_item, null);
		}
		((TextView) convertView.findViewById(R.id.letterView)).setText(ALPHABET[position]);
		return convertView;
	}

	@Override
	public String getTitle(int position) {
		return ALPHABET[position];
	}

}
