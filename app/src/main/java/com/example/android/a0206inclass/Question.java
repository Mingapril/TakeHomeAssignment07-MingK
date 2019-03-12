package com.example.android.a0206inclass;

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;

    public Question(int testResId, boolean answerTrue){
        mTextResId = testResId;
        mAnswerTrue = answerTrue;
    }


    public int getmTextResId() {
        return mTextResId;
    }

    public void setmTextResId(int mTextResId) {
        this.mTextResId = mTextResId;
    }

    public boolean ismAnswerTrue() {
        return mAnswerTrue;
    }

    public void setmAnswerTrue(boolean mAnswerTrue) {
        this.mAnswerTrue = mAnswerTrue;
    }
}
