class Pilha(var n: Int)//n recebe a contagem de palavras
{//entra nulo pois a posição zero do array já armazena a primeira informação
    var top = -1
    var S: IntArray? = null
//Verifica posicaoPilha se igual -1, pilha nula, retornando verdadeiro
    fun stack_empty(): Boolean
    {
        return if (top == -1) true else false
    }
//indica quantos itens tem dentro da pilha, somando 1 a cada iteração
    fun push(x: Int)
    {
        top = top + 1
        S!![top] = x
    }
//se a posicao pilha é menor que o tamanho pilha, sendo verdadeiro insere valor na pilha e adiciona atributo a posição pilha
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
//verificamos última posição da pilha e decrementa o atributo posicao pilha
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