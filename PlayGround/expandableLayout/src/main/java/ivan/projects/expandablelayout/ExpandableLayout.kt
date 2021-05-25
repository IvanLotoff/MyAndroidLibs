package ivan.projects.expandablelayout

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.view.doOnLayout
import androidx.core.view.updateLayoutParams
import ivan.projects.expandablelayout.databinding.ChildWrapperBinding
import ivan.projects.expandablelayout.databinding.MainLayoutBinding

@Deprecated(message = "dont use it")
open class ExpandableLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0,
) : LinearLayout(context, attributeSet, defStyle) {

    private var animationValue: Float = 0f // выносим свойство сюда для сохранения состояния
    private var _isExpended = false
    private var _isExpending = false
    private var _isCollapsing = false

    var isExpended: Boolean
        get() = _isExpended
        private set(value) {
            _isExpended = value
        }

    var isExpending: Boolean
        get() = _isExpending
        private set(value) {
            _isExpending = value
        }

    var isCollapsing: Boolean
        get() = _isCollapsing
        private set(value) {
            _isCollapsing = value
        }

    private var _childLayoutHeight: Int = 0
    private var _parentView: TextView? = null
    private var _childView: TextView? = null

    private var childViewAdded = false

    private var mainParentLayout: FrameLayout = FrameLayout(context).apply {
        setBackgroundColor(Color.BLUE)
        setOnClickListener {
            if (!isExpended)
                this@ExpandableLayout.expand()
            else
                this@ExpandableLayout.collapse()
        }
        //layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    }
    private var imageSpinner = ImageView(context).apply {
        setImageResource(R.drawable.ic_arrow_down)
        layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        rotation = 90f
    }

    var parentView
        get() = _parentView
        set(value) {
            mainParentLayout.addView(value, 0)
            mainParentLayout.addView(imageSpinner, 1)
            (imageSpinner.layoutParams as FrameLayout.LayoutParams).gravity =
                Gravity.CENTER_VERTICAL or Gravity.RIGHT
            if (value != null) {
                copyLayoutParams(value, mainParentLayout)
                copyLayoutParams(value, this)
            }
            mainParentLayout.requestLayout()
        }

    private fun copyLayoutParams(from: View, to: View) {
        if (to.layoutParams == null) {
            to.layoutParams = LayoutParams(from.layoutParams.width, from.layoutParams.height)
        }
        to.updateLayoutParams {
            height = from.layoutParams.height
            width = from.layoutParams.width
        }
    }


    var childView
        get() = _childView
        set(value) {
//            if(this.contains(childBinding.root))
//                this.removeView(childBinding.root)
            _childView = value
            value?.doOnLayout {
                Log.d("Tagging", "doOnLayout: height = " + it.height)
                Log.d("Tagging", "doOnLayout: measuredHeight = " + it.measuredHeight)
                //_childLayoutHeight = it.height
            }
            _childView?.measure(
                MeasureSpec.makeMeasureSpec(80000, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(80000, MeasureSpec.UNSPECIFIED)
            )
            Log.d("Tagging", "this width : " + this.width + " this height = " + this.height)
            Log.d("Tagging", "child height after measure = : " + _childView?.measuredHeight)
            _childLayoutHeight = value!!.measuredHeight
            //Log.d("Tagging", "from setters : " + post{value?.measuredHeight})
            //value?.updateLayoutParams { height = 0 }
//            with(childBinding){
//                layoutWrapper.addView(_childView)
//            }
            Log.d("Tagging", "from setters : " + value?.height)
            //this.addView(childBinding.root)
        }

    @LayoutRes
    private var _parentLayoutResource: Int = 0

    @LayoutRes
    private var _childLayoutResource: Int = 0

    var parentLayoutResource: Int
        @LayoutRes get() = _parentLayoutResource
        set(value) {
            _parentLayoutResource = value
            updateParentView()
        }

    var childLayoutResource: Int
        @LayoutRes get() = _childLayoutResource
        set(value) {
            _childLayoutResource = value
            updateChildView()
        }

    private val binding: MainLayoutBinding =
        MainLayoutBinding.inflate(LayoutInflater.from(context), null, false)
    private val childBinding: ChildWrapperBinding =
        ChildWrapperBinding.inflate(LayoutInflater.from(context), null, false)

    private var _isSpinnerVisible = true
    var isSpinnerVisible
        get() = _isSpinnerVisible
        set(value) {
            _isSpinnerVisible = value
            validateSpinnerVisibility(value)
        }

    private fun validateSpinnerVisibility(value: Boolean) {
        with(binding) {
            spinnerId.visibility = if (value) View.VISIBLE else View.GONE
        }
    }

    init {
        /**
         * Если attributeSet не null, значит нам есть что извлекать
         * из аттрибутов лайаута
         */
        this.orientation = VERTICAL
        if (attributeSet != null) {
            initAttrsSet(attributeSet, defStyle)
        }
        //this.addView(binding.root)
        this.addView(mainParentLayout)
        this.isSaveEnabled = true
        this.id = View.generateViewId()
        //this.layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT) Здесь пока нету наших текст вьюх
    }

    private fun initAttrsSet(attributeSet: AttributeSet, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.ExpandableLayout,
            defStyle,
            0
        )
        try {
            isExpended = typedArray.getBoolean(R.styleable.ExpandableLayout_isExpanded, isExpended)
            parentLayoutResource = typedArray.getResourceId(
                R.styleable.ExpandableLayout_parentLayout,
                parentLayoutResource
            )
            childLayoutResource = typedArray.getResourceId(
                R.styleable.ExpandableLayout_childLayout,
                childLayoutResource
            )
            isSpinnerVisible =
                typedArray.getBoolean(R.styleable.ExpandableLayout_showSpinner, isSpinnerVisible)
        } finally {
            typedArray.recycle()
        }
    }

    protected fun updateChildView() {
        this._childView = LayoutInflater
            .from(context)
            .inflate(childLayoutResource, this, false) as TextView
        _childView?.visibility = View.GONE
        this.addView(_childView)
    }

    protected fun updateParentView() {
        this._parentView = LayoutInflater
            .from(context)
            .inflate(parentLayoutResource, this, false) as TextView
        with(binding) {
            mainLayoutTag.addView(_parentView)
            mainLayoutTag.setOnClickListener {
                this@ExpandableLayout.expand()
            }
        }
        this.updateLayoutParams {
            width = LayoutParams.MATCH_PARENT
        }
        this.addView(binding.root)
    }

    fun expand() {

        Log.d("rott", "expand: start = " + imageSpinner.rotation)
        val h = this.height
        Log.d(
            "Tagging",
            "expand: this.height = " + this.height + " this.measuredHeight = " + this.measuredHeight
                    + " childView.height = " + childView?.height + " childView.measureHeight = " + childView?.measuredHeight
                    + " parentView.hegiht = " + parentView?.height + " parentView.measuredHeight = " + parentView?.measuredHeight
        )
        isExpending = true
        Log.d("Tagging", "childView == null : " + (childView == null))
        childView?.visibility = View.VISIBLE
        //childView?.updateLayoutParams { height = 0 }
        Log.d(
            "Tagging",
            "expand: this.height = " + this.height + " this.measuredHeight = " + this.measuredHeight
                    + " childView.height = " + childView?.height + " childView.measureHeight = " + childView?.measuredHeight
                    + " parentView.hegiht = " + parentView?.height + " parentView.measuredHeight = " + parentView?.measuredHeight
        )
        //binding.mainLayoutTag.updateLayoutParams { height = 30 }

        Log.d("Tagging", "parent =" + childView?.parent)
        if (!childViewAdded) {
            this.addView(childView)
            childViewAdded = true
        }
        ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 5000
            addUpdateListener {
                animationValue = it.animatedValue as Float
                childView?.updateLayoutParams {
                    height = (_childLayoutHeight * animationValue).toInt()
                }
                imageSpinner.rotation = (1 - animationValue) * 90
                //this@ExpandableLayout.requestLayout()
            }
        }.start()
        isExpended = true
        isExpending = false
        Log.d("rott", "expand: end = " + imageSpinner.rotation)
    }

    fun collapse() {
        Log.d("rott", "collapse: start = " + imageSpinner.rotation)
        ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 5000
            addUpdateListener {
                animationValue = it.animatedValue as Float
                childView?.updateLayoutParams {
                    height = (_childLayoutHeight * (1 - animationValue)).toInt()
                }
                imageSpinner.rotation = 90 * animationValue
            }
        }.start()
        isExpended = false
        Log.d("rott", "collapse: start = " + imageSpinner.rotation)
    }

    override fun onSaveInstanceState(): Parcelable? {
        val onSaveInstanceState = super.onSaveInstanceState()
        val savedState = SavedState(onSaveInstanceState)
        savedState.isExpending = isExpending
        savedState.animationValue = animationValue
        Log.d("Savings",
            "onSaveInstanceState: isExpending = $isExpending animationValue = $animationValue")
        return savedState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        super.onRestoreInstanceState(state)
        isExpending = (state as SavedState).isExpending
        animationValue = state.animationValue
        if(isExpending)
            expand()
        Log.d("Savings",
            "onSaveInstanceState: isExpending = $isExpending animationValue = $animationValue")
    }
    private inner class SavedState : BaseSavedState {
        constructor(superState: Parcelable?) : super(superState)

        private constructor(source: Parcel?) : super(source) {
            animationValue = source?.readFloat() ?: 0f
            isExpending = source?.readInt() == 1
        }

        var isExpending : Boolean = false
        var animationValue : Float = 0f

        override fun writeToParcel(out: Parcel?, flags: Int) {
            super.writeToParcel(out, flags)
            out?.writeInt(if(isExpending) 1 else 0)
            out?.writeFloat(animationValue)
        }

        @JvmField
        val CREATOR: Creator<SavedState> =
            object : Creator<SavedState> {
                override fun createFromParcel(`in`: Parcel): SavedState? {
                    return SavedState(`in`)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return arrayOfNulls<SavedState>(size)
                }
            }

    }
}

