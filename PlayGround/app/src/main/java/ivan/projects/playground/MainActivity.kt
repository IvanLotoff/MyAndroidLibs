package ivan.projects.playground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ivan.projects.recyclerviewutils.adapters.general.GeneralSimpleAdapterWithInitCallback

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
        val recyclerView = findViewById<RecyclerView>(R.id.rec_view)
        val arr = listOf<String>("check1", "check2", "check3")
        val people = listOf<Person>(Person("name1", "phone1"), Person("name2", "phone2"))
//        val adapter = GeneralSimpleAdapterWithOnBindLambda<String>(R.layout.item,arr ){ view: View, line: String ->
//            view.findViewById<TextView>(R.id.item_text_id).apply {
//                text = line
//            }
//        }
        val adapter = GeneralSimpleAdapterWithInitCallback<Person, Dto>(
            R.layout.item_person,
            people,
            Dto(null,null),
            {
                view : View, dto : Dto ->
                val name = view.findViewById<TextView>(R.id.name_text_view)
                val phone = view.findViewById<TextView>(R.id.phone_text_view)
                dto.nameTextView = name
                dto.phoneTextView = phone
            },
            {
                    _: View, person : Person, dto : Dto ->
              dto.phoneTextView?.text = person.phone
              dto.nameTextView?.text = person.name
            }
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
    data class Person(var name : String, var phone : String)
    data class Dto(var nameTextView: TextView?, var phoneTextView: TextView?)
}