package ivan.projects.recyclerviewutils.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ivan.projects.recyclerviewutils.interfaces.IOnBind

class SimpleViewHolder<MODEL, DTO>(itemView: View,
                                   view2Dto:  (View)->DTO,
                                   private val onBindCallback: (View, MODEL, DTO)-> Unit)
    : RecyclerView.ViewHolder(itemView), IOnBind<MODEL> {
    private val dto = view2Dto(itemView)
    override fun onBind(item: MODEL) {
        onBindCallback(itemView, item, dto)
    }
}