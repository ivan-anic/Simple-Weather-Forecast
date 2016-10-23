package ianic.simpleweatherforecast.util;

import android.text.TextUtils;
import android.widget.EditText;

/**
 * A utility class which which offers methods to control or check the various component's statuses.
 *
 * @author Ivan Anić
 * @version 1.0
 */
public class ComponentUtil {

    /*
        Checks if the given EditText is empty
     */
    public static boolean checkTextField(EditText e) {
        if (TextUtils.isEmpty(e.getText().toString())) {
            e.setError("Field empty!");
            return false;
        }
        return true;
    }
}
