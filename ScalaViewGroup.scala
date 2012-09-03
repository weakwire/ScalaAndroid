package com.weakwire.scala.android

import android.view.{ViewParent, View, ViewGroup}

trait ScalaViewGroup {
  implicit def view2ScalaViewGroup[T <: ViewGroup](viewGroup: T) =
    new _ScalaViewGroup[T](viewGroup)
}

class _ScalaViewGroup[T <: ViewGroup](viewGroup: T) {

  def withChildren(action: View => Unit) {
    for (i <- 0 to viewGroup.getChildCount - 1)
    yield action(viewGroup.getChildAt(i))
  }

  /**
   * Gets parent the safe way
   * @return Option[ViewParent]
   */
  def getParent2(): Option[ViewParent] = {
    Some(viewGroup.getParent)
  }


}

