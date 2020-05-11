class Pilha(var n: Int)
{
    var top = -1
    var S: IntArray? = null
    fun stack_empty(): Boolean
    {
        return if (top == -1) true else false
    }

    fun push(x: Int)
    {
        top = top + 1
        S!![top] = x
    }

    fun pop(): Int
    {
        return if (stack_empty())
        {
            println("underflow")
            0
        }
        else
        {
            top = top - 1
            S!![top + 1]
        }
    }

    override fun toString(): String
    {
        val res = StringBuilder()
        for (x in S!!) {
            res.append(x)
            res.append(" ")
        }
        return res.toString()
    }

    init
    {
        top = -1
        S = IntArray(n)
    }
}