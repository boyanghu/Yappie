package com.bo.yang.hu.yappie.ui;

import java.io.File;

import android.content.Context;
import android.graphics.Typeface;

public class FontProvider {
	
	private static final String FONTS_FOLDERNAME = "fonts";
	private static final String COMFORTAA_REGULAR_FILENAME = "Comfortaa_Regular.ttf";
	
	private static Typeface primaryFont;

	public static Typeface getFont(Context context) {
		if (primaryFont == null) {
			primaryFont = createFont(context);
		}
		return primaryFont;
	}
	
	private static Typeface createFont(Context context) {
		return getComfortaaRegular(context);
	}
	
	private static Typeface getComfortaaRegular(Context context) {
		return Typeface.createFromAsset(context.getAssets(), FONTS_FOLDERNAME + File.separator + COMFORTAA_REGULAR_FILENAME);
	}
	

}
