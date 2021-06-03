package ivan.projects.recyclerviewutils.ontouchhelpers

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ivan.projects.recyclerviewutils.extensions.toInt

class SimpleSwipeBuilder {

    /**
     * Is right swipe enabled
     */
    var rightSwipe = false

    /**
     * Is left swipe enabled
     */
    var leftSwipe = false

    var onSwipe: ((RecyclerView.ViewHolder, Int) -> Unit)? = null

    fun build(rec : RecyclerView){
        val obj = object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.RIGHT * rightSwipe.toInt() or
                    ItemTouchHelper.LEFT * leftSwipe.toInt()) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                onSwipe?.let { it(viewHolder, direction) }

            }
        }
        ItemTouchHelper(obj).attachToRecyclerView(rec)
    }
}