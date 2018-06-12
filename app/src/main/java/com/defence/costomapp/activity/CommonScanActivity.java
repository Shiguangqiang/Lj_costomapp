/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.defence.costomapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.model.LatLng;
import com.defence.costomapp.qrcode.utils.Constant;
import com.defence.costomapp.qrcode.zxing.ScanListener;
import com.defence.costomapp.qrcode.zxing.ScanManager;
import com.defence.costomapp.qrcode.zxing.decode.DecodeThread;
import com.defence.costomapp.qrcode.zxing.decode.Utils;
import com.defence.costomapp.R;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.defence.costomapp.utils.httputils.HttpUtils;
import com.defence.costomapp.base.Urls;
import com.google.gson.Gson;
import com.google.zxing.Result;
import com.loopj.android.http.RequestParams;


/**
 * 二维码扫描使用
 *
 * @author yh  2015年4月29日  下午5:49:45
 */
public final class CommonScanActivity extends Activity implements ScanListener, View.OnClickListener {
    static final String TAG = CommonScanActivity.class.getSimpleName();
    final int PHOTOREQUESTCODE = 1111;
    SurfaceView scanPreview = null;
    View scanContainer;
    View scanCropView;
    ImageView scanLine;
    ScanManager scanManager;
    TextView iv_light;
    TextView qrcode_g_gallery;
    TextView qrcode_ic_back;

