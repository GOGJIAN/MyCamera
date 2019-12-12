package com.huantansheng.easyphotos.ui

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.huantansheng.cameralibrary.util.FileUtil
import com.huantansheng.easyphotos.R
import kotlinx.android.synthetic.main.activity_crop.*

class CropActivity : AppCompatActivity() {
    private var applicationName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop)
        val uri = intent.getStringExtra(PHOTO_URI)
        if(!TextUtils.isEmpty(uri)){
            img_crop_photo.setImageURI(Uri.parse(uri))
        }
        try {
            applicationName = getString(R.string.app_name)
            val packageManager = applicationContext.packageManager
            val applicationInfo = packageManager.getApplicationInfo(packageName, 0)
            applicationName = packageManager.getApplicationLabel(applicationInfo) as String
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        crop_done.setOnClickListener {
            val bitmap = img_crop_photo.croppedImage
            val path = FileUtil.saveBitmap(applicationName, bitmap)
            val intent = Intent()
            intent.putExtra(PHOTO_PATH, path)
            setResult(REQUEST_CODE_CROP, intent)
            finish()
        }
        crop_cancel.setOnClickListener {
            finish()
        }
    }

    companion object{
        const val PHOTO_URI = "photo_uri"
        const val PHOTO_PATH = "photo_path"
        const val REQUEST_CODE_CROP = 8
        fun open(context: Context, imageUri:String){
            val intent = Intent(context,CropActivity::class.java)
            intent.putExtra(PHOTO_URI,imageUri)
            (context as AppCompatActivity).startActivityForResult(intent, REQUEST_CODE_CROP)
        }
    }
}
