package com.example.android.a0206inclass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Quizactivity";
    private static final int REQUEST_CODE_CHEAT =0;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private Button mPreviousButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_europe, true),
            new Question(R.string.question_ocean, false),
            new Question(R.string.question_city, false),
            new Question(R.string.question_river, true),
            new Question(R.string.question_americas, false),
            new Question(R.string.question_asia, true)
    };

    private int mCurrentIndex = 0;

    private boolean mIsCheater;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called ");
        setContentView(R.layout.activity_main);


        mTrueButton = (Button) findViewById(R.id.button_true);


        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }

        });

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);


        mFalseButton = (Button) findViewById(R.id.button_false);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });


        mNextButton = (Button) findViewById(R.id.button_next);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;

                mIsCheater = false;
                updateQuestion();
            }
        });

        mPreviousButton = (Button) findViewById(R.id.button_previous);

        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex-1)%mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();
            }
        });

        mCheatButton= (Button) findViewById(R.id.button_cheat);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismAnswerTrue();
                Intent intent = CheatActivity.newIntent(MainActivity.this,answerIsTrue);
                startActivityForResult(intent,REQUEST_CODE_CHEAT);
            }
        });

        updateQuestion();
    }




    public void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getmTextResId();
        mQuestionTextView.setText(question);
    }

    public void checkAnswer(boolean userPressTrue) {

        boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismAnswerTrue();
        int messageResId = 0;

        if (mIsCheater){
            messageResId = R.string.judgement_toast;
        }else {
            if (userPressTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }

        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }

    protected void onActivityResult(int RequestCode,int ResultCode, Intent data){
        if(ResultCode != Activity.RESULT_OK){
            return;
        }

        if (RequestCode ==REQUEST_CODE_CHEAT){
            if (data == null){
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }


    }
        @Override
        public void onStart () {
            super.onStart();
            Log.d(TAG, "onStart()called");
        }

        @Override
        public void onResume () {
            super.onResume();
            Log.d(TAG, "onResume()called");
        }

        @Override
        public void onPause () {
            super.onPause();
            Log.d(TAG, "onPause()called");
        }

        @Override
        public void onStop () {
            super.onStop();
            Log.d(TAG, "onStop()called");
        }

        @Override
        public void onDestroy () {
            super.onDestroy();
            Log.d(TAG, "onDestroy()called");
        }


}
