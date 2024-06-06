package com.example.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 1;

    private static final int NOTIFICATION_REQUEST_CODE = 10;
    Button sendNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        sendNotice = findViewById(R.id.btn_sendNoti);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission("android.permission.POST_NOTIFICATIONS") != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{"android.permission.POST_NOTIFICATIONS"}, NOTIFICATION_REQUEST_CODE);
            }
        }

        sendNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });
    }

    private void sendNotification() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        Notification notification = new NotificationCompat.Builder(this, NotificationConfig.CHANNEL_ID)
                .setContentTitle("Title push notification")
                .setContentText("Message push notification")
                .setSmallIcon(R.drawable.img)
                .setLargeIcon(bitmap)
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(getNotificationId(), notification);

//        if (notificationManager != null) {
//            notificationManager.notify(getNotificationId(), notification);
//        }
    }

    private int getNotificationId() {
        return (int) new Date().getTime();
    }
}