package org.taptwo.android.widget.viewflow.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.taptwo.android.widget.TitleProvider;
import org.taptwo.android.widget.viewflow.example.R;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;


public class AsyncAdapter extends BaseAdapter implements TitleProvider {

	private LayoutInflater mInflater;
	
	private static final DateFormat dfTitle = new SimpleDateFormat("E, dd MMM");
	
	private static final int daysDepth = 10;
	private static final int daysSize = daysDepth * 2 + 1;
	
	private static Date[] dates = new Date[ daysSize ];
	private static String[] content = new String[ daysSize ];
	
	
	private class ViewHolder {
		ProgressBar mProgressBar;
		View mContent;
		TextView mDate;
	}
	
	
	public AsyncAdapter(Context context) {
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		prepareDates();
	}
	
	@Override
	public String getItem(int position) {
		return content[position];
	}

	@Override
	public long getItemId(int position) {
		return position; 
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return drawView(position, convertView);
	}

	private View drawView(int position, View view) {
		ViewHolder holder = null;
		
		if(view == null) {
			view = mInflater.inflate(R.layout.day_view, null);
			
			holder = new ViewHolder();

			holder.mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
			holder.mDate = (TextView) view.findViewById(R.id.date);
			holder.mContent = (View) view.findViewById(R.id.content);
			
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}		


		final String o = getItem(position);
		if (o != null) {
			holder.mProgressBar.setVisibility(View.GONE);
			holder.mDate.setText(o);
			holder.mContent.setVisibility(View.VISIBLE);
		}
		else {
			new LoadContentTask().execute(position, view);

			holder.mContent.setVisibility(View.GONE);
			holder.mProgressBar.setVisibility(View.VISIBLE);
		}
	
		return view;
	}

	@Override
	public String getTitle(int position) {
		return dfTitle.format( dates[position] );
	}

	@Override
	public int getCount() {
		return dates.length;
	}

	public int getTodayId() {
		return daysDepth;
	}

	public Date getTodayDate() {
		return dates[daysDepth];
	}
	
	/**
	 * Prepare dates for navigation, to past and to future
	 */
	private void prepareDates() {
		Date today = new Date();

		Calendar calPast = Calendar.getInstance();
		Calendar calFuture = Calendar.getInstance();

		calPast.setTime(today);
		calFuture.setTime(today);

		dates[ daysDepth ] = calPast.getTime();
		for (int i = 1; i <= daysDepth; i++) {
			calPast.add( Calendar.DATE, -1 );
			dates[ daysDepth - i ] = calPast.getTime();

			calFuture.add( Calendar.DATE, 1 );
			dates[ daysDepth + i ] = calFuture.getTime();
		}
	}
	
	
	private class LoadContentTask extends AsyncTask<Object, Object, Object> {
		
		private Integer position;
		private View view;
		
		@Override
		protected Object doInBackground(Object... arg) {
			position = (Integer) arg[0];
			view = (View) arg[1];

// long-term task is here 			
			try {
				Thread.sleep(3000); // do nothing for 3000 miliseconds (3 second)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			return getTitle(position);
		}

		protected void onPostExecute(Object result) {
// process result    	 
			content[position] = (String) result;
			
	    	drawView(position, view);

	    	view.postInvalidate();
	     }

	}	

}
