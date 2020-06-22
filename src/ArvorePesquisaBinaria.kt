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
/*enquanto valor comparado da string é menor/diferente que o nodo corrente da árvore vai para subárvore esquerda
tendo elemento no nodo esquerdo continua a busca*/
        while (x != NIL)
        {
            y = x
//compara ordem lexicográfical
            if (m!!.key!!.get(z)!!.compareTo(m!!.key!!.get(x).toString()) < 0)
            {
               x = m!!.left!!.get(x)
            }
            else
            {
               x = m!!.right!!.get(x)
            }
        }
//tendo elemento no nodo direito continua a busca
        m!!.p!![z] = y
        if (y == NIL)
        {
            this.root = z
        }
//compara ordem lexicográfical
        else if (m!!.key!!.get(z)!!.compareTo(m!!.key!!.get(y).toString()) < 0)
        {
            m!!.left!![y] = z
        }
        else
        {
            m!!.right!![y] = z
        }
    }
//imprimos para verificar a ordem
    override fun toString(): String
    {
        return m.toString().toString() + "root -> " + if (root == 0) "nil" else root
    }
    init
    {
        m = Memoria(n)
    }
}