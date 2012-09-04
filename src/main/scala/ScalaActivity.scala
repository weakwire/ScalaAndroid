package com.weakwire.scala.android

import android.app.Activity
import android.view.View
import android.os.Bundle

trait ScalaActivity extends Activity with _OnCreate {

  implicit def findView[T <: View](id: Int): T = findViewById(id).asInstanceOf[T]

  implicit def id2View(id: Int) = new _ScalaView[View](findView(id))

}

trait _OnCreate extends Activity {
  private var onCreateThunk = (savedInstanceState:Bundle) => {}
  private var layout: Option[Any] = None

  def withLayout(id: Int) {
    layout = Some(id)
  }

  def withLayout(id: View) {
    layout = Some(id)
  }

  def onCreate(f: (Bundle) => Unit) {
    onCreateThunk = f
  }

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    onCreateThunk(savedInstanceState)
    layout.get match {
      case v: View => setContentView(v)
      case id: Int => setContentView(id)
    }
  }
}
