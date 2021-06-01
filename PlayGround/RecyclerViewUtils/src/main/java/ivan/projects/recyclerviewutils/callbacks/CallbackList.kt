package ivan.projects.recyclerviewutils.callbacks

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

fun createSwipeToDeleteCallback(adapter : RecyclerView.Adapter<*>, items : MutableList<*>) : (RecyclerView.ViewHolder, Int)-> Unit  {
    return {
            holder, _ ->
        val pos = holder.adapterPosition
        items.removeAt(pos)
        adapter.notifyItemRemoved(pos)
    }
}

fun <ITEM> createSwipeToDeleteCallbackWithSnackBar(adapter : RecyclerView.Adapter<*>,
                                                   items : MutableList<ITEM>,
                                                   view : View,
                                                   snackBarText: String,
                                                   actionText : String) : (RecyclerView.ViewHolder, Int)-> Unit  {
    return {
            holder, _ ->
        val pos = holder.adapterPosition // Запоминаем позицию предмета, который мы хотим удалить
        val item = items[pos]  // Запоминаем удаленный предмет

        items.removeAt(pos) // удаляем предмет из списка
        adapter.notifyItemRemoved(pos) // говорим адаптеру, что данный предмет удален

        Snackbar.make(view, snackBarText, Snackbar.LENGTH_LONG)
            .setAction(actionText) {
                items.add(pos, item)
                adapter.notifyItemInserted(pos)

                //dapter.notifyDataSetChanged()
                //adapter.notifyItemRangeInserted(pos, 1)
            }.show()

        Log.d("WithSnackBar", "createSwipeToDeleteCallbackWithSnackBar: " + items)
    }
}