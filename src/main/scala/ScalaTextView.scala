package com.weakwire.scala.android
import android.view.KeyEvent
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener

trait ScalaTextView {
  implicit def textView2ScalaTextView[T <: TextView](textView: T) =
    new _ScalaTextView[T](textView)
}

class _ScalaTextView[T <: TextView](textView: T) {

  def onEditorAction2(action: (TextView, Int, KeyEvent) => Boolean) = {
    textView.setOnEditorActionListener(new OnEditorActionListener {
      def onEditorAction(textView: TextView, id: Int, event: KeyEvent) =
        action(textView, id, event)
    })
  }

}

