package com.mysticwind.android.bignerdranch.training.mygeoquiz.manager;

import com.mysticwind.android.bignerdranch.training.mygeoquiz.model.Question;

public class QuizManagerImpl implements QuizManager {

    private final Question[] questions;
    private int currentIndex = 0;

    public QuizManagerImpl(Question[] questions) {
        this.questions = questions;
    }

    @Override
    public int startQuiz() {
        currentIndex = 0;
        return getTextResourceId();
    }

    @Override
    public int nextQuiz() {
        currentIndex = (currentIndex + 1) % questions.length;
        return getTextResourceId();
    }

    @Override
    public boolean answer(boolean enteredAnswer) {
        boolean isAnswerCorrect = questions[currentIndex].validateAnswer(enteredAnswer);
        return isAnswerCorrect;
    }

    public int getTextResourceId() {
        return questions[currentIndex].getTextResourceId();
    }
}
