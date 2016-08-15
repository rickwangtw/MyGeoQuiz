package com.mysticwind.android.bignerdranch.training.mygeoquiz.manager;

import com.mysticwind.android.bignerdranch.training.mygeoquiz.model.Question;
import com.mysticwind.android.bignerdranch.training.mygeoquiz.model.QuizState;

public class QuizManagerImpl implements QuizManager {

    private final Question[] questions;
    private int currentIndex = 0;
    private int answeredIndex = -1;

    public QuizManagerImpl(Question[] questions) {
        this.questions = questions;
    }

    @Override
    public int startQuiz() {
        currentIndex = 0;
        return getTextResourceId();
    }

    @Override
    public int previousQuiz() {
        if (currentIndex == answeredIndex) {
            currentIndex = (currentIndex + questions.length - 1) % questions.length;
        } else {
            // question is not answered yet, do nothing
        }
        return getTextResourceId();
    }

    @Override
    public int nextQuiz() {
        if (currentIndex == answeredIndex) {
            currentIndex = (currentIndex + 1) % questions.length;
        } else {
            // question is not answered yet, do nothing
        }
        return getTextResourceId();
    }

    @Override
    public boolean answer(boolean enteredAnswer) {
        answeredIndex = currentIndex;
        boolean isAnswerCorrect = questions[answeredIndex].validateAnswer(enteredAnswer);
        return isAnswerCorrect;
    }

    @Override
    public boolean peekAnswer() {
        return questions[currentIndex].isAnswerTrue();
    }

    @Override
    public QuizState getQuizState() {
        return new QuizState(currentIndex, answeredIndex);
    }

    @Override
    public int continueQuiz(QuizState quizState) {
        currentIndex = quizState.getCurrentIndex();
        answeredIndex = quizState.getAnsweredIndex();
        return nextQuiz();
    }

    public int getTextResourceId() {
        return questions[currentIndex].getTextResourceId();
    }
}
