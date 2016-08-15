package com.mysticwind.android.bignerdranch.training.mygeoquiz.manager;

public interface QuizManager {
    int startQuiz();
    int previousQuiz();
    int nextQuiz();
    boolean answer(boolean enteredAnswer);
}
