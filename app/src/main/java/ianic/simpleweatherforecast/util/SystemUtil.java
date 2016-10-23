package ianic.simpleweatherforecast.util;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.net.InetAddress;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import ianic.simpleweatherforecast.R;

/**
 * A utility class which offers methods which help with user interaction; e.g. dialog boxes.
 *
 * @author Ivan Anić
 * @version 1.0
 */
public class SystemUtil {

    public static boolean isInternetAvailable() {

        RunnableFuture<Boolean> f = new FutureTask<>(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
                try {
                    InetAddress ipAddr = InetAddress.getByName("google.com");
                    return !ipAddr.equals("");

                } catch (Exception e) {
                    return false;
                }
            }
        });
        new Thread(f).start();

        try {
            return f.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return false;
        } catch (ExecutionException e) {
            return false;
        } catch (TimeoutException e) {
            return false;
        }

    }

    public static void createDialogExportFinished(final Context context, int alert, int message,
                                                  boolean finish) {
        DialogInterface.OnClickListener onClickListener = null;
        if (finish)
            onClickListener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    ((AppCompatActivity) context).finish();
                    dialog.cancel();
                }
            };
        new AlertDialog.Builder(context)
                .setTitle(context.getResources().getString(alert))
                .setMessage(context.getResources().getString(message))
                .setCancelable(false)
                .setPositiveButton(context.getResources().getString(R.string.ok), onClickListener)
                .create().show();
    }
}
