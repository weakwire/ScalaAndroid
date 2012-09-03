package com.weakwire.scala.android

import android.app.Activity
import android.view.View

trait ScalaActivity extends Activity {

  def findView[T <: View](id: Int): T = {
    findViewById(id).asInstanceOf[T]
  }

}
