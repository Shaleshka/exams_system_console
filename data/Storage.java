package com.company.data;

import java.util.List;

import static com.company.data.Data.fileWorker;

public abstract class Storage {

    private final String filename;

    public Storage(String filename) {
        this.filename = filename;
    }

    public abstract List getAll();

    public void rewrite() {
        fileWorker.writeObject(filename, getAll());
    }
}
