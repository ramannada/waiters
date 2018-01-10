package id.co.firzil.waiters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shuhart.bubblepagerindicator.BubblePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMenuActivity extends AppCompatActivity {
    @BindView(R.id.view_pager_image) ViewPager viewPager;
    @BindView(R.id.indicator) BubblePageIndicator indicator;
    TextView tvNotifCartCount;
    private int cartValSize = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);
        ButterKnife.bind(this);


        List<String> imagesSrc = new ArrayList<>();
        imagesSrc.add("http://img.taste.com.au/UKBqpWrm/w643-h428-cfill-q90/taste/2016/11/basil-and-lemon-risotto-12862-1.jpeg");

        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(imagesSrc);

        viewPager.setAdapter(imagePagerAdapter);

        indicator.setViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);

        final MenuItem cart = menu.findItem(R.id.menu_cart);

        View actionView = cart.getActionView();

        if (actionView == null) {
            Log.d("cart view", "kosong");
            return true;
        }

        tvNotifCartCount = actionView.findViewById(R.id.tv_notif_cart_count);

        setupBadge();

        return true;
    }


    private void setupBadge() {
        if (cartValSize > 0) {
            tvNotifCartCount.setText(String.valueOf(Math.min(cartValSize, 99)));
            if (tvNotifCartCount.getVisibility() == View.GONE) {
                tvNotifCartCount.setVisibility(View.VISIBLE);
            }
        } else {
            if (tvNotifCartCount.getVisibility() == View.VISIBLE) {
                tvNotifCartCount.setVisibility(View.GONE);
            }
        }
    }

    private class ImagePagerAdapter extends PagerAdapter {
        List<String> images;


        public ImagePagerAdapter(List<String> images) {
            this.images = images;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            Context context = DetailMenuActivity.this;
            ImageView imageView = new ImageView(context);

            Glide.with(context)
                    .load(images.get(position))
                    .apply(new RequestOptions().centerInside().fitCenter())
                    .into(imageView);

            container.addView(imageView, 0);

            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((ImageView) object);
        }
    }

}
