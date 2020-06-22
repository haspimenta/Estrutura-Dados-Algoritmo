/*Hugo Alexandre Silva
nº 18544
18544@stu.ipbeja.pt
 */

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.io.PrintWriter
import java.util.*

object GrafoMain {
    //cria a tabela dinamicamente com X numeros se N_VERTICES e X numeros de N_ARESTAS
    private const val N_VERTICES = 20000
    private const val N_ARESTAS = 20000
    //determina o maior custo para uma determinada arestas
    private const val N_MAX_CUSTO = 20

    @JvmStatic
    fun main(args: Array<String>) {

        val r = Random()
        //construi arestas e adiciona instâncias à lista de arestas do grafo
        val edges: MutableList<Edge> = ArrayList()
        //criação dos vertices para primeiro momento de teste do código
        //descomente e comente a N_VERTICES e FOR VERTICES
        /*
        Vertice a = grafos.addVertice("a");
        Vertice b = grafos.addVertice("b");
        Vertice c = grafos.addVertice("c");
        Vertice d = grafos.addVertice("d");
        Vertice e = grafos.addVertice("e");
         */

        //primeiras arestas determinadas como entrada para verificação dos valores
        edges.add(Edge(0, 1, -1))
        edges.add(Edge(0, 2, 4))
        edges.add(Edge(1, 2, 3))
        edges.add(Edge(1, 3, 2))
        edges.add(Edge(1, 4, 2))
        edges.add(Edge(3, 1, 1))
        edges.add(Edge(4, 3, -3))
        edges.add(Edge(4, 3, -3))
        edges.add(Edge(4, 3, -3))

        //ler o arquivo com os valores de origem, destino e custo respectivamente
        val path = "docs/EntradaGrafo.txt"
        leitor(path)

        //for para arestas
        for (i in 0 until N_ARESTAS) {
            edges.add(Edge(r.nextInt(N_ARESTAS),
                    r.nextInt(N_ARESTAS),
                    r.nextInt(N_MAX_CUSTO)))
        }
        //for para os vertices
        for (i in 0 until N_VERTICES) {
            edges.add(Edge(r.nextInt(N_VERTICES),
                    r.nextInt(N_VERTICES),
                    r.nextInt(N_MAX_CUSTO)))
        }
        //criação do grafo com vertices e arestas
        val grafo = Grafo(N_VERTICES, edges)
        //Print(grafo) //debug para verificaçãodo codigo
        //iniciar a contagem do tempo de execução do algoritmo
        val startTime = System.currentTimeMillis()
        val writer = PrintWriter("docs/TabelaGrafo.txt", "UTF-8") //escreve o resultado no arquivo
        Print(BellmanFord(grafo, 0))
        val endTime = System.currentTimeMillis() //finaliza o tempo de execuçao do algoritmo
        val elapsedTime = endTime - startTime //calculo do tempo tortal de execução do algoritmo

        println("Time start: " + startTime + " ms" + "\n" + "End time: " + endTime + " ms")
        print("Total time: " + elapsedTime + " ms")

        writer.print("${BellmanFord(grafo, 0)} $elapsedTime\n") //conclui a escrita no arquivo com os resultados
        writer.close()//fecha o processo de escrita
    }

    private fun BellmanFord(grafo: Grafo, origem: Int): List<Int>
    {
        val distancias: MutableList<Int> = ArrayList()
        //iniciar todos os valores das distancias a infinito para cada vértice
        for (i in 0 until grafo.nVertices)
        {
            distancias.add(Int.MAX_VALUE)
        }
        distancias[origem] = 0

        //em cada vértice, aplica o relaxamento para todas as arestas
        for (i in 1 until grafo.nVertices)
        {
            for (j in grafo.edges.indices)
            {
                val orig = grafo.edges[j].origem
                val dest = grafo.edges[j].destino
                val cust = grafo.edges[j].custo
                if (distancias[orig] != Int.MAX_VALUE && distancias[orig] + cust < distancias[dest])
                {
                    distancias[dest] = distancias[orig] + cust
                }
            }
        }
        //executando o algoritmo uma segunda vez para determinar quais nós fazem parte
        // do ciclo negativo, se ocorrer poderemos encontrar um caminho melhor além da solução ideal.
        for (i in 1 until grafo.nVertices)
        {
            for (j in grafo.edges.indices)
            {
                val orig = grafo.edges[j].origem
                val dest = grafo.edges[j].destino
                val cust = grafo.edges[j].custo
                //max_value determina o maior valor para o infinito na categoria de inteiro, neste caso valores positivos
                if (distancias[orig] != Int.MAX_VALUE && distancias[orig] + cust < distancias[dest])
                {
                    //min_value determina o maior valor para o infinito na categoria de inteiro, neste caso valores negativos
                    distancias[dest] = Int.MIN_VALUE
                }
            }
        }
        return distancias
    }
    //Print(grafo) //debug para verificaçãodo codigo
    private fun Print(grafo: Grafo)
    {
        for (edge in grafo.edges)
        {
            println(edge.origem.toString() + ' '
                    + edge.destino.toString() + ' '
                    + edge.custo.toString())
        }
    }
    //função para impressão do resultados
    private fun Print(values: List<Int>)
    {
        for (i in values.indices)
        {
            println(values[i].toString())
        }
    }
    //gráfico com vértices e arestas
    internal class Grafo(var nVertices: Int, var edges: List<Edge>)
    //função auxiliar para adicionar aresta ao gráfico
    //aresta direcionada com um custo
    class Edge(var origem: Int, var destino: Int, var custo: Int)

    //faz a leitura do arquivo com tabela de dados origem, destino e custo
    @Throws(IOException::class)
    fun leitor(path: String?) {
        val buffRead = BufferedReader(FileReader(path))
        var linha: String? = ""
        while (true) {
            if (linha != null) {
                println(linha)
            } else break
            linha = buffRead.readLine()
        }
        buffRead.close()
    }
}