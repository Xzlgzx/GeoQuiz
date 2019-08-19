package com.example.geoquiz.QuizActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.example.geoquiz.CheatActivity.CheatActivity;
import com.example.geoquiz.CheatActivity.CheatPresenter;
import com.example.geoquiz.R;

public class MainPresenter implements ActivityContract.QuizPresenter{

    private static final int REQUEST_CODE_CHEAT = 0;
    private static final String KEY_INDEX = "index";
    private int mCurrentIndex = 0;
    private boolean mDidNotCheat;
    private boolean mIsCheater;
    private ActivityContract.QuizView mView;
    // Better data storage needed: potentially store the questions in a database/server
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_china, true),
            new Question(R.string.question_toronto, true),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    MainPresenter(ActivityContract.QuizView view){
        mView = view;
    }

    // Methods predefined in the contract
    @Override
    public void handleBackButtonClick() {
        mIsCheater = false;
        if(mCurrentIndex == 0){ mView.onEnd(); }
        else{
            mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
            updateQuestion();
        }
        mView.showBackButtonClick(); //What's this gonna do?
    }

    @Override
    public void handleForwardButtonClick() {
        mIsCheater = false;
        if(mCurrentIndex < mQuestionBank.length - 1){
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            updateQuestion();
        }
        else{ mView.onEnd(); }
        mView.showForwardButtonClick(); // what is this gonna do?
    }

    @Override
    public void handleCheatButtonClick() { //Handles CheatActivity from here
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        Intent intent = CheatPresenter.newIntent((MainActivity)mView, answerIsTrue);
        ((MainActivity)mView).startActivityForResult(intent, REQUEST_CODE_CHEAT);
        mView.showCheatButtonClick();
    }

    @Override
    public void handleTrueButtonClick() {
        checkAnswer(true);
        mView.showTrueButtonClick(); // For future usages if needed
    }

    @Override
    public void handleFalseButtonClick() {
        checkAnswer(false);
        mView.showFalseButtonClick(); // For future usages if needed
    }

    @Override
    public void handleQuestionClick() {
        if(mCurrentIndex == mQuestionBank.length - 1 || mCurrentIndex == 0){
            mView.onEnd();
        }
        else {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        }
        mView.showQuestionClick(); // For future usages if needed
        updateQuestion();
    }

    // Helper Functions
    void updateIndexFromBundle(Bundle bundle){ //extracts from bundle and updates the model layer
        if (bundle != null) {
            mCurrentIndex = bundle.getInt(KEY_INDEX, 0);
        }
    }

    void updateQuestion() {
        mView.changeQuestion(mQuestionBank[mCurrentIndex].getTextResId());
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        final int messageResId;

        if (mIsCheater) {
            mView.showClickToastResult(R.string.judgment_toast);
        }
        if(mDidNotCheat){
            mView.showClickToastResult(R.string.honest_toast); // Should be handled in cheat activity
            mDidNotCheat = false;
        }
        if (answerIsTrue == userPressedTrue) {
            messageResId = R.string.correct_toast;
        }
        else {
            messageResId = R.string.incorrect_toast;
        }

        Handler handler = new Handler();// A thread for handling a 2nd toast displayed after the 1st
        handler.postDelayed(new Runnable() {
            public void run() {
                mView.showClickToastResult(messageResId);
            }
        }, 2000);
    }

    void checkCheater(int requestCode, int resultCode, Intent data){ // helper to onActivityResult() to see if the user cheated
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            if(data.getBooleanExtra("DID_NOT_CHEAT", false)){
                mDidNotCheat = true;
                return;
            }
        }

        mIsCheater = CheatPresenter.wasAnswerShown(data);
    }

    void saveInstance(Bundle bundle){ // helper to onSaveInstanceState() to save CurrentIndex
        bundle.putInt(KEY_INDEX, mCurrentIndex);
    }
}
