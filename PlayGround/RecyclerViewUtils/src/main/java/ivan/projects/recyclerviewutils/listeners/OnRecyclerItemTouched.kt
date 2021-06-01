package ivan.projects.recyclerviewutils.listeners

import android.view.View

import androidx.recyclerview.widget.RecyclerView

import android.view.MotionEvent

import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import androidx.recyclerview.widget.RecyclerView.SimpleOnItemTouchListener


abstract class OnRecyclerItemTouched : SimpleOnItemTouchListener() {
    private var mGestureDetector: GestureDetector? = null
    override fun onInterceptTouchEvent(recyclerView: RecyclerView, e: MotionEvent): Boolean {
        if (mGestureDetector == null) {
            mGestureDetector =
                GestureDetector(recyclerView.context, object : SimpleOnGestureListener() {
                    override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                        val childView = recyclerView.findChildViewUnder(e.x, e.y)
                        if (childView != null) {
                            val holder = recyclerView.getChildViewHolder(childView)
                            onItemClick(recyclerView, holder)
                        }
                        return true
                    }

                    override fun onLongPress(e: MotionEvent) {
                        val childView = recyclerView.findChildViewUnder(e.x, e.y)
                        if (childView != null) {
                            val holder = recyclerView.getChildViewHolder(childView)
                            onItemLongClick(recyclerView, holder)
                        }
                    }

                    override fun onDoubleTap(e: MotionEvent): Boolean {
                        //return super.onDoubleTap(e)
                        val childView = recyclerView.findChildViewUnder(e.x, e.y)
                        if(childView != null){
                           val holder = recyclerView.getChildViewHolder(childView)
                           onItemDoubleTap(recyclerView, holder)
                        }
                        return true
                    }
                })
        }
        mGestureDetector!!.onTouchEvent(e)
        return false
    }

    abstract fun onItemClick(recyclerView: RecyclerView?, holder : RecyclerView.ViewHolder)
    abstract fun onItemLongClick(recyclerView: RecyclerView?, holder : RecyclerView.ViewHolder)
    abstract fun onItemDoubleTap(recyclerView: RecyclerView?, holder : RecyclerView.ViewHolder)
}
