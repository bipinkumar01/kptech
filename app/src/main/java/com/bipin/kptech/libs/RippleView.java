package com.bipin.kptech.libs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.bipin.kptech.R;

/**
 * Created by Bipin on 8/30/2016.
 */
public class RippleView extends RelativeLayout {

    //Declaring variables
    private int nWidth;
    private int nHeight;
    private int nFrameRate = 10;
    private int nRippleDuration = 400;
    private int nRippleAlpha = 90;
    private Handler mCanvasHandler;
    private float fRadiusMax = 0;
    private boolean isAnimationRunning = false;
    private int nTimer = 0;
    private int nTimerEmpty = 0;
    private int nDurationEmpty = -1;
    private float x = -1;
    private float y = -1;
    private int nZoomDuration;
    private float nZoomScale;
    private ScaleAnimation mScaleAnimation;
    private Boolean isHasToZoom;
    private Boolean isCentered;
    private Integer nRippleType;
    private Paint mPaint;
    private Bitmap mOriginBitmap;
    private int nRippleColor;
    private int nRipplePadding;
    private GestureDetector mGestureDetector;

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    private OnRippleCompleteListener onCompletionListener;

    public RippleView(Context context) {
        super(context);
    }

    public RippleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RippleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    /**
     * Method that initializes all fields and sets listeners
     *
     * @param context Context used to create this view
     * @param attrs Attribute used to initialize fields
     */
    @SuppressWarnings("deprecation")
    private void init(final Context context, final AttributeSet attrs)
    {
        if (isInEditMode())
            return;

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RippleView);
        nRippleColor = typedArray.getColor(R.styleable.RippleView_rv_color, getResources().getColor(R.color.rippel_dark_color));
        nRippleType = typedArray.getInt(R.styleable.RippleView_rv_type, 0);
        isHasToZoom = typedArray.getBoolean(R.styleable.RippleView_rv_zoom, false);
        isCentered = typedArray.getBoolean(R.styleable.RippleView_rv_centered, false);
        nRippleDuration = typedArray.getInteger(R.styleable.RippleView_rv_rippleDuration, nRippleDuration);
        nFrameRate = typedArray.getInteger(R.styleable.RippleView_rv_frame_rate, nFrameRate);
        nRippleAlpha = typedArray.getInteger(R.styleable.RippleView_rv_alpha, nRippleAlpha);
        nRipplePadding = typedArray.getDimensionPixelSize(R.styleable.RippleView_rv_ripplePadding, 0);
        mCanvasHandler = new Handler();
        nZoomScale = typedArray.getFloat(R.styleable.RippleView_rv_zoomScale, 1.03f);
        nZoomDuration = typedArray.getInt(R.styleable.RippleView_rv_zoomDuration, 200);
        typedArray.recycle();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(nRippleColor);
        mPaint.setAlpha(nRippleAlpha);
        this.setWillNotDraw(false);

        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener()
        {
            @Override
            public void onLongPress(MotionEvent event)
            {
                super.onLongPress(event);
                animateRipple(event);
                sendClickEvent(true);
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        this.setDrawingCacheEnabled(true);
        this.setClickable(true);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (isAnimationRunning) {
            if (nRippleDuration <= nTimer * nFrameRate) {
                isAnimationRunning = false;
                nTimer = 0;
                nDurationEmpty = -1;
                nTimerEmpty = 0;
                canvas.restore();
                invalidate();
                if (onCompletionListener != null) onCompletionListener.onComplete(this);
                return;
            } else
                mCanvasHandler.postDelayed(mRunnable, nFrameRate);

            if (nTimer == 0)
                canvas.save();


            canvas.drawCircle(x, y, (fRadiusMax * (((float) nTimer * nFrameRate) / nRippleDuration)), mPaint);

            mPaint.setColor(Color.parseColor("#ffff4444"));

            if (nRippleType == 1 && mOriginBitmap != null && (((float) nTimer * nFrameRate) / nRippleDuration) > 0.4f) {
                if (nDurationEmpty == -1)
                    nDurationEmpty = nRippleDuration - nTimer * nFrameRate;

                nTimerEmpty++;
                final Bitmap tmpBitmap = getCircleBitmap((int) ((fRadiusMax) * (((float) nTimerEmpty * nFrameRate) / (nDurationEmpty))));
                canvas.drawBitmap(tmpBitmap, 0, 0, mPaint);
                tmpBitmap.recycle();
            }

            mPaint.setColor(nRippleColor);

            if (nRippleType == 1) {
                if ((((float) nTimer * nFrameRate) / nRippleDuration) > 0.6f)
                    mPaint.setAlpha((int) (nRippleAlpha - ((nRippleAlpha) * (((float) nTimerEmpty * nFrameRate) / (nDurationEmpty)))));
                else
                    mPaint.setAlpha(nRippleAlpha);
            }
            else
                mPaint.setAlpha((int) (nRippleAlpha - ((nRippleAlpha) * (((float) nTimer * nFrameRate) / nRippleDuration))));

            nTimer++;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        nWidth = w;
        nHeight = h;

        mScaleAnimation = new ScaleAnimation(1.0f, nZoomScale, 1.0f, nZoomScale, w / 2, h / 2);
        mScaleAnimation.setDuration(nZoomDuration);
        mScaleAnimation.setRepeatMode(Animation.REVERSE);
        mScaleAnimation.setRepeatCount(1);
    }

    /**
     * Launch Ripple animation for the current view with a MotionEvent
     *
     * @param event MotionEvent registered by the Ripple gesture listener
     */
    public void animateRipple(MotionEvent event) {
        createAnimation(event.getX(), event.getY());
    }

    /**
     * Launch Ripple animation for the current view centered at x and y position
     *
     * @param x Horizontal position of the ripple center
     * @param y Vertical position of the ripple center
     */
    public void animateRipple(final float x, final float y) {
        createAnimation(x, y);
    }

    /**
     * Create Ripple animation centered at x, y
     *
     * @param x Horizontal position of the ripple center
     * @param y Vertical position of the ripple center
     */
    private void createAnimation(final float x, final float y) {
        if (this.isEnabled() && !isAnimationRunning) {
            if (isHasToZoom)
                this.startAnimation(mScaleAnimation);

            fRadiusMax = Math.max(nWidth, nHeight);

            if (nRippleType != 2)
                fRadiusMax /= 2;

            fRadiusMax -= nRipplePadding;

            if (isCentered || nRippleType == 1) {
                this.x = getMeasuredWidth() / 2;
                this.y = getMeasuredHeight() / 2;
            } else {
                this.x = x;
                this.y = y;
            }

            isAnimationRunning = true;

            if (nRippleType == 1 && mOriginBitmap == null)
                mOriginBitmap = getDrawingCache(true);

            invalidate();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event)) {
            animateRipple(event);
            sendClickEvent(false);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        this.onTouchEvent(event);
        return super.onInterceptTouchEvent(event);
    }

    /**
     * Send a click event if parent view is a Listview instance
     *
     * @param isLongClick Is the event a long click ?
     */
    private void sendClickEvent(final Boolean isLongClick) {
        if (getParent() instanceof AdapterView) {
            @SuppressWarnings("rawtypes")
            final AdapterView adapterView = (AdapterView) getParent();
            final int position = adapterView.getPositionForView(this);
            final long id = adapterView.getItemIdAtPosition(position);
            if (isLongClick) {
                if (adapterView.getOnItemLongClickListener() != null)
                    adapterView.getOnItemLongClickListener().onItemLongClick(adapterView, this, position, id);
            } else {
                if (adapterView.getOnItemClickListener() != null)
                    adapterView.getOnItemClickListener().onItemClick(adapterView, this, position, id);
            }
        }
    }

    private Bitmap getCircleBitmap(final int radius) {
        final Bitmap output = Bitmap.createBitmap(mOriginBitmap.getWidth(), mOriginBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        final Rect rect = new Rect((int)(x - radius), (int)(y - radius), (int)(x + radius), (int)(y + radius));

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(x, y, radius, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mOriginBitmap, rect, rect, paint);

        return output;
    }

    /**
     * Set Ripple color, default is #FFFFFF
     *
     * @param rippleColor New color resource
     */
    @SuppressWarnings("deprecation")
    @ColorRes
    public void setRippleColor(int rippleColor) {
        this.nRippleColor = getResources().getColor(rippleColor);
    }

    public int getRippleColor() {
        return nRippleColor;
    }

    public RippleType getRippleType()
    {
        return RippleType.values()[nRippleType];
    }

    /**
     * Set Ripple type, default is RippleType.SIMPLE
     *
     * @param rippleType New Ripple type for next animation
     */
    public void setRippleType(final RippleType rippleType)
    {
        this.nRippleType = rippleType.ordinal();
    }

    public Boolean isCentered()
    {
        return isCentered;
    }

    /**
     * Set if ripple animation has to be centered in its parent view or not, default is False
     *
     * @param isCentered
     */
    public void setCentered(final Boolean isCentered)
    {
        this.isCentered = isCentered;
    }

    public int getRipplePadding()
    {
        return nRipplePadding;
    }

    /**
     * Set Ripple padding if you want to avoid some graphic glitch
     *
     * @param ripplePadding New Ripple padding in pixel, default is 0px
     */
    public void setRipplePadding(int ripplePadding)
    {
        this.nRipplePadding = ripplePadding;
    }

    public Boolean isZooming()
    {
        return isHasToZoom;
    }

    /**
     * At the end of Ripple effect, the child views has to zoom
     *
     * @param hasToZoom Do the child views have to zoom ? default is False
     */
    public void setZooming(Boolean hasToZoom)
    {
        this.isHasToZoom = hasToZoom;
    }

    public float getZoomScale()
    {
        return nZoomScale;
    }

    /**
     * Scale of the end animation
     *
     * @param zoomScale Value of scale animation, default is 1.03f
     */
    public void setZoomScale(float zoomScale)
    {
        this.nZoomScale = zoomScale;
    }

    public int getZoomDuration()
    {
        return nZoomDuration;
    }

    /**
     * Duration of the ending animation in ms
     *
     * @param zoomDuration Duration, default is 200ms
     */
    public void setZoomDuration(int zoomDuration)
    {
        this.nZoomDuration = zoomDuration;
    }

    public int getRippleDuration()
    {
        return nRippleDuration;
    }

    /**
     * Duration of the Ripple animation in ms
     *
     * @param rippleDuration Duration, default is 400ms
     */
    public void setRippleDuration(int rippleDuration)
    {
        this.nRippleDuration = rippleDuration;
    }

    public int getFrameRate()
    {
        return nFrameRate;
    }

    /**
     * Set framerate for Ripple animation
     *
     * @param frameRate New framerate value, default is 10
     */
    public void setFrameRate(int frameRate)
    {
        this.nFrameRate = frameRate;
    }

    public int getRippleAlpha()
    {
        return nRippleAlpha;
    }

    /**
     * Set alpha for ripple effect color
     *
     * @param rippleAlpha Alpha value between 0 and 255, default is 90
     */
    public void setRippleAlpha(int rippleAlpha)
    {
        this.nRippleAlpha = rippleAlpha;
    }

    public void setOnRippleCompleteListener(OnRippleCompleteListener listener) {
        this.onCompletionListener = listener;
    }

    /**
     * Defines a callback called at the end of the Ripple effect
     */
    public interface OnRippleCompleteListener {
        void onComplete(RippleView rippleView);
    }

    public enum RippleType {
        SIMPLE(0),
        DOUBLE(1),
        RECTANGLE(2);

        int type;

        RippleType(int type)
        {
            this.type = type;
        }
    }
}
