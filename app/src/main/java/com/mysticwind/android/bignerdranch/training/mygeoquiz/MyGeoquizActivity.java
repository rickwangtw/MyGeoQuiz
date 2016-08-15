package com.mysticwind.android.bignerdranch.training.mygeoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mysticwind.android.bignerdranch.training.mygeoquiz.manager.QuizManager;
import com.mysticwind.android.bignerdranch.training.mygeoquiz.manager.QuizManagerImpl;
import com.mysticwind.android.bignerdranch.training.mygeoquiz.model.Question;

public class MyGeoquizActivity extends AppCompatActivity {

    private TextView quizTextView;
    private Button yesButton;
    private Button noButton;
    private Button previousButton;
    private Button nextButton;
    private int currentIndex = 0;

    // DI to handle questions and QuizManager
    private Question[] questions = new Question[] {
            new Question(R.string.Oceans, true),
            new Question(R.string.Mideast, false),
            new Question(R.string.Africa, false),
            new Question(R.string.Americas, true),
            new Question(R.string.Asia, true),
    };

    private final QuizManager quizManager = new QuizManagerImpl(questions);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_geoquiz);

        quizTextView = (TextView) findViewById(R.id.quiz_text_view);
        quizTextView.setText(quizManager.startQuiz());

        yesButton = (Button) findViewById(R.id.yesButton);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerAndShowToast(true);
            }
        });

        noButton = (Button) findViewById(R.id.noButton);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerAndShowToast(false);
            }
        });

        previousButton = (Button) findViewById(R.id.previous_button_view);
        previousButton.setEnabled(false);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizTextView.setText(quizManager.previousQuiz());
                enablePreviousAndNextButtonAndDisableAnswerButtons(false);
            }
        });

        nextButton = (Button) findViewById(R.id.next_button_view);
        nextButton.setEnabled(false);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizTextView.setText(quizManager.nextQuiz());
                enablePreviousAndNextButtonAndDisableAnswerButtons(false);
            }
        });
    }

    private void checkAnswerAndShowToast(boolean enteredAnswer) {
        boolean isAnswerCorrect = quizManager.answer(enteredAnswer);
        int stringResourceId = isAnswerCorrect ? R.string.answer_correct : R.string.answer_incorrect;
        Toast.makeText(MyGeoquizActivity.this, stringResourceId, Toast.LENGTH_SHORT).show();
        enablePreviousAndNextButtonAndDisableAnswerButtons(true);
    }

    private void enablePreviousAndNextButtonAndDisableAnswerButtons(boolean enablePreviousAndNextButton) {
        previousButton.setEnabled(enablePreviousAndNextButton);
        nextButton.setEnabled(enablePreviousAndNextButton);
        yesButton.setEnabled(!enablePreviousAndNextButton);
        noButton.setEnabled(!enablePreviousAndNextButton);
    }
}
