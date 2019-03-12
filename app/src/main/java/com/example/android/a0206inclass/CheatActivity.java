package com.example.android.a0206inclass;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "com.example.android.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.example.android.answer_shown";


    private boolean mAnswerIsTrue;
    private TextView mAnswerView;
    private Button mShowAnswer;


    public static Intent newIntent(Context packageContext, boolean answerIsTrue){
        Intent intent = new Intent(packageContext,CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN,false);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);

        mAnswerView = (TextView)findViewById(R.id.cheat_answer);

        mShowAnswer = (Button)findViewById(R.id.button_show_answer);

        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAnswerIsTrue){
                    mAnswerView.setText(R.string.true_button);
                } else {
                    mAnswerView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }

        });



    }

    public void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK,data);

    }
}
