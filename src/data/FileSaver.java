package data;

import model.Manager;
import model.articles.Article;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class FileSaver implements Saver{
    @Override
    public void save(Manager manager) throws IOException{
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("fichier.bin"))){
            oos.writeObject(manager);
        }
    }
}
