package com.bo.yang.hu.yappie.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView {

	public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		if (!isInEditMode()) {
			init(attrs);
		}
	}

	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (!isInEditMode()) {
			init(attrs);
		}

	}

	public CustomTextView(Context context) {
		super(context);
		if (!isInEditMode()) {
			init(null);
		}
	}

	private void init(AttributeSet attrs) {
		if (attrs != null) {
			setTypeface(FontProvider.getFont(getContext()));
		}
	}

}
