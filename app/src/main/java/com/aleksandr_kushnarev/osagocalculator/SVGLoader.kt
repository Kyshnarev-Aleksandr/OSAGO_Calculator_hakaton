package com.aleksandr_kushnarev.osagocalculator

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.ImageView
import coil.ImageLoader
import coil.api.load
import coil.decode.SvgDecoder
import coil.request.LoadRequest
import java.util.*

object SVGLoader {

    @SuppressLint("WrongViewCast")
    fun ImageView.loadSvgOrOthers(myUrl: String?) {
        myUrl?.let {
            if (it.toLowerCase(Locale.ENGLISH).endsWith("svg")) {
                val imageLoader = ImageLoader.Builder(this.context)
                    .componentRegistry {
                        add(SvgDecoder(this@loadSvgOrOthers.context))
                    }
                    .build()
                val request = LoadRequest.Builder(this.context)
                    .data(it)
                    .target(this)
                    .build()
                imageLoader.execute(request)
            } else {
                this.load(myUrl)
            }
        }
    }
    @kotlin.jvm.JvmStatic
    fun getSVG(imageView: ImageView, url : String?){
        imageView.loadSvgOrOthers(url)
    }
}