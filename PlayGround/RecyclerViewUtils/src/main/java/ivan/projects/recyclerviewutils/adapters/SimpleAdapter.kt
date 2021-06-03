package ivan.projects.recyclerviewutils.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ivan.projects.recyclerviewutils.interfaces.ISubmitable
import ivan.projects.recyclerviewutils.viewholders.SimpleViewHolder

class SimpleAdapter<MODEL, DTO>(@LayoutRes private val layoutId : Int,
                                differCallback: DiffUtil.ItemCallback<MODEL>,
                                private val view2Dto:(View)->DTO,
                                private val onBindCallback: (View, MODEL, DTO)-> Unit )
    : RecyclerView.Adapter<SimpleViewHolder<MODEL, DTO>>(), ISubmitable<MODEL> {

    private val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SimpleViewHolder<MODEL, DTO> {
        return SimpleViewHolder<MODEL,DTO>(LayoutInflater.from(parent.context).inflate(layoutId, parent, false),
            view2Dto, onBindCallback)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder<MODEL, DTO>, position: Int) {
        holder.onBind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun submit(newItems: List<MODEL>) {
        differ.submitList(newItems)
    }
}