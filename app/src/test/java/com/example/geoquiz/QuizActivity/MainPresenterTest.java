package com.example.geoquiz.QuizActivity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class MainPresenterTest {
    @Mock
    private ActivityContract.QuizView mQuizView;

    private MainPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mPresenter = new MainPresenter(mQuizView);
    }

    @Test
    public void handleBackButtonClick() {
        mPresenter.handleBackButtonClick();
        verify(mQuizView).showBackButtonClick();
    }

    @Test
    public void handleForwardButtonClick() {
        mPresenter.handleForwardButtonClick();
        verify(mQuizView).showForwardButtonClick();
    }

    @Test
    public void handleCheatButtonClick() {
        mPresenter.handleCheatButtonClick();
        //verify(mQuizView).showCheatButtonClick(); // how to handle this?
    }

    @Test
    public void handleTrueButtonClick() {
        mPresenter.handleTrueButtonClick();
        verify(mQuizView).showTrueButtonClick();
    }

    @Test
    public void handleFalseButtonClick() {
        mPresenter.handleFalseButtonClick();
        verify(mQuizView).showFalseButtonClick();
    }

    @Test
    public void handleQuestionClick() {
        mPresenter.handleQuestionClick();
        //verify(mQuizView).showQuestionClick(); // how to handle this?
    }


}