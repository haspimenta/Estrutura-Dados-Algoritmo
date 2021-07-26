# Estrutura de Dados e Algoritmo

Seguindo o enunciado trabalho encontra-se dividido em 4 partes que correspondem às divisões da matéria leccionada na disciplina:
1. algoritmos de ordenação e análise da complexidade computacional dos algoritmos;
2. estruturas de dados simples: conjuntos dinâmicos, hash-tables e árvores de pesquisa binária;
3. grafos e algoritmos de: pesquisa simples; de geração de árvores de envergadura
mínima e de pesquisa de caminhos mais curtos;
4. introdução à concorrência e paralelismo na algoritmia.

#Grafo por meio de listas de adjacência

    - Em ciência da computação, uma lista de adjacência é uma estrutura de dados para representar grafos. 
    Em uma representação de lista de adjacência, podemos manter, para cada vértice do grafo, 
    uma lista de todos os outros vértices com os quais ele tem uma aresta (a "lista de adjacência", deste vértice).
    Por exemplo, a representação sugerida por van Rossum, em que uma tabela de dispersão (tabela hash) 
    é usada para associar cada vértice com um array de vértices adjacentes, pode ser vista como um exemplo deste 
    tipo de representação. Outro exemplo é a representação encontrada em Cormen et al. em que um array indexado 
    pelos números dos vértices aponta para uma lista simplesmente encadeada dos vizinhos de cada vértice.
                                                    (ebook ciência da computação - wikipedia
                                                    CORMEN, Thomas H.; LEISERSON, Charles E.; RIVEST Ronald L.; 
                                                    STEIN, Clifford (2001). Introduction to Algorithms 2ª ed. 
                                                    [S.l.]: MIT Press/McGraw-Hill. p. 420. ISBN 0-262-03293-7)
                                                    
- Uma lista de adjacências contém arestas direcionadas que formam o gráfico.
```sh
        Cada direcionamento é um conjunto de dois nós exercendo a sua ligação, caminho ou peso, através de arestas
correspondentes; sendo dirigido cada direcionamento é uma tupla de dois nós, um aponta o nó ou vértice de origem 
e o outro aponta para o nó ou vértice de destino.
```
        
 - Baseando nesta propriedade o algoritmo a seguir deve exercer um o modelo de um grafo e sua lista de adjacência.
 A lista esta diretamente relacionada com a ligação entre os vértices através das arestas. No nosso caso ainda vamos
 levar em consideração a execução do algoritmo de Bellman-Ford, pois vamos adicionar peso, tempo ou custo denominado 
 conforme a necessidade do projeto, para que, com esse valor possamos determinar o caminho mais curto, ou melhor caminho
 entre um ponto de origem, partida, entre todos os nós ou vértices existentes no grafo.
 
 
# Código

  - O código estrutural foi retirado do conteúdo acadêmico ministrado pelo professor Jasnau Caeiro, em seu guia de aula
  diponibilizado para os alunos.
  O bjetivo dessa parte três é programar a estrutura de dados designada por grafo em linha adjacênte com o algoritmo de 
  Bellman Forda para determinar o caminho mais curto leccionada na disciplina, realizada uma aplicação que recebe uma 
  tabela de N distâncias, sob a forma de números pertencentes ao corpo de reais positivos, utilizando a biblioteca 
  padrão da linguagem de programação Kotlin.
  
  - Ponto importante: O número N deve ser escolhido de modo a que no computador do aluno o tempo de execução do
  algoritmo seja de aproximadamente 600 segundos.
                                                                                                                                                                            
 ```sh                                                                                          
  Grafo - EDA - Estrutura de dados e algoritmo
  
  Vertice a =>  Arestas com -> b -> c -> e - 
  Vertice b =>  Arestas com -> c - 
  Vertice c =>  Arestas com -> b -> d - 
  Vertice d =>  Arestas com -> a -
  Vertice e =>  Arestas com -> b -
 ```  
  - Acima temos o resultado do código elaborado para teste conforme imagem grafo.jpg existente
   no diretório img, grafo feito a mão para criar um raciocínio de uso e poder comprar a efetividade
   do código elaborado até o momento. Podendo ver também pelo imagem listaAdjacente.png obtida 
   diretamente do programa codificador utilizado, a saber, IntelliJ.
   
      
   ```sh
2147483647
109
79
107
82
93
107
Time start: 1592001165939 ms
End time: 1592001233669 ms
Total time: 67730 ms
````
  - Acima temos o resultado do código elaborado, recebendo os valores conforme determinados, tendo os pontos 
  de origem, destino e custo. O numero "2147483647", corresponde a execução do MAX e MIN VALUES, conforme descrição e 
  comentários no código, são os numeros apontados para o infinito dessas funções utilizadas no código.
  Sendo assim toda ve que encontrar esse valor significa que a roda apresentou resultado negativo ao qual o 
  algoritmo de BellmanFord comporta, situação diferente em relação aos outros algoritmos.
  
# Especificando partes do código

  - Nesta etapa chamamos a criação do novo grafo e criação dos vértices para verificação da veracidade
  do código, assim defino a entrada das arestas ponto de origem e ponto de destino.
  Obs: essa primeira parte é base para verificar a adjacência, verificando com poucos valores
  a sequência da lista, se ela se encontra a demonstrar os valores e segmentos corretos.
  
```sh
         //construi arestas e adiciona instâncias à lista de arestas do grafo
                val edges: MutableList<Edge> = ArrayList()

        //vertices criados manualmente
                val a = grafo.addVertice("a")
                val b = grafo.addVertice("b")
                val c = grafo.addVertice("c")
                val d = grafo.addVertice("d")
                val e = grafo.addVertice("e")

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

