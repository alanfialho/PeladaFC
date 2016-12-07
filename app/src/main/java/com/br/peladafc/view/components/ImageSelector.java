package com.br.peladafc.view.components;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import java.io.IOException;
import java.nio.ByteBuffer;

import static android.content.ContentValues.TAG;
import com.br.peladafc.R;
import com.br.peladafc.utils.RoundedViewHelper;

/**
 * Created by alan on 30/10/2016.
 */

public class  ImageSelector extends Fragment{

    private static  int SELECT_IMAGE = 1;
    private RoundedImageButton ribProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ribProfile = new RoundedImageButton(getActivity());
        ribProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);//
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_IMAGE);

            }
        });

        return ribProfile;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == SELECT_IMAGE)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                if (data != null)
                    try {

                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                        ribProfile.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Log.e(TAG, "onActivityResult: ", e);
                    }
            }
        }
    }

    public byte[] getImage(){
       return ribProfile.getImage();
    }

    public boolean wasDefaultImageChanged(){
        return ribProfile.wasDefaultImageChanged();
    }

    private class RoundedImageButton extends ImageButton {

        private boolean defaultImageChanged;

        public RoundedImageButton(Context context) {
            super(context);
            setToDefaultImage();
            setBackgroundColor(Color.TRANSPARENT);
        }

        public RoundedImageButton(Context context, AttributeSet attrs) {
            super(context, attrs);
            setToDefaultImage();
            setBackgroundColor(Color.TRANSPARENT);
        }

        public RoundedImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            setToDefaultImage();
            setBackgroundColor(Color.TRANSPARENT);
        }

        public void setToDefaultImage(){
            this.setImageResource(R.drawable.ic_default_profile);
            this.defaultImageChanged = false;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            RoundedViewHelper.drawCircleOnCanvas(canvas, getDrawable(), getWidth());
        }

        @Override
        public void setImageBitmap(Bitmap bitmap){
            super.setImageBitmap(bitmap);
            this.defaultImageChanged = true;
        }

        public boolean wasDefaultImageChanged(){
            return defaultImageChanged;
        }

        public byte[] getImage(){
            Bitmap bitmap = ((BitmapDrawable)getDrawable()).getBitmap();
            int size = bitmap.getRowBytes() * bitmap.getHeight();
            ByteBuffer byteBuffer = ByteBuffer.allocate(size);
            bitmap.copyPixelsToBuffer(byteBuffer);
            return byteBuffer.array();
        }

    }
}
