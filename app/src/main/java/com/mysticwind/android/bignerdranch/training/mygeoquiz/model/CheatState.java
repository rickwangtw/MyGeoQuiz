package com.mysticwind.android.bignerdranch.training.mygeoquiz.model;

import java.io.Serializable;

public class CheatState implements Serializable {

    private final int quizUniqueId;
    private final boolean isAnswerTrue;
    private final boolean isAnswerShown;

    public CheatState(int quizUniqueId, boolean isAnswerTrue, boolean isAnswerShown) {
        this.quizUniqueId = quizUniqueId;
        this.isAnswerTrue = isAnswerTrue;
        this.isAnswerShown = isAnswerShown;
    }

    public int getQuizUniqueId() {
        return quizUniqueId;
    }

    public boolean isAnswerTrue() {
        return isAnswerTrue;
    }

    public boolean isAnswerShown() {
        return isAnswerShown;
    }
}
