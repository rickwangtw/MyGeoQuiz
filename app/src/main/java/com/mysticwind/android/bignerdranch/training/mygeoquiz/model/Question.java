package com.mysticwind.android.bignerdranch.training.mygeoquiz.model;

public class Question {

    private final int textResourceId;
    private final boolean isAnswerTrue;

    public Question(int textResourceId, boolean isAnswerTrue) {
        this.textResourceId = textResourceId;
        this.isAnswerTrue = isAnswerTrue;
    }

    public int getTextResourceId() {
        return textResourceId;
    }

    public boolean validateAnswer(boolean answer) {
        return answer == isAnswerTrue;
    }

    public boolean isAnswerTrue() {
        return isAnswerTrue;
    }
}
