package com.mysticwind.android.bignerdranch.training.mygeoquiz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mysticwind.android.bignerdranch.training.mygeoquiz.R;
import com.mysticwind.android.bignerdranch.training.mygeoquiz.manager.QuizManager;
import com.mysticwind.android.bignerdranch.training.mygeoquiz.manager.QuizManagerImpl;
import com.mysticwind.android.bignerdranch.training.mygeoquiz.model.Question;
import com.mysticwind.android.bignerdranch.training.mygeoquiz.model.QuizState;

public class MyGeoquizActivity extends AppCompatActivity {

    private static final String TAG = MyGeoquizActivity.class.getSimpleName();
    private static final String PERSISTED_QUIZ_STATE_KEY = "quizState";

    private TextView quizTextView;
    private Button yesButton;
    private Button noButton;
    private ImageButton previousButton;
    private ImageButton nextButton;
    private Button cheatButton;

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

        debug("onCreate()");

        setContentView(R.layout.activity_my_geoquiz);

        quizTextView = (TextView) findViewById(R.id.quiz_text_view);

        int quizTextResourceId;
        if (savedInstanceState == null) {
            quizTextResourceId = quizManager.startQuiz();
        } else {
            QuizState quizState = (QuizState) savedInstanceState.getSerializable(PERSISTED_QUIZ_STATE_KEY);
            quizTextResourceId = quizManager.continueQuiz(quizState);
        }
        quizTextView.setText(quizTextResourceId);

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

        previousButton = (ImageButton) findViewById(R.id.previous_button_view);
        previousButton.setEnabled(false);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizTextView.setText(quizManager.previousQuiz());
                enablePreviousAndNextButtonAndDisableAnswerButtons(false);
            }
        });

        nextButton = (ImageButton) findViewById(R.id.next_button_view);
        nextButton.setEnabled(false);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizTextView.setText(quizManager.nextQuiz());
                enablePreviousAndNextButtonAndDisableAnswerButtons(false);
            }
        });

        cheatButton = (Button) findViewById(R.id.cheatButton);
        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO we are leaking the answer even if the user did not choose the see the answer
                Intent intent = MyCheatActivity.newLaunchCheatActivityIntent(MyGeoquizActivity.this, quizManager.peekAnswer());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        debug("onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        debug("onDestroy()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        QuizState quizState = quizManager.getQuizState();
        outState.putSerializable(PERSISTED_QUIZ_STATE_KEY, quizState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        debug("onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        debug("onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        debug("onStart()");
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

    private void debug(String message) {
        Log.d(TAG, message);
    }
}
