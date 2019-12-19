package com.huantansheng.easyphotos.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.huantansheng.cameralibrary.util.FileUtil
import com.huantansheng.easyphotos.R
import kotlinx.android.synthetic.main.activity_crop.*

class CropActivity : AppCompatActivity() {
    private var applicationName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop)
        //透明状态栏
        if (Build.VERSION.SDK_INT >= 23) {//21表示5.0
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        img_crop_photo.setAspectRatio(3,4)
        val resources = this.resources
        val dm = resources.displayMetrics
        val width = dm.widthPixels
        val height = dm.heightPixels
        val layoutParams = imgbg_view.getLayoutParams() as FrameLayout.LayoutParams
        layoutParams.height = width
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        imgbg_view.setLayoutParams(layoutParams)

        val uri = intent.getStringExtra(PHOTO_URI)
        if(!TextUtils.isEmpty(uri)){
            img_crop_photo.setImageBitmap(BitmapFactory.decodeFile(uri))
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
            setResult(Activity.RESULT_OK, intent)
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
