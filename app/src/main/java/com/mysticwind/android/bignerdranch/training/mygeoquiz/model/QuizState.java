package com.mysticwind.android.bignerdranch.training.mygeoquiz.model;

import java.io.Serializable;

public class QuizState implements Serializable {

    private final int currentIndex;
    private final int answeredIndex;

    public QuizState(int currentIndex, int answeredIndex) {
        this.currentIndex = currentIndex;
        this.answeredIndex = answeredIndex;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getAnsweredIndex() {
        return answeredIndex;
    }
}
