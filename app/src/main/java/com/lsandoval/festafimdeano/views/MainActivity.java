package com.lsandoval.festafimdeano.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lsandoval.festafimdeano.R;
import com.lsandoval.festafimdeano.constant.FimDeAnoConstants;
import com.lsandoval.festafimdeano.data.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;
    private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("EEE, MMM d, yyyy");
    private String PRESENCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.txtToday = findViewById(R.id.textToday);
        this.mViewHolder.txtDaysLeft = findViewById(R.id.daysLeft);
        this.mViewHolder.btnConfirm = findViewById(R.id.btnConfirm);

        this.mViewHolder.btnConfirm.setOnClickListener(this);

        this.mViewHolder.txtToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));

        this.mViewHolder.txtDaysLeft.setText(String.format("%s %s", String.valueOf(this.getDaysLeft()), getString(R.string.days_left)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPresence();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(FimDeAnoConstants.PRESENCE_KEY, this.PRESENCE);

        startActivity(intent);
    }

    private void verifyPresence() {
        this.PRESENCE = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE_KEY);
        switch (this.PRESENCE) {
            case FimDeAnoConstants.CONFIRMATION_YES: {
                this.mViewHolder.btnConfirm.setText(getString(R.string.yes));
                break;
            }
            case FimDeAnoConstants.CONFIRMATION_NO: {
                this.mViewHolder.btnConfirm.setText(getString(R.string.no));
                break;
            }
            default: {
                this.mViewHolder.btnConfirm.setText(getString(R.string.notAnswered));
                break;
            }
        }
    }

    private int getDaysLeft() {
        return Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_YEAR) - Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
    }

    private static class ViewHolder {
        TextView txtToday;
        TextView txtDaysLeft;
        Button btnConfirm;
    }
}