``` 
  - Uma implementação do algoritmo Bellman-Ford. O algoritmo encontra o caminho mais curto entre
  um nó inicial e todos os outros nós no gráfico, o algoritmo também pode trabalhar com ciclos
  negativos, o custo mínimo para esse nó é definido como MIN_VALUE. 
  
```sh
     //gráfico com vértices e arestas
     internal class Grafo(var nVertices: Int, var edges: List<Edge>)
     //função auxiliar para adicionar aresta ao gráfico
     //aresta direcionada com um custo
     class Edge(var origem: Int, var destino: Int, var custo: Int)

```
- A função do algoritmo Bellman-Ford é avaliar repetidamente as equações
 utilizando os valores da iteração precedente. Os valores d[k] representam 
 os custos ou distâncias dos caminhos mais curtos a partir da origem até qualquer vértice k.
 Os caminhos em si podem ser recuperados através dos valores  auxiliares p[k] 
 também computados no passo 1 do algoritmo. Após a iteração t = 1, todos os valores que 
 dependem diretamente deste, ou seja, aqueles referentes aos vértices apenas 
 1 aresta distante de s ao longo de um caminho ótimo, estarão corretos em definitivo.

```sh
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
```
- A forma de entrada dos valores no algoritmos pode ser dada de inumeras formas, nesse caso temos 
as seguintes entradas de dados que pode ser trabalhados no algoritmo:
Declaração de variaveis gloais para utilização de randons para o numeros dos vertices, assim como para
os numeros de destino e seus respectivos valores de custos para as arestas.

````sh
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
        ........

        //primeiras arestas determinadas como entrada para verificação dos valores
        edges.add(Edge(0, 1, -1))
        .......
````
- Podendo também a entra ser com uma tabela curta conforme as declaradas "edges.add(Edge(0, 1, -1))", demonstrada
no final no modelo código abaixo, ou no inicio desse documento.

````sh
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
        .......
````

- Concluido as formas de entradas, podemos utilizar a leitura de um arquivo txt com os valores determinados
como no nosso caso o aqruivo "EntradaGrafo.txt" que contem 25.320 linhas com os dados em colunas, correspondente
de forma sequêncial a: origem, destino e custo de cada aresta entre esses pontos.

````sh
         //ler o arquivo com os valores de origem, destino e custo respectivamente
         val path = "docs/EntradaGrafo.txt"
         leitor(path)

        //faz a leitura do arquivo com tabela de dados origem, destino e custo
        @Throws(IOException::class)
        fun leitor(path: String?) {
        val buffRead = BufferedReader(FileReader(path))
        var linha: String? = ""
        while (true) {
            if (linha != null) {
                //println(linha)
            } else break
            linha = buffRead.readLine()
        }
        buffRead.close()
    }
````


Ferramentas de uso:
  -  IntelliJ, Git, GitHub, Gnuplot, Google Drive
  -  VirtualBox - Sistema Lunix Ubuntu
  -  OpenCV 4.3.0
  -  Notepad++

Na pasta do projeto, contém todos os documentos gerados, durante a execução do trabalho acadêmico, assim no os arquivos gitgnore, README e as estruturas dos ficheiros conforme PDF de apoio.

> O trabalho deve ser realizado numa pasta designada por EDA-Trabalho-2020. O código
deve residir numa sub-pasta designada por src e a documentação que fôr produzida
deve residir numa sub-pasta designada por docs. Os ficheiros em formato PDF devem
residir numa pasta designada por pdfs.

### Referências e materiais de pesquisa

O material de pesquisa serve como apoio ao conteúdo ministrado em videos aulas, conforme disciplina, tendo em vista as videos aulas, material de apoio da disciplina, referênciamos todos os portais de leitura e consulta para realização do trabalho acadêmico, como forma de demonstrar as bases de aopio para aplicação do conhecimento adquirido.

| Ferramentas | README |
| ------ | ------ |
| Usando o Git e GitHub | [https://www.youtube.com/watch?v=xEKo29OWILE&list=PLHz_AreHm4dm7ZULPAmadvNhH6vk9oNZA&index=1]|
| Leitura e pesquisa | [https://www.ime.usp.br/~pf/algoritmos_para_grafos/aulas/graphdatastructs.html#sec:adjlists] [http://dcm.ffclrp.usp.br/~augusto/teaching/aedii/AED-II-Grafos.pdf] [https://www.youtube.com/watch?v=obWXjtg0L64] [http://wiki.icmc.usp.br/images/e/e9/Grafos_VI.pdf] [https://web.microsoftstream.com/video/0db1aaef-ddb9-4147-85fc-5e03e59e9016?channelId=097ffa61-8e92-4c38-844c-03cdd5edba6d] [https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/compare-to.html] |
| Modelo para código | [Guia prático EDA - ESTIG - Professor Jasnau Carneiro]|

### Controle de versão

  - Fundamental para a gestão sadia e profissional de todo e qualquer trabalho hoje na área de infomática, o controle de versões é extremamente utilizado e existem inumeras ferramentas disponiveis para o uso.
  Neste caso utilizaremos a ferramenta Git e adicionando de forma extra uso o GitHub para complemento de gestão de versões
  Acesso ao repositório através do link abaixo, poderá demonstrar todas as implementações ocorridas.
  Dentro dessa gestão, foi configurado inclusive o IntelliJ a ferramenta para programação, o uso do git, conforme tela. 

 
    [img\gitIntellij.png]

```sh
Poderá clicar no link abaixo para ser direcionado ao repositório do GitHub
https://github.com/haspimenta/EDA-Trabalho-2020
```
Clique aqui [Acesso ao GitHub direto](https://github.com/haspimenta/EDA-Trabalho-2020)
