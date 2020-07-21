package geekbrains.ru.lesson1mvc;

public class Presenter {

    private Model model = new Model();
    private MainView view;

    public void bindView(MainView view){
        this.view = view;
        updateView();
    }

    public void unbindView(MainView view)
    {
        if(view == this.view) this.view = null;
    }

    private void updateView(){
        if(view == null) return;

        for(int i = 0 ; i < model.getElementsCount(); i++){
            view.setButtonText(i, model.getElementValueAtIndex(i));
        }
    }

    public void buttonClick(int index){
        if(model == null) return;
        int newModelValue = model.getElementValueAtIndex(index) + 1;
        model.setElementValueAtIndex(index, newModelValue);
        if(view == null) return;
        view.setButtonText(index, newModelValue);
    }
}
