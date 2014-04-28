package HLib;

/*
 * Copyright (C) 2008 The Android Open Source Project
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

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.RelativeLayout;

/**
 * A special variation of RelativeLayout that can be used as a checkable object.
 * This allows it to be used as the top-level view of a list view item, which
 * also supports checking.  Otherwise, it works identically to a RelativeLayout.
 */
public class CheckableRelativeLayout extends RelativeLayout implements Checkable 
{
    private boolean mChecked;

    private static final int[] CHECKED_STATE_SET = {
        android.R.attr.state_checked
    };


    public CheckableRelativeLayout(Context context) {
        super(context);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void toggle() {
        setChecked(!mChecked);
    }
   
    public boolean isChecked() {
        return mChecked;
    }

    public void setChecked(boolean checked) {
        if (mChecked != checked) {
            mChecked = checked;
            refreshDrawableState();
        }
    }
    
    /**************************/
    /** Drawable States **/
    /**************************/

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
    	final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
    	if (isChecked()) {
    		mergeDrawableStates(drawableState, CHECKED_STATE_SET);
    	}
    	return drawableState;
    }
     
    @Override
    protected void drawableStateChanged() {
    	super.drawableStateChanged();
    	 
    	Drawable drawable = getBackground();
    	if (drawable != null) {
    		int[] myDrawableState = getDrawableState();
    		drawable.setState(myDrawableState);
    		invalidate();
    	}
    }
    
   	/**************************/
   	/** State persistency **/
   	/**************************/
   	 
   	static class SavedState extends BaseSavedState 
   	{
   		boolean checked;
   	 
   		SavedState(Parcelable superState) {
   			super(superState);
   		}
   	 
   		private SavedState(Parcel in) {
   			super(in);
   			checked = (Boolean)in.readValue(null);
   		}
   	 
   		@Override
   		public void writeToParcel(Parcel out, int flags) {
   			super.writeToParcel(out, flags);
   			out.writeValue(checked);
   		}
   	 
   		@Override
   		public String toString() {
   			return "CheckableLinearLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this))
   			+ " checked=" + checked + "}";
   		}
   	 
   		public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() 
   		{
   				public SavedState createFromParcel(Parcel in) 
   				{
   					return new SavedState(in);
   				}
   	 
   				public SavedState[] newArray(int size) 
   				{
   					return new SavedState[size];
   				}
   		};
   	}
   	 
   	@Override
   	public Parcelable onSaveInstanceState() 
   	{
   		// Force our ancestor class to save its state
   		Parcelable superState = super.onSaveInstanceState();
   		SavedState ss = new SavedState(superState);
   		 
   		ss.checked = isChecked();
   		return ss;
   	}
   	 
   	@Override
   	public void onRestoreInstanceState(Parcelable state) 
   	{
   		SavedState ss = (SavedState) state;
   		 
   		super.onRestoreInstanceState(ss.getSuperState());
   		setChecked(ss.checked);
   		requestLayout();
   	}
}

