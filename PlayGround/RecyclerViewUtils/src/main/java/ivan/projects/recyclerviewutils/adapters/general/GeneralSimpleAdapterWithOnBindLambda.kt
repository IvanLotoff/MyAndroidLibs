package ivan.projects.recyclerviewutils.adapters.general

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import ivan.projects.recyclerviewutils.adapters.base.BaseSimpleAdapter
import ivan.projects.recyclerviewutils.viewholders.BaseSimpleViewHolder

/**
 * класс, который принимает layout id, List<items> и onBindLambda и является реализацией
 * адаптера. В onBindViewHolder просто вызывается переданный колбек.
 * Недостатки класса: В лямбде скорее всего будет использоваться findViewById - очень дорогая
 * операция. Он будет производится гораздо большее количество раз, чем нужно было.
 * Однако данный класс удобен для отображения небольшого количества предметов в списке
 */
open class GeneralSimpleAdapterWithOnBindLambda<T>(@LayoutRes private val layoutId: Int,
                                                   items: List<T>,
                                                   private val onBindLambda: (View, T) -> Unit)
    : BaseSimpleAdapter<T>(items) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSimpleViewHolder<T> {
        return object : BaseSimpleViewHolder<T>(LayoutInflater.from(parent.context).inflate(layoutId, parent,false)) {
            override fun onBind(item: T) {
                onBindLambda(itemView,item)
                Log.d("OnBindCheck", "onBind: " + item)
            }
        }
    }
}