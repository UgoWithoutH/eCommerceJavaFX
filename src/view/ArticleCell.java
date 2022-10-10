package view;

import javafx.beans.binding.Bindings;
import javafx.scene.control.ListCell;
import viewmodel.ArticleVM;

public class ArticleCell extends ListCell<ArticleVM> {
    @Override
    protected void updateItem(ArticleVM item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty){
            textProperty().bind(item.nomProperty());
        }
        else{
            textProperty().unbind();
            setText("");
        }
    }
}
