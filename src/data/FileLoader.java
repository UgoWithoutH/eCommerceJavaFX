package data;

import model.Manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class FileLoader implements Loader{
    @Override
    public Manager load() throws IOException, ClassNotFoundException {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("fichier.bin"))){
            return (Manager) ois.readObject();
        }
    }
}
