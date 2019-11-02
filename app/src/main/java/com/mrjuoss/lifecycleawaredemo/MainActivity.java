package com.mrjuoss.lifecycleawaredemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mrjuoss.lifecycleawaredemo.room.NewNoteActivity;
import com.mrjuoss.lifecycleawaredemo.room.Note;
import com.mrjuoss.lifecycleawaredemo.room.NoteAdapter;
import com.mrjuoss.lifecycleawaredemo.room.NoteViewModel;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = this.getClass().getSimpleName();
    private String mRandomNumber;

    private static final int NEW_NOTE_ACTIVITY_REQUEST_CODE = 1;
    private NoteViewModel noteViewModel;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "Owner ON_CREATE");
        getLifecycle().addObserver(new MainActivityObserver());

        MainActivityViewModel model = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        LiveData<String> number = model.getNumber();
//        final TextView txtRandomNumber = findViewById(R.id.txt_random_number);
//        String number = getRandomNumber();
//        number.observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                txtRandomNumber.setText(s);
//            }
//        });

//        Button btnFetchData = findViewById(R.id.btn_fetch_data);
//        btnFetchData.setOnClickListener(this);

        FloatingActionButton fab = findViewById(R.id.fab_add);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);
                startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE);
            }
        });

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        initRecyclerView();

        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                noteAdapter.setNotes(notes);
            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rv_note);
        noteAdapter = new NoteAdapter(this);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            // Insert Data
            final String note_id = UUID.randomUUID().toString();
            Note note = new Note(note_id, data.getStringExtra(NewNoteActivity.NEW_NOTE));
            noteViewModel.insert(note);

            Toast.makeText(this.getApplicationContext(), R.string.saved, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.getApplicationContext(), R.string.not_saved, Toast.LENGTH_SHORT).show();
        }
    }

    private String getRandomNumber() {
        Log.i(TAG, "Get Random Number");
        if (mRandomNumber == null) {
            createRandomNumber();
        }
        return mRandomNumber;
    }

    private void createRandomNumber() {
        Log.i(TAG, "Create New Random Number");
        Random random = new Random();
        mRandomNumber = "Number " + (random.nextInt(10 - 1) + 1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Owner ON_START");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Owner ON_STOP");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Owner ON_DESTROY");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Owner ON_PAUSE");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Owner ON_RESUME");
    }

    @Override
    public void onClick(View v) {
        MainActivityViewModel model = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        model.createRandomNumber();
    }
}
