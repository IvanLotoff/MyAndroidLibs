package ivan.projects.expandablelayout

import androidx.annotation.Dimension

/**
 * Points out that an integer is represented in Dps
 */
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY
)
@Dimension(unit = Dimension.DP)
internal annotation class Dp
