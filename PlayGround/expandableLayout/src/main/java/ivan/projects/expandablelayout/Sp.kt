package ivan.projects.expandablelayout

import androidx.annotation.Dimension

/**
 * Points out that an integer is represented in Sps
 */
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY
)
@Dimension(unit = Dimension.SP)
annotation class Sp
