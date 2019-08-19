package com.example.geoquiz.CheatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.example.geoquiz.R;

import static android.app.Activity.RESULT_OK;

public class CheatPresenter implements CheatContract.CheatPresenter {
    CheatContract.CheatView mCheatView;

    private boolean mAnswerIsTrue;

    private static final String EXTRA_ANSWER_IS_TRUE =
            "com.example.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN =
            "com.bignerdranch.android.geoquiz.answer_shown";

    CheatPresenter(CheatContract.CheatView CheatView){
        mCheatView = CheatView;
    }

    @Override
    public void handleHonestButtonClick() {
        setAnswerShownResult(false);
    }

    @Override
    public void handleCheatButtonClick() {
        int answerIdToShow;
        if (mAnswerIsTrue) {
            answerIdToShow = R.string.true_button;
        }
        else {
            answerIdToShow = R.string.false_button;
        }
        setAnswerShownResult(true);
        mCheatView.showAnswer(answerIdToShow);
    }

    // Static Method Creates Intent
    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    void passInAnswer(){
        mAnswerIsTrue = ((CheatActivity)mCheatView).getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        if(!isAnswerShown){ data.putExtra("DID_NOT_CHEAT", true); }
        ((CheatActivity)mCheatView).setResult(RESULT_OK, data);
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }
}
