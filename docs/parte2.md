# Estrutura de Dados e Algoritmo
EDA - ESTIG
Hugo Alexandre Silva
nº 18544
18544@stu.ipbeja.pt

Seguindo o enunciado trabalho encontra-se dividido em 4 partes que correspondem às divisões da matéria leccionada na disciplina:
1. algoritmos de ordenação e análise da complexidade computacional dos algoritmos;
2. estruturas de dados simples: conjuntos dinâmicos, hash-tables e árvores de pesquisa binária;
3. grafos e algoritmos de: pesquisa simples; de geração de árvores de envergadura
mínima e de pesquisa de caminhos mais curtos;
4. introdução à concorrência e paralelismo na algoritmia.

#Árvore binária balanceada

    - A primeira árvore binária de pesquisa com balanceamento foi proposta
      por Adel'son-Vel'skii e Landis (1962), dois matemáticos russos, a qual recebeu
      o nome de árvore AVL. Uma árvore binária de pesquisa é uma árvore
      AVL se a altura da subárvore à esquerda de cada nodo nunca difere de ± 1 da
      altura da subárvore à direita.
                                                    (ebook - projetos algoritmos implementaçao Pascal e C)
                                                    
- Importante salientar as propriedades que constitui uma ávore rubro-negra:
```sh
        Um nó é vermelho ou preto
        A raiz é preta
        Todas as folhas (nil) são pretas
        Ambos os filhos de todos os nós vermelhos são pretos
        Todo caminho de um dado nó para qualquer de seus nós folhas descendentes contém o mesmo número de nós pretos.
```
        
 - Baseando nestas propriedades os algoritmos a seguir deverão exercer um balanceamento da árvore como um todo.
 A soma do nós sendo maior que o filho seguirá a estrutura apontada para direta, sendo menor segue para esquerda, quando o valor inserido
 corresponder a mesma cor sequencial é realizado a rotação ou para esquerda ou para direita, afim de reorganizar a árvore, trazendo a 
 sequência do balancemanto para ambos os lados da árvore, podendo inclusive redefir a raiz da árvore, trabalhando em visão de um triângulo através 
 das arestas ou mesmo em linha reta, porém sempre tendo as propriedades da árvore respeitadas.
 

# Código

  - Importante salientar o código para uso:
    - Algumas partes estarão comentadas, mas não é por questoes de erro e sim por questões de uso.
    Temos duas partes importantes nessa sequência do BucketSort, a primeira é uma execução com o arrayrandom, numeros aleatorios com a funcionalidade de testar a execução e a taxa de crescimento do mesmo. A segunda parte, temos a entrada de dados, valores, através de imagens "coloquei duas imagens para uso", com essas imagens vamos gerar um arraylist com mais de 2 milhoes de valores, por isso a importância de comentar a parte que vai usar no momento para não executar ambas.
    Todo o código foi divido em funções ou metodos, separados, buscando aplicar a melhor prática de programação e dentro dos meus campos de conhecimento e pesquisa.
    - Todo o código esta devidamente comentado, com o descritivo para que serve cada função e ações especificas.

# Especificando algumas partes do código

  - Nesta função o objetivo é criar o arraylist de pixels da imagem utilizada, fazendo um for para linhas e colunas, para não estourar o tamanho do array na condição subtraimos 1, mas poderá também ser inicialuizado a contagem em 1,em vez de 0, mas para isso devemos estar atentos a estrutura usada em todo código para não perdermos dados e obter erro de estrutura durante o processo.
 
```sh
private fun GetPixelValues(path: String): ArrayList<Int>
 {
        val img = Imgcodecs.imread(path, Imgcodecs.IMREAD_GRAYSCALE) //FULL HD 1920*1080
        val values = ArrayList<Int>()
        for (r in 0 until img.rows() - 1) {
            for (c in 0 until img.cols() - 1) {
               val temp = img[r, c]
                values.add(temp[0].toInt())
            }
        }
        return values
    }
``` 
  - Para efeteuar o insertion usei um conjunto de dados que contém mais de 2 milhões
de números inteiros aleatórios que serão criados conforme a imagem escolhida com intervalo de 1 a 2.073.600,
que corresponde a uma imagem com resolução 1920x1080. Cada bucket irá receber os valores conforme a troca e execução de n e j para o temp sendo guardados no bucket.
```sh
    //criando a array de pixels da imagem inserida, através de linhas e colunas
    private fun GetPixelValues(path: String): ArrayList<Int>
    {
        val img = Imgcodecs.imread(path, Imgcodecs.IMREAD_GRAYSCALE) //FULL HD 1920*1080
        val values = ArrayList<Int>()
        for (r in 0 until img.rows() - 1)
        {
            for (c in 0 until img.cols() - 1)
            {
                val temp = img[r, c]
                values.add(temp[0].toInt())
            }
        }
        return values
    }

private fun InsertionSort(bucket: ArrayList<Int>)
    {
        for (n in bucket.indices)
        {
            var j = n
            val temp = bucket[n]
            while (j > 0 && bucket[j - 1] > temp)
            {
               bucket[j] = bucket[j - 1]
                j--
            }
            bucket[j] = temp
        }
    }
```
- A distribuição dos valores nos buckets, ocorre de uma forma concisa, ou seja, para qualquer imagem escolhida o algoritmo deve distribuir de uma forma uniforme os valores entre os buckets. Para isso ao calcular a divisão pretende que seja feito um "arredondamento", por isso a operação com o math.floor, para saber em qual bucket o valor será adicionado. Exemplo: ao dividir 10/4 temos 2,5, como trabalhamos com numeros inteiros de buckets, não podemos ter 2,5 buckets, assim temos que colocar o valor no backet 2. Além é claro que ao definir o numero de array, também controla a situação de não estouramos a estrutura do array, passando o seu numero de posições.
```sh
for (i in toSort.indices)
        {
            <p>val nBucketDest = Math.floor(toSort[i] / (max + 1.0) * nBuckets).toInt()
            <p>buckets.get(nBucketDest).add(toSort[i])
        }
```
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
| Usando o Markdown | [https://blog.da2k.com.br/2015/02/08/aprenda-markdown/][https://docs.pipz.com/central-de-ajuda/learning-center/guia-basico-de-markdown#open] |
| Usando o Gnuplot | [https://label2.tecnico.ulisboa.pt/IC/HowTo/HowTo_GnuPlot.php][https://blog.pantuza.com/tutoriais/como-criar-histogramas-com-gnu-plot] |
| Bucket e Insertion Sort | [Material disponibilizado pelo professor][Wikipedia com pseudo-código] [https://www.guj.com.br/search?q=bucketsort][https://www.guj.com.br/search?q=insertionsort][https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/to-int-array.html][https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/BitSet.html#valueOf(byte)]|
| Verificando a estrutura e pixels/histogram da imagem | [https://www.dcode.fr/image-histogram?fbclid=IwAR0dYbtXDky_pHS9vulIfmqz1EJCeCNT37tWIX1d3sl1wB_hZfmeG02VwGc] |
| Usando o Git e GitHub | [https://www.youtube.com/watch?v=xEKo29OWILE&list=PLHz_AreHm4dm7ZULPAmadvNhH6vk9oNZA&index=1]|


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
