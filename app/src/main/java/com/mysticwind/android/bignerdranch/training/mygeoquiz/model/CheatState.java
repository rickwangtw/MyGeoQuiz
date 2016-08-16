package com.mysticwind.android.bignerdranch.training.mygeoquiz.model;

import java.io.Serializable;

public class CheatState implements Serializable {

    private final boolean isAnswerTrue;
    private final boolean isAnswerShown;

    public CheatState(boolean isAnswerTrue, boolean isAnswerShown) {
        this.isAnswerTrue = isAnswerTrue;
        this.isAnswerShown = isAnswerShown;
    }

    public boolean isAnswerTrue() {
        return isAnswerTrue;
    }

    public boolean isAnswerShown() {
        return isAnswerShown;
    }
}
