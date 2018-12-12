package com.bedel.app.kamerbiz.Tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;



public class DialogTools {
    public static interface OnMenuItemClickListener {
        void OnMenuItemClick(int position);
    }

    public static ProgressDialog progressDialog;

    public static void showAlert(final Activity activity, final String message, final Runnable runnable) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                        .setTitle("Message")
                        .setMessage(message)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(runnable != null) {
                                    runnable.run();
                                }
                            }
                        });
                builder.show();
            }
        });
    }
    public static void showConfirm(final Activity activity, final String message, final Runnable runnable, final Runnable dismissRunnable) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage(message)
                            .setPositiveButton("Oui",  new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    runnable.run();
                                }
                            })
                            .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    dismissRunnable.run();
                                    dialog.cancel();
                                }
                            })
                            .show();
            }
        });
    }

    public static void showConfirm(final Activity activity, final String message, final Runnable runnable) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage(message)
                        .setPositiveButton("Oui",  new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                runnable.run();
                            }
                        })
                        .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        .show();
            }
        });
    }

    public static void showMenu(final Activity activity, final String title, final String[] menu, final OnMenuItemClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle(title)
                .setItems(menu, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.OnMenuItemClick(which);
                    }
                });
        builder.show();
    }

    public static void showProgress(final Activity activity, final String message) {
        progressDialog = ProgressDialog.show(activity, "", message, true);
        progressDialog.show();
    }

    public static void hideProgress() {
        if(progressDialog!=null) {
            progressDialog.hide();
        }
    }
}
