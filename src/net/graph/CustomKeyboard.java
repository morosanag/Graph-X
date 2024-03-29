/**
 * Copyright 2013 Maarten Pennings
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * If you use this software in a product, an acknowledgment in the product
 * documentation would be appreciated but is not required.
 */

package net.graph;

import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;

/**
 * When an activity hosts a keyboardView, this class allows several EditText's to register for it.
 *
 * @author Maarten Pennings
 * @date   2012 December 23
 */
class CustomKeyboard {

	/** A link to the KeyboardView that is used to render this CustomKeyboard. */
	private KeyboardView mKeyboardView;
	/** A link to the activity that hosts the {@link #mKeyboardView}. */
	private Activity     mHostActivity;
	private View layout;
	ScrollView scrollview;
	private int height;
	/** The key (code) handler. */
	private OnKeyboardActionListener mOnKeyboardActionListener = new OnKeyboardActionListener() {

		public final static int CodeDelete   = -5; // Keyboard.KEYCODE_DELETE
		public final static int CodePi   = -10; // 
		public final static int CodeLeft   = -15; // 
		public final static int CodeCancel   = -3; 
		public final static int CodeAllLeft  = 55001;
        public final static int CodeRight    = 55003;
        public final static int CodeAllRight = 55004;
        public final static int CodeNext     = 55005;
        public final static int CodeClear    = 55006;


		@Override public void onKey(int primaryCode, int[] keyCodes) {
			// NOTE We can say '<Key android:codes="49,50" ... >' in the xml file; all codes come in keyCodes, the first in this list in primaryCode
			// Get the EditText and its Editable
			View focusCurrent = mHostActivity.getWindow().getCurrentFocus();
			if( focusCurrent==null || focusCurrent.getClass()!=EditText.class ) return;
			EditText edittext = (EditText) focusCurrent;
			Editable editable = edittext.getText();
			int start = edittext.getSelectionStart();
			// Apply the key to the edittext

			if( primaryCode==CodeDelete ) {
				if( editable!=null && start>0 ) editable.delete(start - 1, start);
			}
			else if( primaryCode==CodePi)
			{
				//\u03C0
				editable.append("\u03C0");
			}
			else if (primaryCode==CodeLeft)
			{
				editable.append("");
			}
			else if( primaryCode==CodeCancel ) {
                hideCustomKeyboard();
            }
			else if( primaryCode==CodeAllRight ) {
                edittext.setSelection(edittext.length());
			}
			else { // insert character
				editable.insert(start, Character.toString((char) primaryCode));
			}
		}

		@Override public void onPress(int arg0) {
		}

		@Override public void onRelease(int primaryCode) {
		}

		@Override public void onText(CharSequence text) {
		}

		@Override public void swipeDown() {
			hideCustomKeyboard();
		}

		@Override public void swipeLeft() {
			hideCustomKeyboard();
		}

		@Override public void swipeRight() {
			hideCustomKeyboard();
		}

		@Override public void swipeUp() {
		}
	};

	/**
	 * Create a custom keyboard, that uses the KeyboardView (with resource id <var>viewid</var>) of the <var>host</var> activity,
	 * and load the keyboard layout from xml file <var>layoutid</var> (see {@link Keyboard} for description).
	 * Note that the <var>host</var> activity must have a <var>KeyboardView</var> in its layout (typically aligned with the bottom of the activity).
	 * Note that the keyboard layout xml file may include key codes for navigation; see the constants in this class for their values.
	 * Note that to enable EditText's to use this custom keyboard, call the {@link #registerEditText(int)}.
	 *
	 * @param host The hosting activity.
	 * @param viewid The id of the KeyboardView.
	 * @param layoutid The id of the xml file containing the keyboard layout.
	 */
	public CustomKeyboard(Activity host, int viewid, int layoutid,View layout, ScrollView scrollview) {
		this.layout=layout;
		this.scrollview=scrollview;
		mHostActivity= host;
		mKeyboardView= (KeyboardView) layout.findViewById(viewid);
		Log.d("Debug",mKeyboardView+"");
		mKeyboardView.setKeyboard(new Keyboard(mHostActivity, layoutid));
		mKeyboardView.setPreviewEnabled(false); // NOTE Do not show the preview balloons
		mKeyboardView.setOnKeyboardActionListener(mOnKeyboardActionListener);

		mKeyboardView.setBackgroundResource(0);




		Keyboard key = new Keyboard(mHostActivity, layoutid);


		// Hide the standard keyboard initially
		height = layout.getHeight() ;
	}

