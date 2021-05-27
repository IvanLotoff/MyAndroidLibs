package ivan.projects.recyclerviewutils.interfaces

import android.view.View

/**
 * интерфейс, который обязаны имплементить ViewHolder-ы
 */
interface IOnBind<E> {
    fun onBind(item : E)
}