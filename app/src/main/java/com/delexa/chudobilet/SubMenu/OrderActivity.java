package com.delexa.chudobilet.SubMenu;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.delexa.chudobilet.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

//        QRCodeWriter writer = new QRCodeWriter();
//        try {
//            BitMatrix bitMatrix = writer.encode("adsadsadsad", BarcodeFormat.QR_CODE, 512, 512);
//            int width = bitMatrix.getWidth();
//            int height = bitMatrix.getHeight();
//            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
//            for (int x = 0; x < width; x++) {
//                for (int y = 0; y < height; y++) {
//                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
//                }
//            }
//            ((ImageView) findViewById(R.id.img_result_qr)).setImageBitmap(bmp);
//
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
    }
}
