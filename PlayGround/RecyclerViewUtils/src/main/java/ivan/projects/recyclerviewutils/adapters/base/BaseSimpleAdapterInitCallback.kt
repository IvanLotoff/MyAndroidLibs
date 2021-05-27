package ivan.projects.recyclerviewutils.adapters.base

import androidx.recyclerview.widget.RecyclerView
import ivan.projects.recyclerviewutils.viewholders.BaseSimpleViewHolderInitCallback

abstract class BaseSimpleAdapterInitCallback <T, DTO>(protected val items : List<T>)
    : RecyclerView.Adapter<BaseSimpleViewHolderInitCallback<T, DTO>>() {
    override fun onBindViewHolder(holder: BaseSimpleViewHolderInitCallback<T, DTO>, position: Int) {
        holder.onBind(items[position])
    }
    override fun getItemCount(): Int {
        return items.size
    }
}