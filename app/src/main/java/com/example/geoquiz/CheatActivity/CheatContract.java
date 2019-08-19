package com.example.geoquiz.CheatActivity;

public interface CheatContract {
    interface CheatPresenter{
        void handleHonestButtonClick();
        void handleCheatButtonClick();
    }

    interface CheatView{
        void showHonestButtonClick();
        void showCheatButtonClick();
        void showAnswer(int id);
    }
}