	/** Returns whether the CustomKeyboard is visible. */
	public boolean isCustomKeyboardVisible() {
		return mKeyboardView.getVisibility() == View.VISIBLE;
	}

	/** Make the CustomKeyboard visible, and hide the system keyboard for view v. */
	public void showCustomKeyboard( View v ) {  
		mKeyboardView.setVisibility(View.VISIBLE);
		mKeyboardView.setEnabled(true);

		if( v!=null ) ((InputMethodManager)mHostActivity.getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(), 0);
		mHostActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

	}

	/** Make the CustomKeyboard invisible. */
	public void hideCustomKeyboard() {
		mKeyboardView.setVisibility(View.GONE);
		mKeyboardView.setEnabled(false);
		try
		{
			scrollview.setLayoutParams(new LinearLayout.LayoutParams(layout.getWidth(),height));
		}
		catch(Exception ex)
		{

		}
	}

	/**
	 * Register <var>EditText<var> with resource id <var>resid</var> (on the hosting activity) for using this custom keyboard.
	 *
	 * @param resid The resource id of the EditText that registers to the custom keyboard.
	 */
	public void registerEditText(EditText edittext) {
		// Find the EditText 'resid'
		//EditText edittext= (EditText)layout.findViewById(resid);  
		// Make the custom keyboard appear
		edittext.setOnFocusChangeListener(new OnFocusChangeListener() {
			// NOTE By setting the on focus listener, we can show the custom keyboard when the edit box gets focus, but also hide it when the edit box loses focus
			@Override public void onFocusChange(View v, boolean hasFocus) {
				if( hasFocus ) showCustomKeyboard(v); else hideCustomKeyboard();
				Log.d("Debug","!!!!!!");   
				Log.d("Debug","scroll: "+scrollview);

				android.view.ViewGroup.LayoutParams params1 = layout.getLayoutParams();

				height = layout.getHeight() ;
				try
				{
					scrollview.setLayoutParams(new LinearLayout.LayoutParams(layout.getWidth(),height*2/3));
				}
				catch(Exception ex)
				{

				}

			}
		});
		edittext.setOnClickListener(new OnClickListener() {
			// NOTE By setting the on click listener, we can show the custom keyboard again, by tapping on an edit box that already had focus (but that had the keyboard hidden).
			@Override public void onClick(View v) {
				showCustomKeyboard(v);

			}
		});
		// Disable standard keyboard hard way
		// NOTE There is also an easy way: 'edittext.setInputType(InputType.TYPE_NULL)' (but you will not have a cursor, and no 'edittext.setCursorVisible(true)' doesn't work )
		edittext.setOnTouchListener(new OnTouchListener() {
			@Override public boolean onTouch(View v, MotionEvent event) {
				EditText edittext = (EditText) v;
				int inType = edittext.getInputType();       // Backup the input type
				edittext.setInputType(InputType.TYPE_NULL); // Disable standard keyboard
				edittext.onTouchEvent(event);               // Call native handler
				edittext.setInputType(inType);              // Restore input type
				return true; // Consume touch event
			}
		});
		// Disable spell check (hex strings look like words to Android)
		edittext.setInputType(edittext.getInputType() | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
	}

}


// NOTE How can we change the background color of some keys (like the shift/ctrl/alt)?
// NOTE What does android:keyEdgeFlags do/mean
