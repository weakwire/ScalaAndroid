package com.weakwire.scala.android

import android.app.Activity
import android.view.View
import android.widget.TextView

trait ScalaActivity extends Activity {

  def findView2[T <: View](id: Int):T = {
    findViewById(id).asInstanceOf[T]
  }

}
