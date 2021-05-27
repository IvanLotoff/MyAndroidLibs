package ivan.projects.recyclerviewutils.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ivan.projects.recyclerviewutils.interfaces.IOnBind

/**
 * ViewHolder, который принимает dto
 * DTO есть data class, который хранит все важные UI-элементы
 * (такие как TextView), в initCallback-e они единажды извлекаются в конструкторе
 * и далее переиспользутся
 */
abstract class BaseSimpleViewHolderInitCallback<E, DTO>(view: View,
                                                        protected val dto: DTO,
                                                        private val initCallback :(View, DTO) -> Unit)
    : RecyclerView.ViewHolder(view), IOnBind<E> {
    init{
        initCallback(view, dto)
    }
}