package ivan.projects.recyclerviewutils.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ivan.projects.recyclerviewutils.interfaces.IOnBind

/**
 * Базовый класс ViewHolder-a для обычного RecyclerView.Adapter
 */
abstract class BaseSimpleViewHolder<T>(view: View)
    : RecyclerView.ViewHolder(view), IOnBind<T>