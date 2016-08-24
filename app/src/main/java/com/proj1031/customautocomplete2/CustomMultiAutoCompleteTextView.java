package com.proj1031.customautocomplete2;


import android.content.Context;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.QwertyKeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Filter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

/**
 * Created by MK on 8/23/16.
 */
class CustomMultiAutoCompleteTextView extends MultiAutoCompleteTextView {
    Context mContext;
    public CustomMultiAutoCompleteTextView(Context context) {
        super(context);
        mContext = context;

    }


    @Override
    protected void replaceText(CharSequence text) {
        String name = text.toString();
        TextView tv = new TextView(mContext);
        tv.setText(name);
        SpannableStringBuilder sb = new SpannableStringBuilder(name);
        sb.setSpan(new ViewReplacementSpan(tv), 0, sb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        super.replaceText(sb);
    }


}