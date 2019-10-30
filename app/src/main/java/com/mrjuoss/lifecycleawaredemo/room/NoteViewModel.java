package com.mrjuoss.lifecycleawaredemo.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class NoteViewModel extends AndroidViewModel {

    private NoteDao noteDao;
    private NoteDatabase noteDatabase;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        noteDatabase = NoteDatabase.getDatabase(application);
        noteDao = noteDatabase.noteDao();
    }

    public void insert(Note note) {
        new InsertAsyncTask(noteDao).execute(note);
    }

    private class InsertAsyncTask extends AsyncTask<Note, Void, Void> {

        NoteDao mNoteDao;

        public InsertAsyncTask(NoteDao noteDao) {
            this.mNoteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.insert(notes[0]);
            return null;
        }
    }
}
