package geekbrains.ru.lesson1mvc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener{
    private static final String TAG = MainActivity.class.getName();
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            presenter = new Presenter();
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }
        init();
    }
    private void init(){
        findViewById(R.id.btnCounter1).setTag(0);
        findViewById(R.id.btnCounter2).setTag(1);
        findViewById(R.id.btnCounter3).setTag(2);
    }
    @Override
    protected void onResume() {
        super.onResume();
        presenter.bindView(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unbindView(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PresenterManager.getInstance().savePresenter(presenter, outState);
    }

    @Override
    public void onClick(View v) {
        presenter.buttonClick((int)v.getTag());
    }

    @Override
    public void setButtonText(int index, int value) {
        Button button = getWindow().getDecorView().getRootView().findViewWithTag(index);
        if(button == null)
        {
            Log.e(TAG, "missing button with tag " + index);
            return;
        }
        button.setText("Количество = " + value);
    }
}

