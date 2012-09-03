package com.weakwire.scala.android


trait ScalaBackground {
  implicit def function2AsyncTask[T1](f: () => T1): _ScalaAsyncTask[T1] = new _ScalaAsyncTask[T1](f)

  implicit def function2Handler(f: () => Any): _Handler = new _Handler(f)


  //  implicit def function2Runnable(f: () => Any): Runnable = new Runnable {
  //    def run() {
  //      new _Handler(f)
  //    }
  //  }
}

class _Handler(f: () => Any) {
  val handler = new android.os.Handler

  def post = handler.post(new Runnable {
    override def run() {
      f()
    }
  })

  def postDelayed(after: Long) = handler.postDelayed(new Runnable {
    override def run() {
      f()
    }
  }, after)

  /**
   * Not tested YET
   * @param at
   * @return
   */
  @deprecated def postAtTime(at: Long) = handler.postAtTime(new Runnable {
    override def run() {
      f()
    }
  }, at)
}

class _ScalaAsyncTask[T1](f1: () => T1) {
  def doInBackground = doInBackgroundAndThen((value: T1) => Unit)

  def doInBackgroundAndThen(f2: (T1) => Any) {
    new JavaAsyncTask[Function0[T1], Function0[Long], T1] {

      override protected def doInBackground(f: () => T1): T1 = {
        f1()
      }

      override def onPostExecute(result: T1) {
        f2(result)
      }

      override protected def onProgressUpdate(f: () => Long) {
        f()
      }

    }.execute(f1);
  }
}
