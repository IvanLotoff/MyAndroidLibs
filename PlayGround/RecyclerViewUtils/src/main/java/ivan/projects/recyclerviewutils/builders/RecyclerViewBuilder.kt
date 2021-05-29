package ivan.projects.recyclerviewutils.builders

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import ivan.projects.recyclerviewutils.adapters.general.GeneralSimpleAdapterWithInitCallback
import ivan.projects.recyclerviewutils.adapters.general.GeneralSimpleAdapterWithOnBindLambda

class RecyclerViewBuilder<MODEL, DTO> private constructor(){
    companion object {
        fun <MODEL, DTO> build(block: RecyclerViewBuilder<MODEL, DTO>.() -> Unit) =
            RecyclerViewBuilder<MODEL, DTO>().apply(block).build()
    }

    @LayoutRes var layoutRes = 0
    var dto : DTO? = null
    var items : (List<MODEL>)? = null
    /**
     * Лучшее место для инициализации view с помощью findViewById
     */
    var onInitViewHolder : ((View, DTO)-> Unit)? = null // nullable lambda
    var onBindViewHolderWithDTO : ((View, MODEL, DTO) -> Unit)? = null
    var onBindViewHolderWithoutDTO : ((View, MODEL) -> Unit)? = null

    fun build(): RecyclerView.Adapter<*> {
        if(layoutRes == 0)
            throw IllegalArgumentException("layoutRes must be set")
        if(dto == null && onInitViewHolder == null)
            return GeneralSimpleAdapterWithOnBindLambda(layoutRes,
                items!!,
                onBindViewHolderWithoutDTO!!)
        return GeneralSimpleAdapterWithInitCallback<MODEL, DTO>(layoutRes,
            items!!,
            dto!!, onInitViewHolder!!, onBindViewHolderWithDTO!!)
    }
}