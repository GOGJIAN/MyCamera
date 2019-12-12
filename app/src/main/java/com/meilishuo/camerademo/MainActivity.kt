package com.meilishuo.camerademo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.huantansheng.easyphotos.EasyPhotos
import com.huantansheng.easyphotos.callback.SelectCallback
import com.huantansheng.easyphotos.models.album.entity.Photo
import com.huantansheng.easyphotos.ui.EasyCameraActivity.CAMERA_TYPE
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private val selectedPhotoList = ArrayList<Photo>()
    private val callback = object : SelectCallback() {
        override fun onResult(photos: ArrayList<Photo>, paths: ArrayList<String>, isOriginal: Boolean) {

            var bitmap = BitmapFactory.decodeFile(photos.get(0).path).copy(Bitmap.Config.ARGB_8888, true)
            img.visibility= View.VISIBLE
            img.setImageBitmap(bitmap)

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        aaaaa.setOnClickListener {
            EasyPhotos.createCamera(this)
                .start(callback,CAMERA_TYPE)
        }

    }
}
