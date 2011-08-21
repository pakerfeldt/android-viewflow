/*
 * Copyright (C) 2011 Patrik �kerfeldt
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
package org.taptwo.android.widget;

import org.taptwo.android.widget.viewflow.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

/**
 * A FlowIndicator which draws circles (one for each view). The current view
 * position is filled and others are only striked.<br/>
 * <br/>
 * Availables attributes are:<br/>
 * <ul>
 * fillColor: Define the color used to fill a circle (default to white)
 * </ul>
 * <ul>
 * strokeColor: Define the color used to stroke a circle (default to white)
 * </ul>
 * <ul>
 * radius: Define the circle radius (default to 4.0)
 * </ul>
 */
public class CircleFlowIndicator extends View implements FlowIndicator {
	private static final int INACTIVE_STROKE = 0;
	private static final int INACTIVE_FILL = 1;
	private float radius = 4;
	private final Paint mPaintStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
	private final Paint mPaintFill = new Paint(Paint.ANTI_ALIAS_FLAG);
	private ViewFlow viewFlow;
	private int currentScroll = 0;
	private int flowWidth = 0;

	/**
	 * Default constructor
	 * 
	 * @param context
	 */
	public CircleFlowIndicator(Context context) {
		super(context);
		initColors(0xFFFFFFFF, 0xFFFFFFFF, INACTIVE_STROKE);
	}

	/**
	 * The contructor used with an inflater
	 * 
	 * @param context
	 * @param attrs
	 */
	public CircleFlowIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		// Retrieve styles attributs
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.CircleFlowIndicator);
		// Retrieve the colors to be used for this view and apply them.
		int activeColor = a.getColor(R.styleable.CircleFlowIndicator_fillColor,
				0xFFFFFFFF);
		// Gets the inactive circle type, defaulting to "stroke"
		int inactiveType = a.getInt(
				R.styleable.CircleFlowIndicator_inactiveType, INACTIVE_STROKE);
		// Work out the inactive color based on the type
		int inactiveDefaultColor = 0xFFFFFFFF;
		switch (inactiveType) {
		case INACTIVE_STROKE:
			inactiveDefaultColor = 0xFFFFFFFF;
			break;
		case INACTIVE_FILL:
			inactiveDefaultColor = 0x44FFFFFF;
		}
		// Get a custom inactive color if there is one
		int inactiveColor = a.getColor(
				R.styleable.CircleFlowIndicator_strokeColor,
				inactiveDefaultColor);

		// Retrieve the radius
		radius = a.getDimension(R.styleable.CircleFlowIndicator_radius, 4.0f);
		initColors(activeColor, inactiveColor, inactiveType);
	}

	private void initColors(int activeColor, int inactiveColor, int inactiveType) {
		// Select the paint type given the type attr
		switch (inactiveType) {
		case INACTIVE_STROKE:
			mPaintStroke.setStyle(Style.STROKE);
			break;
		case INACTIVE_FILL:
			mPaintStroke.setStyle(Style.FILL);
		}
		mPaintStroke.setColor(inactiveColor);

		mPaintFill.setStyle(Style.FILL);
		mPaintFill.setColor(activeColor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int count = 3;
		if (viewFlow != null) {
			count = viewFlow.getViewsCount();
		}
		// Draw stroked circles
		for (int iLoop = 0; iLoop < count; iLoop++) {
			canvas.drawCircle(getPaddingLeft() + radius
					+ (iLoop * (2 * radius + radius)),
					getPaddingTop() + radius, radius, mPaintStroke);
		}
		float cx = 0;
		if (flowWidth != 0) {
			// Draw the filled circle according to the current scroll
			cx = (currentScroll * (2 * radius + radius)) / flowWidth;
		}
		// The flow width has been upadated yet. Draw the default position
		canvas.drawCircle(getPaddingLeft() + radius + cx, getPaddingTop()
				+ radius, radius, mPaintFill);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.taptwo.android.widget.ViewFlow.ViewSwitchListener#onSwitched(android
	 * .view.View, int)
	 */
	@Override
	public void onSwitched(View view, int position) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.taptwo.android.widget.FlowIndicator#setViewFlow(org.taptwo.android
	 * .widget.ViewFlow)
	 */
	@Override
	public void setViewFlow(ViewFlow view) {
		viewFlow = view;
		flowWidth = viewFlow.getWidth();
		invalidate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.taptwo.android.widget.FlowIndicator#onScrolled(int, int, int,
	 * int)
	 */
	@Override
	public void onScrolled(int h, int v, int oldh, int oldv) {
		currentScroll = h;
		flowWidth = viewFlow.getWidth();
		invalidate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(measureWidth(widthMeasureSpec),
				measureHeight(heightMeasureSpec));
	}

	/**
	 * Determines the width of this view
	 * 
	 * @param measureSpec
	 *            A measureSpec packed into an int
	 * @return The width of the view, honoring constraints from measureSpec
	 */
	private int measureWidth(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		// We were told how big to be
		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		}
		// Calculate the width according the views count
		else {
			int count = 3;
			if (viewFlow != null) {
				count = viewFlow.getViewsCount();
			}
			result = (int) (getPaddingLeft() + getPaddingRight()
					+ (count * 2 * radius) + (count - 1) * radius + 1);
			// Respect AT_MOST value if that was what is called for by
			// measureSpec
			if (specMode == MeasureSpec.AT_MOST) {
				result = Math.min(result, specSize);
			}
		}
		return result;
	}

	/**
	 * Determines the height of this view
	 * 
	 * @param measureSpec
	 *            A measureSpec packed into an int
	 * @return The height of the view, honoring constraints from measureSpec
	 */
	private int measureHeight(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		// We were told how big to be
		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		}
		// Measure the height
		else {
			result = (int) (2 * radius + getPaddingTop() + getPaddingBottom() + 1);
			// Respect AT_MOST value if that was what is called for by
			// measureSpec
			if (specMode == MeasureSpec.AT_MOST) {
				result = Math.min(result, specSize);
			}
		}
		return result;
	}

	/**
	 * Sets the fill color
	 * 
	 * @param color
	 *            ARGB value for the text
	 */
	public void setFillColor(int color) {
		mPaintFill.setColor(color);
		invalidate();
	}

	/**
	 * Sets the stroke color
	 * 
	 * @param color
	 *            ARGB value for the text
	 */
	public void setStrokeColor(int color) {
		mPaintStroke.setColor(color);
		invalidate();
	}
}