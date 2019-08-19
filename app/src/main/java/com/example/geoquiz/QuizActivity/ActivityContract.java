package com.example.geoquiz.QuizActivity;

import android.content.Intent;

public interface ActivityContract {
    interface QuizPresenter{
        void handleBackButtonClick();
        void handleForwardButtonClick();
        void handleCheatButtonClick();
        void handleTrueButtonClick();
        void handleFalseButtonClick();
        void handleQuestionClick();
    }

    interface QuizView{
        void showClickToastResult(int index);
        void changeQuestion(int id);
         void showBackButtonClick();
         void showForwardButtonClick();
         void showCheatButtonClick();
         void showTrueButtonClick();
         void showFalseButtonClick();
         void showQuestionClick();
         void onEnd();
    }
}
