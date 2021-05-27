package ivan.projects.recyclerviewutils.adapters.base

import android.util.Log
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import ivan.projects.recyclerviewutils.viewholders.BaseSimpleViewHolder

abstract class BaseSimpleAdapter<T>(protected val items : List<T>)
    : RecyclerView.Adapter<BaseSimpleViewHolder<T>>() {
    override fun getItemCount() = items.size
    override fun onBindViewHolder(holder: BaseSimpleViewHolder<T>, position: Int) {
        holder.onBind(items[position])
        Log.d("OnBindCheck", "onBindViewHolder: " + items[position])
    }
}