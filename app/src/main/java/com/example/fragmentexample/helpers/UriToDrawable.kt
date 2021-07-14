package com.example.fragmentexample.helpers

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.example.fragmentexample.R
import java.io.FileNotFoundException

object UriToDrawable {


    fun uriToDrawable(uri: String?, context: Context): Drawable? {
        uri?.let {
            val inputStream = context.contentResolver.openInputStream(Uri.parse(uri))
            return Drawable.createFromStream(inputStream, uri)
        }
        return ContextCompat.getDrawable(context, R.drawable.phone);
    }
}