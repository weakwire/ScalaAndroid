package com.weakwire.scala.android

import android.app.Activity
import android.view.{WindowManager, Window, View}
import android.os.Bundle

trait ScalaActivity extends Activity with _OnCreate {

  implicit def findView[T <: View](id: Int): T = findViewById(id).asInstanceOf[T]

  implicit def id2View(id: Int) = new _ScalaView[View](findView(id))

}

trait _OnCreate extends Activity {
  private var onCreateThunk = (savedInstanceState: Bundle) => {}
  private var layout: Option[Any] = None

  def withLayout(id: Int) {
    layout = Some(id)
  }

  var _noTitle = false

  def noTitle {
    _noTitle = true
  }

  var _fullScreen = false

  def fullScreen {
    _fullScreen = true
  }

  def withLayout(id: View) {
    layout = Some(id)
  }

  def onCreate(f: (Bundle) => Unit) = {
    onCreateThunk = f
    this
  }

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    if (_noTitle) requestWindowFeature(Window.FEATURE_NO_TITLE);
    if (_fullScreen) getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    layout.get match {
      case v: View => setContentView(v)
      case id: Int => setContentView(id)
    }
    onCreateThunk(savedInstanceState)

  }

}
