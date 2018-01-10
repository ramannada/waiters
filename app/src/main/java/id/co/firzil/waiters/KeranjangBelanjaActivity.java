package id.co.firzil.waiters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KeranjangBelanjaActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view_keranjang)
    RecyclerView recyclerViewKerajang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang_belanja);
        ButterKnife.bind(this);

        KeranjangItemAdapter keranjangItemAdapter = new KeranjangItemAdapter();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);

        recyclerViewKerajang.setLayoutManager(layoutManager);
        recyclerViewKerajang.setAdapter(keranjangItemAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
