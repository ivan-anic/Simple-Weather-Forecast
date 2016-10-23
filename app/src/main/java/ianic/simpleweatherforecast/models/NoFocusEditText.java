package ianic.simpleweatherforecast.models;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;

/**
 * A model which extends the {@linkplain AppCompatEditText} in order to add a single function,
 * {@linkplain #onKeyPreIme}
 *
 * @author Ivan Anić
 * @version 1.0
 */
public class NoFocusEditText extends AppCompatEditText {

    public NoFocusEditText(Context context) {
        super(context);
    }

    public NoFocusEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /*
        a method which clears the focus from the text field in order to preserve the
        screen panning feature in {@LinkPlain MainActivity}
     */
    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            clearFocus();
        }
        return super.onKeyPreIme(keyCode, event);
    }
}
