package com.jamen.chen.bigchat.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import com.jamen.chen.listener.OnTouchAssortListener;

public class AssortView extends Button {

	public AssortView(Context context) {
		super(context);
	}

	public AssortView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AssortView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	// 分类
	private String[] assort = { "↑", "☆", "A", "B", "C", "D", "E", "F", "G",
			"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z", "#" };
	private Paint paint = new Paint();
	// 选择的索引
	private int selectIndex = -1;
	// 字母监听器
	private OnTouchAssortListener onTouch;

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		int width = getWidth();
		int height = getHeight();
		// 字母-->间隔-
		int interval = height / assort.length;// ----->计算出每个字母的高度

		for (int i = 0, length = assort.length; i < length; i++) {
			// 抗锯齿
			paint.setAntiAlias(true);
			// 默认粗体
			paint.setTypeface(Typeface.DEFAULT_BOLD);
			// 黑色
			paint.setColor(Color.BLACK);
			paint.setTextSize(interval/2);
			if(i==selectIndex){
				// 被选择的字母改变颜色和粗体
				paint.setColor(Color.parseColor("#3399ff"));
				paint.setFakeBoldText(true);
				paint.setTextSize(30);
			}
			// 计算字母的X坐标
			float xPos = width / 2 - paint.measureText(assort[i]) / 2;
			// 计算字母的Y坐标
			float yPos = interval * i + interval;
			canvas.drawText(assort[i], xPos, yPos, paint);
			paint.reset();
		}
	}

}
