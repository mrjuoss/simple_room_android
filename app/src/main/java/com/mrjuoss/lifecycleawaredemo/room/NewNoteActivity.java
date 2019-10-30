package com.mrjuoss.lifecycleawaredemo.room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mrjuoss.lifecycleawaredemo.R;

public class NewNoteActivity extends AppCompatActivity {

    public static final String NEW_NOTE = "new_note";

    private EditText etNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        etNewNote = findViewById(R.id.et_note);

        Button btnSaveNote = findViewById(R.id.btn_add_note);

        btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();

                if (TextUtils.isEmpty(etNewNote.getText())) {
                    setResult(RESULT_CANCELED, resultIntent);
                } else {
                    String note = etNewNote.getText().toString().trim();
                    resultIntent.putExtra(NEW_NOTE, note);
                    setResult(RESULT_OK, resultIntent);
                }
                finish();
            }
        });
    }
}
