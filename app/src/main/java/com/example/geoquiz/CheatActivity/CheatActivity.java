package com.example.geoquiz.CheatActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.ViewAnimationUtils;
import android.animation.AnimatorListenerAdapter;
import com.example.geoquiz.R;

public class CheatActivity extends AppCompatActivity implements CheatContract.CheatView {
    private TextView mAnswerTextView;
    private Button mShowAnswerButton;
    private Button mHonestButton;

    CheatPresenter mCheatPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mCheatPresenter = new CheatPresenter(this);

        mCheatPresenter.passInAnswer();

        mAnswerTextView = findViewById(R.id.answer_text_view);

        mShowAnswerButton = findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheatPresenter.handleCheatButtonClick();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int cx = mShowAnswerButton.getWidth() / 2;
                    int cy = mShowAnswerButton.getHeight() / 2;
                    float radius = mShowAnswerButton.getWidth();
                    Animator anim = ViewAnimationUtils
                            .createCircularReveal(mShowAnswerButton, cx, cy, radius, 0);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mShowAnswerButton.setVisibility(View.INVISIBLE);
                        }
                    });
                    anim.start();
                } else {
                    mShowAnswerButton.setVisibility(View.INVISIBLE);
                }
                finish();
            }
        });

        mHonestButton = findViewById(R.id.return_button);
        mHonestButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mCheatPresenter.handleHonestButtonClick();
                finish();
                return true;
            }
        });
    }

    // Methods from Contract
    @Override
    public void showHonestButtonClick() {
    }

    @Override
    public void showCheatButtonClick() {
    }

    @Override
    public void showAnswer(int idToShow) {
        mAnswerTextView.setText(idToShow);
    }
}
