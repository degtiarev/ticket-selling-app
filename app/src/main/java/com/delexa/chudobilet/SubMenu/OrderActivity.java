package com.delexa.chudobilet.SubMenu;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.delexa.chudobilet.MainClasses.TicketOrder;
import com.delexa.chudobilet.Adapters.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.text.Format;
import java.text.SimpleDateFormat;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ((AppCompatActivity) this).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int id = intent.getIntExtra("_id", Integer.MAX_VALUE);

        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(this);
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        TicketOrder ticketOrder = ChudobiletDatabaseHelper.getTicketOrder(db, id);


        TextView name = (TextView) findViewById(R.id.textViewOrderEventName);
        TextView address = (TextView) findViewById(R.id.textViewOrderEstablishmentAddress);
        TextView dateTime = (TextView) findViewById(R.id.textViewOrderEventDateTime);
        TextView eventSeat = (TextView) findViewById(R.id.textViewOrderEventSeat);
        TextView price = (TextView) findViewById(R.id.textViewOrderEventPrice);
        TextView service = (TextView) findViewById(R.id.textViewOrderService);
        TextView purchaseDateTime = (TextView) findViewById(R.id.textViewOrderPurchaseDateTime);
        TextView series = (TextView) findViewById(R.id.textViewOrderTicketSeries);
        TextView number = (TextView) findViewById(R.id.textViewOrderTicketNumber);

        Format formatter1 = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        String s1 = formatter1.format(ticketOrder.getSeat().getTimeDate());
        Format formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        String s2 = formatter2.format(ticketOrder.getPurchaseDate());


        name.setText(ticketOrder.getSeat().getEvent().getName());
        address.setText(ticketOrder.getSeat().getEvent().getEstablishment().getAddress());
        dateTime.setText(s1);
        eventSeat.setText(ticketOrder.getSeat().getName());
        price.setText(Double.toString(ticketOrder.getSeat().getPrice()) + " руб");
        service.setText(Double.toString(ticketOrder.getSeat().getServicePrice()) + " руб");
        purchaseDateTime.setText(s2);
        series.setText(ticketOrder.getSeries());
        number.setText(ticketOrder.getNumber());

        setTitle(ticketOrder.getSeat().getEvent().getName());
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(ticketOrder.getCode(), BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            ((ImageView) findViewById(R.id.imageViewOrderQr)).setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
