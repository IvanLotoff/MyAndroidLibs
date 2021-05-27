package ivan.projects.recyclerviewutils.viewholders

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ivan.projects.recyclerviewutils.interfaces.IOnBind

/**
 * Базовый класс ViewHolder-a для databinding-a
 */
abstract class BaseBindingViewHolder<T>(binding : ViewBinding)
    : RecyclerView.ViewHolder(binding.root), IOnBind<T>