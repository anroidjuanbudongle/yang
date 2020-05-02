package hxz.www.commonbase.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import hxz.www.commonbase.R;

public class GlideUtil {


    public static void load(Context mContext, String url, ImageView mImageView) {
        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_load_err)
                .dontAnimate()
                .into(mImageView);
    }

    public static void loadCircle(Context mContext, String url, ImageView mImageView) {
        Glide.with(mContext)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_load_err)
                .dontAnimate()
                .into(mImageView);
    }

    public static void loadRounded(Context mContext, String url, ImageView mImageView, float value) {
        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_load_err)
                .transform(new RoundedCorners((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, mContext.getResources().getDisplayMetrics())))
                .dontAnimate()
                .into(mImageView);
    }

    public static void loadRoundedV2(Context mContext, String url, ImageView mImageView, float value) {
        Glide.with(mContext)
                .load(url)
                .error(R.drawable.image_load_err)
                .transform(new RoundedCorners((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, mContext.getResources().getDisplayMetrics())))
                .dontAnimate()
                .into(mImageView);
    }

    public static void load(Context mContext, String url, View mView) {
        Glide.with(mContext)
                .asBitmap()
                .load(url)
                .dontAnimate()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        Drawable drawable = new BitmapDrawable(resource);
                        mView.setBackground(drawable);
                    }
                });
    }

    public static void loadRounded(Context mContext, String url, ImageView mImageView, float value, boolean leftTop, boolean rightTop, boolean leftBottom, boolean rightBottom) {
        CornerTransform transformation = new CornerTransform(mContext, value);
        //只是绘制左上角和右上角圆角
        transformation.setExceptCorner(leftTop, rightTop, leftBottom, rightBottom);
        Glide.with(mContext).
                load(url).
                skipMemoryCache(true).
                load(url)
                .skipMemoryCache(true)
                .dontAnimate()
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_load_err).
                diskCacheStrategy(DiskCacheStrategy.NONE).
                transform(transformation).into(mImageView);


    }


}

