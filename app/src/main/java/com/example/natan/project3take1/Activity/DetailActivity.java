package com.example.natan.project3take1.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.natan.project3take1.Adapters.StepsAdapter;
import com.example.natan.project3take1.Pojo.Recepie;
import com.example.natan.project3take1.Pojo.Steps;
import com.example.natan.project3take1.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

import butterknife.ButterKnife;

import static com.example.natan.project3take1.Activity.StepsDetailActivity.stepslist;

public class DetailActivity extends AppCompatActivity {

    int index;
    protected Steps steps;
    private ArrayList<Steps> stepList;
    @BindView(R.id.next)
    Button btn_next;
    @BindView(R.id.prev)
    Button btn_prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        stepList = getIntent().getParcelableArrayListExtra("stepsi");
        int index = getIntent().getExtras().getInt("position");
        Steps steps = stepList.get(index);


        setUpView(steps);


    }

    private void setUpView(Steps step) {

        Toast.makeText(this, step.getShortDescription(), Toast.LENGTH_SHORT).show();


    }

    @OnClick(R.id.next)
    void doNextStep() {
        if (index == stepList.size() - 1) {
            Toast.makeText(DetailActivity.this, String.valueOf(index), Toast.LENGTH_SHORT).show();
            if (btn_next.isEnabled()) {
                btn_next.setEnabled(false);
            }
        } else {
            index++;

            if (!btn_prev.isEnabled()) btn_prev.setEnabled(true);
                Log.i("tagu", String.valueOf(index));
                steps = stepList.get(index);
                //releasePlayer();
                setUpView(steps);

        }
    }


    @OnClick(R.id.prev)
    void doPreviousStep() {
        if (index == 0) {
            Toast.makeText(DetailActivity.this, String.valueOf(index), Toast.LENGTH_SHORT).show();
            if (btn_prev.isEnabled()) btn_prev.setEnabled(false);
        } else {
            index--;

            if (!btn_next.isEnabled()) btn_next.setEnabled(true);
            Log.i("tagu", String.valueOf(index));

            steps = stepList.get(index);
            //releasePlayer();
            setUpView(steps);
        }
    }


}








