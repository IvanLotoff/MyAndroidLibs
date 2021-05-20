package ivan.projects.expandablelayout

import androidx.databinding.BindingAdapter

class CardionViewExtentions {
    @BindingAdapter(value = ["parent_text"])
    fun CardionView.setParentText(text : String){
        this.parentText = text
    }
    @BindingAdapter(value = ["child_text"])
    fun CardionView.setChildText(text : String){
        this.childText = text
    }
    @BindingAdapter(value = ["parent_font_size"])
    fun CardionView.setParentFontSize(fontSize : Float){
        this.parentFontSize = fontSize
    }

    @BindingAdapter(value = ["child_font_size"])
    fun CardionView.setChildFontSize(fontSize : Float){
        this.childFontSize = fontSize
    }
    @BindingAdapter(value = ["parent_background_color"])
    fun CardionView.setParentBackgroundColor(color : Int){
        this.parentBackgroundColor = color
    }

    @BindingAdapter(value = ["child_background_color"])
    fun CardionView.setChildBackgroundColor(color : Int){
        this.childBackgroundColor = color
    }
    @BindingAdapter(value = ["animation_duration"])
    fun CardionView.setParentBorderRadius(duration : Int){
        this.animationDuration = duration
    }
    @BindingAdapter(value = ["parent_padding"])
    fun CardionView.setParentPadding(padding : Float){
        this.parentPadding = padding
    }
    @BindingAdapter(value = ["child_padding"])
    fun CardionView.setChildPadding(padding : Float){
        this.childPadding = padding
    }
}