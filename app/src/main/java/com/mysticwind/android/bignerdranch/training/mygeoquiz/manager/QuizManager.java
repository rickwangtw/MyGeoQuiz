package com.mysticwind.android.bignerdranch.training.mygeoquiz.manager;

public interface QuizManager {
    int startQuiz();
    int nextQuiz();
    boolean answer(boolean enteredAnswer);
}
