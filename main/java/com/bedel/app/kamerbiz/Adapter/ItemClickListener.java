package com.bedel.app.kamerbiz.Adapter;

import android.view.View;

/**
 * Created by Bedel Lemotio on 02/02/2018.
 */

public interface ItemClickListener<T> {
    void onClick(T item, View sharedView);
}
