package ivan.projects.playground

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import ivan.projects.expandablelayout.CardionView
import ivan.projects.expandablelayout.ExpandableLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
 //       findViewById<CardionView>(R.id.just_id)
//        val child = findViewById<TextView>(R.id.child)
//        val parent = findViewById<TextView>(R.id.parent)
//        val exp = ExpandableLayout(this)
//        //exp.id = View.generateViewId()
//        exp.parentView = TextView(this).apply {
//            text = "parent"
//            textSize = 20f
//            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        }
//        exp.childView = TextView(this).apply {
//            text = "child"
//            textSize = 20f
//        }
//        findViewById<LinearLayout>(R.id.main_layout).addView(exp)
    }
}