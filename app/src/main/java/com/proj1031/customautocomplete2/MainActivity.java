package com.proj1031.customautocomplete2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    CustomMultiAutoCompleteTextView mactv;
    ArrayAdapter<Spanned> adapter;
    CustomMultiAutoCompleteTextView.Tokenizer mytokenizer;
    SpannableStringBuilder sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);


        mactv = new CustomMultiAutoCompleteTextView(this);

        //adapter is the format of the dropdown menu
        adapter = new ArrayAdapter<Spanned>(this, android.R.layout.simple_dropdown_item_1line);

        //adding default values to the dropdown menu.
        String[] names = {"Jane", "John", "Mary", "Mark"};
        for (String name : names) {
            TextView tv = new TextView(this);
            tv.setText(name);
            SpannableStringBuilder sb = new SpannableStringBuilder(name);
            sb.setSpan(new ViewReplacementSpan(tv), 0, sb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            adapter.add(sb);
        }

        mytokenizer = new CustomTokenizer();
        mactv.setAdapter(adapter);
        mactv.setTokenizer(mytokenizer);
        mactv.setThreshold(1);
        mactv.addTextChangedListener(myTextWatcher);


        //add default text in mactv
        SpannableStringBuilder sb = new SpannableStringBuilder();
        for (int i = 0; i < 2; i++) {
            sb.append(mytokenizer.terminateToken(adapter.getItem(i)));
        }
        mactv.setText(sb);



        layout.addView(mactv);
        setContentView(layout);


    }
//////////////// End of onCreate

    private final TextWatcher myTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        public void afterTextChanged(Editable text) {
            int i = text.length();
            if (text.charAt(i-1) == ':') {
                String newString = text.toString();
                // select 20 past letters assuming that no name is longer than 20 letters.
                newString = newString.substring(i-20, i-1);
                if (newString.lastIndexOf('@') != -1){
                    int atindex = newString.lastIndexOf('@');
                    newString = newString.substring(atindex+1);
                    text = new SpannableStringBuilder(newString);
                    mactv.replaceText(text);
                }

                //text.append("hi");
            } else{

            }
        }
    };



    //unused right now
    private static String[] NAMES = new String[] {
            "Minchul", "Cedric", "Abby", "Daniel"
    };


}
