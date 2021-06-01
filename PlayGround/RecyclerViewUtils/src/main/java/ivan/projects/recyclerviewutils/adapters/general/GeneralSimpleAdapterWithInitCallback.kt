package ivan.projects.recyclerviewutils.adapters.general

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import ivan.projects.recyclerviewutils.adapters.base.BaseSimpleAdapterInitCallback
import ivan.projects.recyclerviewutils.viewholders.BaseSimpleViewHolderInitCallback

open class GeneralSimpleAdapterWithInitCallback<T, DTO>(@LayoutRes private val layoutId: Int,
                                                        items: List<T>,
                                                        private val initCallback : (View) -> DTO,
                                                        private val onBindLambda: (View, T, DTO) -> Unit)
    : BaseSimpleAdapterInitCallback<T, DTO>(items) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseSimpleViewHolderInitCallback<T, DTO> {

        return object : BaseSimpleViewHolderInitCallback<T, DTO>(
            LayoutInflater.from(parent.context).inflate(layoutId, parent,false),
            initCallback
        ) {
            override fun onBind(item: T) {
                onBindLambda(itemView, item, dto)
            }
        }
    }

}