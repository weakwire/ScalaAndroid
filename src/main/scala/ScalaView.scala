package com.weakwire.scala.android
import android.view.{MotionEvent, View}
import android.view.View.{OnLongClickListener, OnFocusChangeListener, OnTouchListener, OnClickListener}

trait ScalaView {
  implicit def view2ScalaView[T <: View](view: T) =
    new _ScalaView[T](view)
}

class _ScalaView[T <: View](view: T) {

  /*  LISTENERS */

  def onClick(action: T => Any) =
    view.setOnClickListener(new OnClickListener {
      def onClick(view: View) {
        action(view.asInstanceOf[T])
      }
    })

  def onTouch(action: (T, MotionEvent) => Boolean) =
    view.setOnTouchListener(new OnTouchListener {
      def onTouch(view: View, event: MotionEvent): Boolean = {
        action(view.asInstanceOf[T], event)
      }
    })

  def onFocusChange(action: (T, Boolean) => Any) =
    view.setOnFocusChangeListener(new OnFocusChangeListener {
      def onFocusChange(view: View, hasFocus: Boolean) {
        action(view.asInstanceOf[T], hasFocus)
      }
    })

  def onLongClick(action: T => Boolean) =
    view.setOnLongClickListener(new OnLongClickListener {
      def onLongClick(view: View): Boolean = {
        action(view.asInstanceOf[T])
      }
    })

}

