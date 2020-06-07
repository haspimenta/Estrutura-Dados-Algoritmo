@Suppress("NAME_SHADOWING")
/*As rotações na árvore rubro negra tanto para esquerda e direita é para balancear a árvore,
a caracteristica da árvore preta e vermelha é esse balanceamento, levando o menor para esquerda e o maior para direita do nó pai*/
class ArvoreRedBlack(n: Int) : ArvorePesquisaBinaria(n)
{
    var color: IntArray? = null
    val BLACK = 0
    val RED = 1
    var right: IntArray? = null
    var left: IntArray? = null
    var p: IntArray? = null
/*rotação para esquerda, são trocados apenas os direcioanmentos esqueda ou direita,
fazendo as referencias conforme os valores das strings do texto, basenado na lexicografia*/
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
/*rotação para direita, são trocados apenas os direcioanmentos esqueda ou direita,
fazendo as referencias conforme os valores das strings do texto, basenado na lexicografia*/
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
/*deve ser chamado o fixaadicao, que irá corrigir os possíveis casos
de desbalanceamento que podem ocorrer na árvore*/
    fun insert_fixup(z: Int)
    {
        var z = z
        var y: Int
/*muda a cor do pai e do tio para preto e dos avós para vermelho, se necessario
assim, faz o giro e sobe dois níveis na árvore ate atingir a cores certas*/
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
        }
        color!![root] = BLACK
    }
/*adiciona um novo nodo com valor passado como parâmetro
na árvore que está rodando*/
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