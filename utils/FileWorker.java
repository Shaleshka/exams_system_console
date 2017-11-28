package com.company.utils;

import java.io.*;

public class FileWorker {

    public void writeObject(String filename, Object object) {
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream serial = new ObjectOutputStream(file);
            serial.writeObject(object);
            serial.flush();
            serial.close();
        } catch (IOException ex) {
            System.out.println("Ошибка при работе с файловой системой");
            ex.printStackTrace();
        }
    }

    public Object readObject(String filename) throws Exception {
        FileInputStream file = new FileInputStream(filename);
        ObjectInputStream serial = new ObjectInputStream(file);
        return serial.readObject();
    }

}
