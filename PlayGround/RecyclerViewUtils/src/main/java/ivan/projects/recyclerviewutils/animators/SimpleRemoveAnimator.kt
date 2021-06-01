package ivan.projects.recyclerviewutils.animators

import android.animation.AnimatorInflater
import android.content.Context
import android.view.animation.BounceInterpolator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import ivan.projects.recyclerviewutils.R

class SimpleRemoveAnimator(private val c : Context) : SimpleItemAnimator() {
    override fun animateChange(
        oldHolder: RecyclerView.ViewHolder?,
        newHolder: RecyclerView.ViewHolder?,
        fromLeft: Int,
        fromTop: Int,
        toLeft: Int,
        toTop: Int,
    ): Boolean {
        return false // не поддерживаем анимацию изменения
    }

    override fun runPendingAnimations() {

    }

    override fun endAnimation(item: RecyclerView.ViewHolder) {

    }

    override fun endAnimations() {

    }

    override fun isRunning(): Boolean {
        return false
    }

    override fun animateRemove(holder: RecyclerView.ViewHolder?): Boolean {
        val loadAnimator = AnimatorInflater.loadAnimator(c, R.animator.zoom_in)
        loadAnimator.interpolator = BounceInterpolator()
        loadAnimator.setTarget(holder?.itemView)
        loadAnimator.start()
        return true
    }

    override fun animateAdd(holder: RecyclerView.ViewHolder?): Boolean {
        return false // не поддерживаем анимаццию добавления
    }

    override fun animateMove(
        holder: RecyclerView.ViewHolder?,
        fromX: Int,
        fromY: Int,
        toX: Int,
        toY: Int,
    ): Boolean {
        return false // не поддерживаем анимацию движения
    }

}