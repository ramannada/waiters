package id.co.firzil.waiters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ramannada on 1/10/2018.
 */

public class KeranjangItemAdapter extends RecyclerView.Adapter<KeranjangItemAdapter.ItemViewHolder> {

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.keranjang_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        List<String> catatan = new ArrayList<>();
        catatan.add("tambah keju");
        catatan.add("ekstra pedas");
        catatan.add("extra large");

        CatatanAdapter catatanAdapter = new CatatanAdapter(holder.listViewCatatanItem.getContext(), catatan);
        setListViewHeightBasedOnChildren(holder.listViewCatatanItem);

        holder.listViewCatatanItem.setAdapter(catatanAdapter);
        Glide.with(holder.itemView)
                .load("http://img.taste.com.au/UKBqpWrm/w643-h428-cfill-q90/taste/2016/11/basil-and-lemon-risotto-12862-1.jpeg")
                .apply(new RequestOptions().fitCenter().centerInside())
                .into(holder.ivPoster);
        holder.tvName.setText("Lemon Basil Risotto with Buratta");
        holder.tvPrice.setText("Rp 35.000,-");
        holder.tvQuantity.setText("2");

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    /**** Method for Setting the Height of the ListView dynamically.
     **** Hack to fix the issue of not showing all the items of the ListView
     **** when placed inside a ScrollView  ****/
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_item)
        ImageView ivPoster;
        @BindView(R.id.tv_item_name)
        TextView tvName;
        @BindView(R.id.tv_item_price)
        TextView tvPrice;
        @BindView(R.id.tv_item_quantity)
        TextView tvQuantity;
        @BindView(R.id.btn_decrement_quantity)
        Button btnDecrement;
        @BindView(R.id.btn_increment_quantity)
        Button btnIncrement;
        @BindView(R.id.list_view_catatan_item)
        ListView listViewCatatanItem;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        protected void bindView() {

        }
    }
}
