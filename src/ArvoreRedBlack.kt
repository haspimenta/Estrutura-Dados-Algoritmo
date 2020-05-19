@Suppress("NAME_SHADOWING")
class ArvoreRedBlack(n: Int) : ArvorePesquisaBinaria(n)
{
    var color: IntArray? = null
    val BLACK = 0
    val RED = 1
    var right: IntArray? = null
    var left: IntArray? = null
    var p: IntArray? = null
    fun left_rotate(x: Int) {
        val y = right!![x]
        right!![x] = left!![y]
        if (left!![y] != NIL)
        {
            p!![left!![y]] = x
        }
        p!![y] = p!![x]
        if (p!![x] == NIL) root = y else if (x == p!![left!![x]]) p!![left!![x]] =
            y else right!![p!![x]] = y
        left!![y] = x
        p!![x] = y
    }
    fun right_rotate(x: Int)
    {
        val y = left!![x]
        left!![x] = right!![y]
        if (right!![y] != NIL)
        {
            p!![right!![y]] = x
        }
        p!![y] = p!![x]
        if (p!![x] == NIL) root = y else if (x == p!![right!![x]]) p!![right!![x]] =
            y else left!![p!![x]] = y
        right!![y] = x
        p!![x] = y
    }
    fun insert_fixup(z: Int)
    {
        var z = z
        var y: Int
        while (color!![p!![z]] == RED)
        {
            if (p!![z] == left!![p!![p!![z]]])
            {
                y = right!![p!![p!![z]]]
                if (color!![y] == RED)
                {
                    color!![p!![z]] = BLACK
                    color!![y] = BLACK
                    color!![p!![p!![z]]] = RED
                    z = p!![p!![z]]
                }
                else
                {
                    if (z == right!![p!![z]])
                    {
                        z = p!![z]
                        left_rotate(z)
                    }
                    color!![p!![z]] = BLACK
                    color!![p!![p!![z]]] = RED
                    right_rotate(p!![p!![z]])
                }
            }
            else
            {
                y = left!![p!![p!![z]]]
                if (color!![y] == RED)
                {
                    color!![p!![z]] = BLACK
                    color!![y] = BLACK
                    color!![p!![p!![z]]] = RED
                    z = p!![p!![z]]
                }
                else
                {
                    if (z == left!![p!![z]])
                    {
                        z = p!![z]
                        right_rotate(z)
                    }
                    color!![p!![z]] = BLACK
                    color!![p!![p!![z]]] = RED
                    left_rotate(p!![p!![z]])
                }
            }
        } /* while */
        color!![root] = BLACK
    }
    override fun insert(key: String?)
    {
        super.insert(key)
        color!![z] = RED
        print("z = ")
        println(z)
        insert_fixup(z)
    }
    override fun toString(): String
    {
        val s = super.toString()
        val res = StringBuilder()
        res.append("\n")
        for (k in color!!.indices.reversed())
        {
            res.append(k)
            res.append("-> color = ")
            res.append(if (color!![k] == 0) "BLACK" else "RED")
            res.append("\n")
        }
        return s + res
    }
    init
    {
        color = IntArray(n)
        right = m!!.right
        left = m!!.left
        p = m!!.p
    }
}