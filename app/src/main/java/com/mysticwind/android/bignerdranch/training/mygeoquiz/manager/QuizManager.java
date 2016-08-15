package com.mysticwind.android.bignerdranch.training.mygeoquiz.manager;

import com.mysticwind.android.bignerdranch.training.mygeoquiz.model.QuizState;

public interface QuizManager {
    int startQuiz();
    int previousQuiz();
    int nextQuiz();
    boolean answer(boolean enteredAnswer);

    // TODO not exposing these APIs for persisting states
    QuizState getQuizState();
    int continueQuiz(QuizState quizState);
}
