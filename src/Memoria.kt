class Memoria(n: Int) //n recebe a contagem de palavras
{
    //cria o array com numero de palavras para trabalhar com a pilha
    //temos o nodo pai e os nodos para a direita e esquerda do ponto centro
    var p: IntArray? = null
    var key: Array<String?>? = null
    var left: IntArray? = null
    var right: IntArray? = null
    val NIL = 0
    var s: Pilha? = null
    //uma condição para valor "verdadeiro",
    //verdadeiro é de cor vermelha, caso contrário é preto

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
        //o nodo com valor que foi enviado como parâmetro
        //pega o menor valor na árvore dependendo do nodo que está em execução no momento, para esquerda ou direita
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
        //cria o array com numero de palavras para trabalhar com a pilha
        // temos o nodo pai e os nodos para a direita e esquerda do ponto centro
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