    //    @Bind(R.id.service_register_rescan)
//    Button rescan;
//    @Bind(R.id.scan_image)
//    ImageView scan_image;
    //    @Bind(R.id.authorize_return)
    TextView authorize_return;
    //
//    @Bind(R.id.common_title_TV_center)
    TextView title;
    //    @Bind(R.id.scan_hint)
    TextView scan_hint;
    //    @Bind(R.id.tv_scan_result)
    TextView tv_scan_result;
    private int scanMode;//扫描模型（条形，二维码，全部）
    private LatLng latLng;
    private String km_scan;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_scan_code);
        latLng = getIntent().getParcelableExtra("latlng");
        km_scan = getIntent().getStringExtra("km_scan");

        if (latLng == null && TextUtils.isEmpty(km_scan)) {
            Toast.makeText(this, "定位失败", Toast.LENGTH_SHORT).show();
            finish();
        }

        authorize_return = findViewById(R.id.authorize_return);
        title = findViewById(R.id.common_title_TV_center);
        scan_hint = findViewById(R.id.scan_hint);
        tv_scan_result = findViewById(R.id.tv_scan_result);
        scanMode = getIntent().getIntExtra(Constant.REQUEST_SCAN_MODE, Constant.REQUEST_SCAN_MODE_ALL_MODE);
        initView();
    }

    void initView() {
        switch (scanMode) {
            case DecodeThread.BARCODE_MODE:
                title.setText(R.string.scan_barcode_title);
                scan_hint.setText(R.string.scan_barcode_hint);
                break;
            case DecodeThread.QRCODE_MODE:
                title.setText(R.string.scan_qrcode_title);
                scan_hint.setText(R.string.scan_qrcode_hint);
                break;
            case DecodeThread.ALL_MODE:
                title.setText(R.string.scan_allcode_title);
                scan_hint.setText(R.string.scan_allcode_hint);
                break;
        }

        scanPreview = findViewById(R.id.capture_preview);
        scanContainer = findViewById(R.id.capture_container);
        scanCropView = findViewById(R.id.capture_crop_view);
        scanLine = findViewById(R.id.capture_scan_line);
//        qrcode_g_gallery = (TextView) findViewById(R.id.qrcode_g_gallery);
//        qrcode_g_gallery.setOnClickListener(this);
//        qrcode_ic_back = (TextView) findViewById(R.id.qrcode_ic_back);
//        qrcode_ic_back.setOnClickListener(this);
        iv_light = findViewById(R.id.iv_light);
        iv_light.setOnClickListener(this);
        // rescan.setOnClickListener(this);
        authorize_return.setOnClickListener(this);
        //构造出扫描管理器
        scanManager = new ScanManager(this, scanPreview, scanContainer, scanCropView, scanLine, scanMode, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        scanManager.onResume();
        //rescan.setVisibility(View.INVISIBLE);
        // scan_image.setVisibility(View.GONE);
        check();
    }

    private void check() {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 222);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        scanManager.onPause();
    }

    /**
     *
     */
    @Override
    public void scanResult(Result rawResult, Bundle bundle) {
        //扫描成功后，扫描器不会再连续扫描，如需连续扫描，调用reScan()方法。
        // scanManager.reScan();
//		Toast.makeText(that, "result="+rawResult.getText(), Toast.LENGTH_LONG).show();

        if (!scanManager.isScanning()) { //如果当前不是在扫描状态
            //设置再次扫描按钮出现
            // rescan.setVisibility(View.VISIBLE);
            //scan_image.setVisibility(View.VISIBLE);
            Bitmap barcode = null;
            byte[] compressedBitmap = bundle.getByteArray(DecodeThread.BARCODE_BITMAP);
            if (compressedBitmap != null) {
                barcode = BitmapFactory.decodeByteArray(compressedBitmap, 0, compressedBitmap.length, null);
                barcode = barcode.copy(Bitmap.Config.ARGB_8888, true);
            }
            //scan_image.setImageBitmap(barcode);
        }
        // rescan.setVisibility(View.VISIBLE);
//        scan_image.setVisibility(View.VISIBLE);
//        tv_scan_result.setVisibility(View.VISIBLE);
//        tv_scan_result.setText("结果：" + rawResult.getText());
        String result = rawResult.getText();
        RequestParams params = new RequestParams();
        if (result.contains("://swz.landgy.com/phone/data/forward-device-xyz-abc.php?device_id=") || result.contains("://swz.bj-defence.com/phone/data/forward-device-xyz-abc.php?device_id=")) {
            Log.d(TAG, "内容正确");
            String[] contents = result.split("=");
            String machineNo = contents[1];

            params.put("machineNumber", machineNo);
            params.put("longitude", latLng.longitude + "");
            params.put("latitude", latLng.latitude + "");
            HttpUtils httpUtils = new HttpUtils(this);
            httpUtils.doPost(Urls.setMachineLocation(), SgqUtils.BUHUO_TYPE, params, new HttpInterface() {
                @Override
                public void onSuccess(Gson gson, Object result, String message) {
                    Toast.makeText(CommonScanActivity.this, "位置更新成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
            //  扫码开门
        } else if (result.startsWith("number08111124=")) {
            String[] contents = result.replace("\n", "").split("=");
            String machineNo = contents[1];
            params.put("machineNumber", machineNo);
            HttpUtils httpUtils = new HttpUtils(this);
            httpUtils.doPost(Urls.kaimen(), SgqUtils.BUHUO_TYPE, params, new HttpInterface() {
                @Override
                public void onSuccess(Gson gson, Object result, String message) {
                    String s = result.toString();
                    finish();
                }
            });


        }
    }

    void startScan() {
//        if (rescan.getVisibility() == View.VISIBLE) {
//            rescan.setVisibility(View.INVISIBLE);
//            scan_image.setVisibility(View.GONE);
//            scanManager.reScan();
//        }
    }

    @Override
    public void scanError(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        //相机扫描出错时
        if (e.getMessage() != null && e.getMessage().startsWith("相机")) {
            scanPreview.setVisibility(View.INVISIBLE);
        }
        finish();
    }

    public void showPictures(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String photo_path;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PHOTOREQUESTCODE:
                    String[] proj = {MediaStore.Images.Media.DATA};
                    Cursor cursor = this.getContentResolver().query(data.getData(), proj, null, null, null);
                    if (cursor.moveToFirst()) {
                        int colum_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        photo_path = cursor.getString(colum_index);
                        if (photo_path == null) {
                            photo_path = Utils.getPath(getApplicationContext(), data.getData());
                        }
                        scanManager.scanningImage(photo_path);
                    }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.qrcode_g_gallery:
//                showPictures(PHOTOREQUESTCODE);
//                break;
            case R.id.iv_light:
                scanManager.switchLight();
                break;
//            case R.id.qrcode_ic_back:
//                finish();
//                break;
//            case R.id.service_register_rescan://再次开启扫描
//                startScan();
//                break;
            case R.id.authorize_return:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            //就像onActivityResult一样这个地方就是判断你是从哪来的。
            case 222:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "很遗憾你把相机权限禁用了。请务必开启相机权限享受我们提供的服务吧。", Toast.LENGTH_SHORT)
                            .show();
                    break;
                }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}