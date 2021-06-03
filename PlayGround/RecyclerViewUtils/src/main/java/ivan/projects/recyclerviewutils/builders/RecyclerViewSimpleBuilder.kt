package ivan.projects.recyclerviewutils.builders

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import ivan.projects.recyclerviewutils.adapters.SimpleAdapter

class RecyclerViewSimpleBuilder<MODEL, DTO> {
    var view2Dto : ((View)->DTO)? = null
    var onBind : ((View, MODEL, DTO)-> Unit)? = null
    var diffUtilCallback : DiffUtil.ItemCallback<MODEL>? = null
    var items: List<MODEL>? = null
    @androidx.annotation.LayoutRes var layoutId : Int = 0
    var refreshData: ((Unit)->List<MODEL>)? = null

    fun build(): SimpleAdapter<MODEL, DTO> {
        val adapter = SimpleAdapter<MODEL, DTO>(layoutId,
            diffUtilCallback!!,
            view2Dto!!,
            onBind!!)
        if(items != null)
            adapter.submit(items!!)
        return adapter
    }
}