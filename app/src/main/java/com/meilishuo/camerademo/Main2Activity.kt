package com.meilishuo.camerademo

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.huantansheng.cameralibrary.CameraInterface
import com.huantansheng.cameralibrary.util.FileUtil
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {
    private var applicationName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        try {
            applicationName = getString(com.huantansheng.easyphotos.R.string.app_name)
            val packageManager = applicationContext.packageManager
            val applicationInfo = packageManager.getApplicationInfo(packageName, 0)
            applicationName = packageManager.getApplicationLabel(applicationInfo) as String
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        CameraInterface.getInstance().takePicture { bitmap, isVertical ->
            if (bitmap != null) {
                image_photo.setImageBitmap(bitmap)
            }
        }
        tv_done.setOnClickListener {
            val path = FileUtil.saveBitmap(applicationName, image_photo.croppedImage)
        }
    }
}
