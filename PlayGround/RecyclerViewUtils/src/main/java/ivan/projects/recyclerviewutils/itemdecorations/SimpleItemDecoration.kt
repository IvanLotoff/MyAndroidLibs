package ivan.projects.recyclerviewutils.itemdecorations

import android.graphics.*
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class SimpleItemDecoration : RecyclerView.ItemDecoration() {
    private var left = 0 // левый край разделителя
    private var right = 0 // правый край разделителя
    private var top = 0 // Верх разделителя

    private val paint = Paint().apply{
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 2f
    }
    private val path = Path()

    /**
     * Опциональная установка [PathEffect], который будет
     * применяться к точке и в конечном итогу отразится на
     * прорисовке линии
     */
    var pathEffect : PathEffect? = null
    set(value) {
        paint.pathEffect = value
        field = value
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        for(i in 0 until parent.childCount){
            left = parent.getChildAt(i).paddingLeft
            right = parent.getChildAt(i).width - parent.getChildAt(i).paddingRight
            top = parent.getChildAt(i).bottom
            path.moveTo(left.toFloat(), top.toFloat())
            path.lineTo(right.toFloat(), top.toFloat())
            c.drawPath(path, paint)
        }
    }
}