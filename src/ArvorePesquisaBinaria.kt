open class ArvorePesquisaBinaria(n: Int)
{
    val n: Unit = Unit
    protected var m: Memoria? = null
    protected val NIL = 0
    protected var root = NIL
    protected var z = 0
    open fun insert(key: String?)
    {
        z = m!!.allocate_object()
        m!!.key!![z] = key
        var y = NIL
        var x = this.root
        while (x != NIL)
        {
            y = x

            if (m!!.key!!.get(z)!!.compareTo(m!!.key!!.get(x).toString()) < 0) //compara ordem lexicográfical
            {
               x = m!!.left!!.get(x)
            }
            else
            {
               x = m!!.right!!.get(x)
            }
        }
        m!!.p!![z] = y
        if (y == NIL)
        {
            this.root = z
        }
        else if (m!!.key!!.get(z)!!.compareTo(m!!.key!!.get(y).toString()) < 0) //compara ordem lexicográfical
        {
            m!!.left!![y] = z
        }
        else
        {
            m!!.right!![y] = z
        }
    }
    override fun toString(): String
    {
        return m.toString().toString() + "root -> " + if (root == 0) "nil" else root
    }
    init
    {
        m = Memoria(n)
    }
}