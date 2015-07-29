package com.jamen.chen.bigchat.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.jamen.chen.bigchat.R;
/**
 * 自定义 底部view..
 * @author jamen
 *
 */
public class BottomView extends View {

	private int color = 0xFF45C01A;
	/* private Bitmap bitmap; */
	private String text = "bigchat";
	private int textSize = (int) TypedValue.applyDimension(
			TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());

	private Canvas canvas;
	private Bitmap bitmapioc;
	private Paint textPaint;
	private Paint iocPaint;
	private float mAlpha;

	private Rect textRect;
	private Rect iocRect;

	public BottomView(Context context) {
		this(context, null);
	}

	public BottomView(Context context, AttributeSet attrs) {
		this(context, null, 0);

	}

	/**
	 * 获取自定义属性的值
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public BottomView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		TypedArray typedarray = context.obtainStyledAttributes(attrs,
				R.styleable.BottomView);
		int n = typedarray.getIndexCount();
		for (int i = 0; i < n; i++) {
			int arrt = typedarray.getIndex(i);
			switch (arrt) {
			case R.styleable.BottomView_icon:
				BitmapDrawable drawable = (BitmapDrawable) typedarray
						.getDrawable(arrt);
				bitmapioc = drawable.getBitmap();
				break;

			case R.styleable.BottomView_color:
				color = typedarray.getColor(arrt, 0xFF45C01A);
				break;
			case R.styleable.BottomView_text:
				text = typedarray.getString(arrt);
				break;
			case R.styleable.BottomView_text_size:
				textSize = typedarray.getDimensionPixelOffset(arrt,
						(int) TypedValue.applyDimension(
								TypedValue.COMPLEX_UNIT_SP, 12, getResources()
										.getDisplayMetrics()));
				break;

			}
		}
		typedarray.recycle();

		textRect = new Rect();
		textPaint = new Paint();
		textPaint.setTextSize(textSize);
		textPaint.getTextBounds(text, 0, text.length(), textRect);
		textPaint.setColor(0Xff555555);

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int iocWidth = Math.min(getMeasuredWidth() - getPaddingLeft()
				- getPaddingRight(), getMeasuredHeight() - getPaddingBottom()
				- getPaddingTop());
		int left = getMeasuredWidth() / 2 - iocWidth / 2;
		int top = getMeasuredHeight() / 2 - (textRect.height() + iocWidth) / 2;
		iocRect = new Rect(left, top, left + iocWidth, top + iocWidth);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(bitmapioc, null, iocRect, null);
		int alpha = (int) Math.ceil(255 * mAlpha);
		// 内存去准备bitmapioc , setAlpha , 纯色 ，xfermode ， 图标
		setupTargetBitmap(alpha);

		// 1、绘制原文本 ；
		drawSourceText(canvas, alpha);
		// 2、绘制变色的文本
		drawTargetText(canvas, alpha);
		canvas.drawBitmap(bitmapioc, 0, 0, null);
	}

	private void drawTargetText(Canvas canvas, int alpha) {

		textPaint.setColor(color);
		textPaint.setAlpha(alpha);
		int x = getMeasuredWidth() / 2 - textRect.width() / 2;
		int y = iocRect.bottom + textRect.height();
		canvas.drawText(text, x, y, textPaint);
	}

	private void drawSourceText(Canvas canvas, int alpha) {

		textPaint.setColor(0xff333333);
		textPaint.setAlpha(255 - alpha);
		int x = getMeasuredWidth() / 2 - textRect.width() / 2;
		int y = iocRect.bottom + textRect.height();
		canvas.drawText(text, x, y, textPaint);
	}

	/**
	 * 在内存中绘制可变色的Icon
	 */
	private void setupTargetBitmap(int alpha) {
		bitmapioc = Bitmap.createBitmap(getMeasuredWidth(),
				getMeasuredHeight(), Config.ARGB_8888);
		canvas = new Canvas(bitmapioc);
		iocPaint = new Paint();
		iocPaint.setColor(color);
		iocPaint.setAntiAlias(true);
		iocPaint.setDither(true);
		iocPaint.setAlpha(alpha);
		canvas.drawRect(iocRect, iocPaint);
		iocPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		iocPaint.setAlpha(255);
		canvas.drawBitmap(bitmapioc, null, iocRect, iocPaint);
	}

	private static final String INSTANCE_STATUS = "instance_status";
	private static final String STATUS_ALPHA = "status_alpha";

	@Override
	protected Parcelable onSaveInstanceState() {
		Bundle bundle = new Bundle();
		bundle.putParcelable(INSTANCE_STATUS, super.onSaveInstanceState());
		bundle.putFloat(STATUS_ALPHA, mAlpha);
		return bundle;
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		if (state instanceof Bundle) {
			Bundle bundle = (Bundle) state;
			mAlpha = bundle.getFloat(STATUS_ALPHA);
			super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));
			return;
		}
		super.onRestoreInstanceState(state);
	}

	//设置alpha  透明度
	public void setIconAlpha(float alpha) {
		this.mAlpha = alpha;
		invalidateView();
	}

	/**
	 * 重绘
	 */
	private void invalidateView() {
		//如果在ui线程就直接从画
		if (Looper.getMainLooper() == Looper.myLooper()) {
			invalidate();
		} else {
			postInvalidate();
		}
	}
}
