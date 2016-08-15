package com.mysticwind.android.bignerdranch.training.mygeoquiz.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mysticwind.android.bignerdranch.training.mygeoquiz.R;

public class MyCheatActivity extends AppCompatActivity {

    private static final String IS_ANSWER_TRUE_EXTRA_KEY = "isAnswerTrue";
    private static final String IS_ANSWER_SHOWN_EXTRA_KEY = "isAnswerShown";

    private boolean isAnswerTrue;
    private TextView answerTextView;
    private Button showAnswerButton;
    private TextView apiLevelTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cheat);

        isAnswerTrue = getIntent().getBooleanExtra(IS_ANSWER_TRUE_EXTRA_KEY, false);

        answerTextView = (TextView) findViewById(R.id.answer_text_view);

        showAnswerButton = (Button) findViewById(R.id.show_answer_button);
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int answerResourceId = isAnswerTrue ? R.string.yes : R.string.no;
                answerTextView.setText(answerResourceId);
                setAnswerShown();
            }
        });

        apiLevelTextView = (TextView) findViewById(R.id.api_level_text_view);
        apiLevelTextView.setText(String.format(getString(R.string.api_level), Build.VERSION.SDK_INT));
    }

    private void setAnswerShown() {
        Intent data = new Intent();
        data.putExtra(IS_ANSWER_SHOWN_EXTRA_KEY, true);
        setResult(RESULT_OK, data);
    }

    public static Intent newLaunchCheatActivityIntent(Context context, boolean answerOfQuiz) {
        Intent intent = new Intent(context, MyCheatActivity.class);
        intent.putExtra(MyCheatActivity.IS_ANSWER_TRUE_EXTRA_KEY, answerOfQuiz);
        return intent;
    }

    public static boolean wasAnswerShown(Intent data) {
        return data.getBooleanExtra(IS_ANSWER_SHOWN_EXTRA_KEY, false);
    }
}
