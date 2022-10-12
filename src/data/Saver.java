package data;

import model.Manager;
import model.articles.Article;

import java.io.IOException;
import java.util.List;

public interface Saver {
    void save(Manager manager) throws IOException;
}
