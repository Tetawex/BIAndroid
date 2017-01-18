package net.styleru.biandroid.view.activity;

        import android.annotation.TargetApi;
        import android.app.DownloadManager;
        import android.content.Context;
        import android.graphics.Bitmap;
        import android.net.Uri;
        import android.os.Build;
        import android.os.Bundle;
        import android.os.Environment;
        import android.support.v4.view.PagerAdapter;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v4.view.ViewPager;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.FrameLayout;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.bumptech.glide.Glide;
        import com.bumptech.glide.request.animation.GlideAnimation;
        import com.bumptech.glide.request.target.SimpleTarget;
        import com.davemorrissey.labs.subscaleview.ImageSource;
        import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

        import junit.framework.Assert;

        import net.styleru.biandroid.R;

        import java.io.File;
        import java.util.ArrayList;
        import java.util.Random;

        import butterknife.ButterKnife;
        import butterknife.InjectView;
//Этот код был нагло скопирован с одного сайта в интернете. No jokes.
public class GalleryActivity extends AppCompatActivity {
    public static final String TAG = "GalleryActivity";
    public static final String EXTRA_NAME = "images";

    private ArrayList<String> images;
    private GalleryPagerAdapter adapter;

    private TextView counter=null;

    @InjectView(R.id.pager) ViewPager pager;
    @InjectView(R.id.thumbnails) LinearLayout thumbnails;
    @InjectView(R.id.btn_close) ImageButton closeButton;
    @InjectView(R.id.btn_save) ImageButton saveButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.inject(this);

        images = (ArrayList<String>) getIntent().getSerializableExtra(EXTRA_NAME);
        Assert.assertNotNull(images);

        adapter = new GalleryPagerAdapter(this);

        counter=(TextView) findViewById(R.id.counter);

        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(6); // how many images to load into memory
        counter.setText(1+" "+getString(R.string.of)+" "+String.valueOf(pager.getAdapter().getCount()));
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position)
            {
                counter.setText(String.valueOf(position+1) + " "+getString(R.string.of)+" " + String.valueOf(pager.getAdapter().getCount()));
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d(TAG, "Close clicked");
                finish();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v)
            {
                downloadFile(images.get(pager.getCurrentItem()));
            }
        });
    }

    public void downloadFile(String uRl)
    {
        File direct = new File(Environment.getExternalStorageDirectory()
                + "/BIAndroid");

        if (!direct.exists()) {
            direct.mkdirs();
        }

        DownloadManager mgr = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        Uri downloadUri = Uri.parse(uRl);
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);

        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false).setTitle("")
                .setDescription("")
                .setDestinationInExternalPublicDir("/BIAndroid", "BI"+((new Random()).nextInt(900000)+99999)+".png");
        Toast.makeText(this, getString(R.string.image_saved), Toast.LENGTH_SHORT).show();

        mgr.enqueue(request);

    }

    class GalleryPagerAdapter extends PagerAdapter {

        Context _context;
        LayoutInflater _inflater;

        public GalleryPagerAdapter(Context context) {
            _context = context;
            _inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View itemView = _inflater.inflate(R.layout.pager_gallery_item, container, false);
            container.addView(itemView);

            // Get the border size to show around each image
            int borderSize = thumbnails.getPaddingTop();

            // Get the size of the actual thumbnail image
            int thumbnailSize = ((FrameLayout.LayoutParams)
                    pager.getLayoutParams()).bottomMargin - (borderSize*2);

            // Set the thumbnail layout parameters. Adjust as required
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(thumbnailSize, thumbnailSize);
            params.setMargins(0, 0, borderSize, 0);

            // You could also set like so to remove borders
            //ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
            //        ViewGroup.LayoutParams.WRAP_CONTENT,
            //        ViewGroup.LayoutParams.WRAP_CONTENT);

            final ImageView thumbView = new ImageView(_context);
            thumbView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            thumbView.setLayoutParams(params);
            thumbView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.d(TAG, "Thumbnail clicked");

                    // Set the pager position when thumbnail clicked
                    pager.setCurrentItem(position);
                    counter.setText(String.valueOf(position+1)+" "+getString(R.string.of)+" "+String.valueOf(getCount()));

                }
            });
            thumbnails.addView(thumbView);

            final SubsamplingScaleImageView imageView =
                    (SubsamplingScaleImageView) itemView.findViewById(R.id.image);

            // Asynchronously load the image and set the thumbnail and pager view
            Glide.with(_context)
                    .load(images.get(position))
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                            imageView.setImage(ImageSource.bitmap(bitmap));
                            thumbView.setImageBitmap(bitmap);
                        }
                    });

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }
}
