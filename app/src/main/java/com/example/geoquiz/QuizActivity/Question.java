package com.example.geoquiz.QuizActivity;

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;

    public Question(int TextResId, boolean AnswerTrue){
        mAnswerTrue = AnswerTrue; //holds the resource ID, always an integer
        mTextResId = TextResId;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) { // For future use
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) { // For future use
        mAnswerTrue = answerTrue;
    }
}
