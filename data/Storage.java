package com.company.data;

import java.util.List;

import static com.company.data.Data.fileWorker;

public class Storage {

    private final String filename;

    public Storage(String filename) {
        this.filename = filename;
    }

    public List getAll() {
        return null;
    }

    public void rewrite() {
        fileWorker.writeObject(filename, getAll());
    }
}
