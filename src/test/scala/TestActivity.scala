import android.app.Activity
import com.weakwire.scala.android.ScalaAndroid

class TestActivity extends Activity with ScalaAndroid {
  withLayout(2)
  noTitle
  fullScreen


}


