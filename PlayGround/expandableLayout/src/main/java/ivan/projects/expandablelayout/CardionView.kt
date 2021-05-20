package ivan.projects.expandablelayout

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.setPadding
import androidx.core.view.updateLayoutParams
import ivan.projects.expandablelayout.databinding.CardionViewLayoutBinding

class CardionView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(
    context,
    attributeSet,
    defStyle) {

    override fun onFinishInflate() {
        super.onFinishInflate()
        // изначально всё свёрнуто
        cardionViewLayoutBinding.secondTextView.updateLayoutParams {
            height = 0
        }
        cardionViewLayoutBinding.frameId.setOnClickListener {
            if (this.isExpanded)
                this.collapse()
            else
                this.expand()
        }
        // повернем стрелку на 90 градусов
        cardionViewLayoutBinding.spinnerId.rotation = 90f
        Log.d(TAG, "onFinishInflate: " + (this.layoutParams != null))
        this.addView(cardionViewLayoutBinding.root, outerLayoutParams)
    }
    private lateinit var outerLayoutParams : ViewGroup.LayoutParams
    /**
     * A property to hold text that will be initially displayed upper than
     * appearing text
     */
    var parentText: String = ""
        set(value) {
            field = value
            cardionViewLayoutBinding.parentTextView.text = value
        }

    /**
     * A property to hold text that will be shown after dropping down
     */
    var childText: String = ""
        set(value) {
            field = value
            cardionViewLayoutBinding.secondTextView.text = value
        }

    @Sp
    var parentFontSize: Float = 12f
        set(value) {
            field = value
            cardionViewLayoutBinding.parentTextView.textSize = value
        }

    @Sp
    var childFontSize: Float = 12f
        set(value) {
            field = value
            cardionViewLayoutBinding.secondTextView.textSize = value
        }
    var parentBackgroundColor: Int = Color.BLUE
        set(value) {
            field = value
            cardionViewLayoutBinding.frameId.setBackgroundColor(value)
        }
    var childBackgroundColor: Int = Color.GREEN
        set(value) {
            field = value
            cardionViewLayoutBinding.secondTextView.setBackgroundColor(value)
        }

    @Milliseconds
    var animationDuration: Int = 200
    var parentPadding : Float = 0f
        set(value){
            field = value
            cardionViewLayoutBinding.parentTextView.setPadding(value.toInt())
        }
    var childPadding : Float = 0f
        set(value) {
            field = value
            cardionViewLayoutBinding.secondTextView.setPadding(value.toInt())
        }
    val cardionViewLayoutBinding: CardionViewLayoutBinding =
        CardionViewLayoutBinding.inflate(LayoutInflater.from(context), null, false)
    private var secondLayoutHeight = 0

    private var isExpanded = false
    private fun expand() {
        isExpanded = true
        ValueAnimator.ofFloat(0f, 1f).apply {
            duration = animationDuration.toLong()
            addUpdateListener {
                val animValue = it.animatedValue as Float
                cardionViewLayoutBinding.secondTextView.updateLayoutParams {
                    height = (secondLayoutHeight * animValue).toInt()
                    cardionViewLayoutBinding.spinnerId.rotation = (1 - animValue) * 90
                }
            }
        }.start()

    }
    private fun collapse(){
        isExpanded = false
        ValueAnimator.ofFloat(0f, 1f).apply {
            duration = animationDuration.toLong()
            addUpdateListener {
                val animValue = it.animatedValue as Float
                cardionViewLayoutBinding.secondTextView.updateLayoutParams {
                    height = (secondLayoutHeight * (1 - animValue)).toInt()
                    cardionViewLayoutBinding.spinnerId.rotation = 90 * animValue
                }
            }
        }.start()
    }
    companion object{
        const val TAG = "Tagging"
    }

    init {
        if (attributeSet != null) {
            val typedArray = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.CardionView,
                defStyle,
                0)
            try {
                parentText = typedArray.getString(R.styleable.CardionView_parent_text) ?: ""
                childText = typedArray.getString(R.styleable.CardionView_child_text) ?: ""
                parentFontSize = typedArray.getDimension(R.styleable.CardionView_parent_font_size, 12f)
                childFontSize = typedArray.getDimension(R.styleable.CardionView_child_font_size, 12f)
                parentBackgroundColor =
                    typedArray.getColor(R.styleable.CardionView_parent_background_color, Color.BLUE)
                childBackgroundColor =
                    typedArray.getColor(R.styleable.CardionView_child_background_color, Color.GREEN)
                animationDuration =
                    typedArray.getInteger(R.styleable.CardionView_animation_duration, 200)
                outerLayoutParams = LayoutParams(context, attributeSet)
                parentPadding = typedArray.getDimension(R.styleable.CardionView_parent_padding, 0f)
                childPadding = typedArray.getDimension(R.styleable.CardionView_child_padding, 0f)
            } finally {
                typedArray.recycle()
            }
        }
        cardionViewLayoutBinding.secondTextView.measure(
            MeasureSpec.makeMeasureSpec(80000, MeasureSpec.UNSPECIFIED),
            MeasureSpec.makeMeasureSpec(80000, MeasureSpec.UNSPECIFIED)
        )
        secondLayoutHeight = cardionViewLayoutBinding.secondTextView.measuredHeight
    }
}