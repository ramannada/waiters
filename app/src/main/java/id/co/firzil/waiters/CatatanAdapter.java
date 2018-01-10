package id.co.firzil.waiters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ramannada on 1/10/2018.
 */

public class CatatanAdapter extends ArrayAdapter<String> {
    @BindView(R.id.tv_catatan)TextView tvCatatan;

    public CatatanAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listView = convertView;

        if(listView == null) {
            listView = LayoutInflater.from(getContext())
                    .inflate(R.layout.catatan_item, parent, false);
        }

        ButterKnife.bind(this, listView);

        String catatan = getItem(position);

        tvCatatan.setText(catatan);

        return listView;
    }
}
