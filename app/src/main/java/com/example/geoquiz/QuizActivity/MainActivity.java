package com.example.geoquiz.QuizActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.geoquiz.R;

public class MainActivity extends AppCompatActivity implements ActivityContract.QuizView  {

    private MainPresenter mMainPresenter;
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mBeforeButton;
    private TextView mQuestionTextView;
    private Button mCheatButton;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(bundle) called");
        setContentView(R.layout.activity_main);
        mMainPresenter = new MainPresenter(this);

        if (savedInstanceState != null) {
            mMainPresenter.updateIndexFromBundle(savedInstanceState);
        }

        mQuestionTextView = findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                mMainPresenter.handleQuestionClick();
            }
        });

        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPresenter.handleTrueButtonClick();
            }
        });

        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPresenter.handleFalseButtonClick();
            }
        });

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPresenter.handleForwardButtonClick();
            }
        });

        mBeforeButton = findViewById(R.id.before_button);
        mBeforeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPresenter.handleBackButtonClick();
            }
        });

        mCheatButton = findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPresenter.handleCheatButtonClick();
            }
        });
        mMainPresenter.updateQuestion();
    }

    @Override
    public void onEnd(){
        Toast.makeText(MainActivity.this, "No More Questions!", Toast.LENGTH_SHORT).show();
        mMainPresenter.updateQuestion();
    }

    // Dealing with the result of CheatActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mMainPresenter.checkCheater(requestCode, resultCode, data);
    }

    // Lifecycle Activity Methods used for future/more advanced features
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override //Saves the needed information before the Activity is destroyed (ie. changes orientation)
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        mMainPresenter.saveInstance(savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    // Helper functions
    @Override
    public void changeQuestion(final int questionIndex) {
        mQuestionTextView.setText(questionIndex);
    }

    @Override
    public void showClickToastResult(int id){
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
    }

    // Methods responding to the touches for future uses
    @Override
    public void showBackButtonClick() {
    }

    @Override
    public void showForwardButtonClick() {
    }

    @Override
    public void showCheatButtonClick() {
    }

    @Override
    public void showTrueButtonClick() {
    }

    @Override
    public void showFalseButtonClick() {
    }

    @Override
    public void showQuestionClick() {
    }
}
