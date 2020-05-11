class Memoria(n: Int)
{
    var p: IntArray? = null
    var key: Array<String?>? = null
    var left: IntArray? = null
    var right: IntArray? = null
    val NIL = 0
    var s: Pilha? = null
    fun allocate_object(): Int
    {
        val x: Int = s!!.pop()
        return if (x == 0)
        {
            0
        }
        else
        {
            x
        }
    }

    fun free_object(x: Int)
    {
        s!!.push(x)
    }

    override fun toString(): String
    {
        val res = StringBuilder()
        res.append("\n")
        for (k in p!!.indices.reversed())
        {
            res.append(k)
            res.append("-> [p = ")
            res.append(if (p!![k] == 0) "nil" else p!![k])
            res.append(", key = ")
            res.append(key!![k])
            res.append(", left = ")
            res.append(if (left!![k] == 0) "nil" else left!![k])
            res.append(", right = ")
            res.append(if (right!![k] == 0) "nil" else right!![k])
            res.append("]\n")
        }
        return res.toString()
    }

    init
    {
        p = IntArray(n)
        key = arrayOfNulls(n)
        left = IntArray(n)
        right = IntArray(n)
        s = Pilha(n)
        for (k in 1 until n)
        {
            s!!.push(k)
        }
        for (k in 0 until n)
        {
            p!![k] = NIL
            left!![k] = NIL
            right!![k] = NIL
        }
    }
}