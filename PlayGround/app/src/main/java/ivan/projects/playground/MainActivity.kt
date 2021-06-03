package ivan.projects.playground

import android.graphics.DashPathEffect
import android.graphics.DiscretePathEffect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ivan.projects.recyclerviewutils.builders.RecyclerViewSimpleBuilder
import ivan.projects.recyclerviewutils.callbacks.createSwipeToDeleteCallbackWithSnackBar
import ivan.projects.recyclerviewutils.itemdecorations.SimpleItemDecoration
import ivan.projects.recyclerviewutils.listeners.OnRecyclerItemTouched
import ivan.projects.recyclerviewutils.ontouchhelpers.SimpleSwipeBuilder

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
        val people = mutableListOf<Person>(Person("name1", "phone1"),
            Person("name2", "phone2"),
            Person("name3", "phoen3"),
            Person("name4", "phone4"),
            Person("name5", "phone5"))
//        val adapter = GeneralSimpleAdapterWithOnBindLambda<String>(R.layout.item,arr ){ view: View, line: String ->
//            view.findViewById<TextView>(R.id.item_text_id).apply {
//                text = line
//            }
//        }

        //=======================================
//        val adapter = GeneralSimpleAdapterWithInitCallback<Person, Dto>(
//            R.layout.item_person,
//            people,
//            Dto(null,null),
//            {
//                view : View, dto : Dto ->
//                val name = view.findViewById<TextView>(R.id.name_text_view)
//                val phone = view.findViewById<TextView>(R.id.phone_text_view)
//                dto.nameTextView = name
//                dto.phoneTextView = phone
//            },
//            {
//                    _: View, person : Person, dto : Dto ->
//              dto.phoneTextView?.text = person.phone
//              dto.nameTextView?.text = person.name
//            }
//        )
//        val adapter = RecyclerViewBuilder.build<Person,Dto> {
//            items = people
//            layoutRes = R.layout.item_person
//            onBindViewHolderWithDTO = {
//              _: View, person : Person, dto : Dto ->
//              dto.phoneTextView?.text = person.phone
//              dto.nameTextView?.text = person.name
//            }
//            onInitViewHolder = {
//               view : View ->
//                Dto(
//                    view.findViewById<TextView>(R.id.name_text_view),
//                    view.findViewById<TextView>(R.id.phone_text_view)
//                )
//            }
//        }
        val adapter = RecyclerViewSimpleBuilder<Person, Dto>().apply {
            items = people
            view2Dto = {
                    view : View ->
                Dto(
                    view.findViewById<TextView>(R.id.name_text_view),
                    view.findViewById<TextView>(R.id.phone_text_view)
                )
            }
            onBind = {
              _: View, person : Person, dto : Dto ->
              dto.phoneTextView?.text = person.phone
              dto.nameTextView?.text = person.name
            }
            layoutId = R.layout.item_person
            diffUtilCallback = object : DiffUtil.ItemCallback<Person>(){
                override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
                    return oldItem == newItem
                }
                override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
                    return oldItem == newItem
                }
            }
        }.build()

        recyclerView.adapter = adapter

        val v = findViewById<CoordinatorLayout>(R.id.main_layout)

        recyclerView.layoutManager = LinearLayoutManager(this)
        SimpleSwipeBuilder().apply {
            leftSwipe = true
            onSwipe = createSwipeToDeleteCallbackWithSnackBar(
                adapter, people, v, "Вы удалили элемент", "Вернуть?"
            )
        }.build(recyclerView)

        recyclerView.addOnItemTouchListener(OnRecyclerItemTouched().apply {
            onItemClicked = {
                _, holder->
                Log.d("CheckTag", "onItemClicked: ${holder.adapterPosition}")
            }
            onItemDoubleTap = {
                    _, holder->
                Log.d("CheckTag", "onItemDoubleTap: ${holder.adapterPosition}")
            }
//            onItemLongClick = {
//                    _, holder->
//                Log.d("CheckTag", "onItemLongClicked: ${holder.adapterPosition}")
//            }
        })

        recyclerView.addItemDecoration(SimpleItemDecoration().apply {
            //pathEffect = DashPathEffect(floatArrayOf(15f,15f,15f,15f),5f)
            pathEffect = DiscretePathEffect(5f,5f)
        })
        val defaultItemAnimator = DefaultItemAnimator()
        defaultItemAnimator.removeDuration = 200
        recyclerView.itemAnimator = defaultItemAnimator
    }
    data class Person(var name : String, var phone : String)
    data class Dto(var nameTextView: TextView?, var phoneTextView: TextView?)
}