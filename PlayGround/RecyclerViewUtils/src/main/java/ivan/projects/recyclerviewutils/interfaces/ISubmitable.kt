package ivan.projects.recyclerviewutils.interfaces

interface ISubmitable<MODEL> {
    fun submit(newItems : List<MODEL>)
}