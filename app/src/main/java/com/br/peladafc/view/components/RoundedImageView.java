package com.br.peladafc.view.components;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.br.peladafc.utils.RoundedViewHelper;

/**
 * Created by alan on 30/10/2016.
 */

public class RoundedImageView extends ImageView {

    public RoundedImageView(Context context) {
        super(context);
    }

    public RoundedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundedImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        RoundedViewHelper.drawCircleOnCanvas(canvas, getDrawable(), getWidth());
    }
}
