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

  - O código estrutural foi retirado do conteúdo acadêmico ministrado pelo professor Jasnau Caeiro, em seu guia de aula
  diponibilizado para os alunos.
  O bjetivo dessa parte dois é programar a estrutura de dados designada por árvore de pesquisa binária balanceada
  red-black leccionada na disciplina, realizada uma aplicação que pegue num texto e que coloque na árvore de
  pesquisa binária cada uma das palavras. Deve usar-se uma função de comparação de string existente na biblioteca 
  padrão da linguagem de programação Kotlin.
  
  - Como comparação de string utilizei o ("else if (m!!.key!!.get(z)!!.compareTo(m!!.key!!.get(y).toString()) < 0) //compara ordem lexicográfical"),
  CompareTo, conforme demonstrado nessa linha de código, demais programação necessarias para o reconhecimento do algoritmo para 
  strings também foram realizadas e é estão comentadas no decorrer do código.
  
  -Ponto importante de uso no codigo foi a ação "lexicográfical", a ordenação das palavras conforme sua escrita, até então
  desconhecida da minha parte, porém que ajuda demasiado no entendimento para o balanceamento da árvore.
  EX:
  ```sh
  Texto em ordem lexicográfica
  [de, especial, rubro-negra, tipo, um, uma, árvore, árvore, é]
  ```
  Em matemática, uma ordem lexicográfica, (também conhecida como ordem do dicionário, ordem alfabética ), é uma 
  estrutura de ordem natural do produto cartesiano de dois conjuntos ordenados.
  Dadas dois conjuntos parcialmente ordenados A e B, a ordem lexicográfica sobre o produto cartesiano A x B é 
  definida como (a,b) ≤ (a′,b′) se e somente se a < a′ ou (a = a′ e b ≤ b′).
  O resultado é uma ordem parcial. Se A e B são totalmente ordenados, então o resultado é uma ordem total também.
                                                                                            citação(wikipedia)
                                                                                                                                                                               
 ```sh                                                                                          
  8-> [p = nil, key = uma, left = 6, right = 7]
  7-> [p = 8, key = árvore, left = nil, right = 5]
  6-> [p = 4, key = rubro-negra, left = 3, right = nil]
  5-> [p = 7, key = é, left = nil, right = nil]
  4-> [p = 8, key = um, left = 6, right = nil]
  3-> [p = 4, key = tipo, left = 2, right = 4]
  2-> [p = 3, key = especial, left = 1, right = nil]
  1-> [p = 2, key = de, left = nil, right = nil]
  0-> [p = 5, key = árvore, left = nil, right = nil]
 ```  
  - Sendo assim se analisar a árvore acima veremos que os textos de acordo com a questão
  lexicográfica, bate corretamente, intercalando as cores e correspondendo os valores maiores
  pela direto assim como os menores pela esquerda, além claro de respeitar as demais propriedades 
  do algoritmo da árvore de balanceamento.
  
# Especificando algumas partes do código

  - Nesta chamada dividimos o conteúdo da string de forma a armazenada em uma array
  para que através da ordenação possamos determinar a sequência logica do program, partindo do principio que 
  testei em varios array de menores elementos, passiveis de uma analise visual e de desenho em caderno para
  o devido entendimento e confirmação dos dados.
  
```sh
        val words = text.split("\\s+".toRegex()).toTypedArray()
        val n = words.size

        //Só para testar ordem lexicográfica das palavras
        Arrays.sort(words);
``` 
  - Como não sabemos o tamanho do array ou nesse caso a quantidae para a variavel key de entrada criei um
  "for" para que fosse executado um loop para que enquanto houvesse palavras para inserir, fossem as mesmas contabilizadas
  e adicionadas no array para uso dos dados no código.
  Podendo acompanhar que sempre busco usar pelo menos 2 (duas) entradas diferentes para confirmação
  do execução do código.  
  
```sh
    //Inserir palavra a palavra na árvore
            for (i in words.indices)
            {
                bs.insert(words[i])
            }
            println("AFTER")
            System.out.println(bs)
        }

```
- A função compareTo do Kotlin nos permitir compara esse valor com o valor especificado para o pedido. 
Retorna zero se esse valor for igual ao outro valor especificado, um número negativo se for menor que outro 
ou um número positivo se for maior que outro, assim comparamos a string para definir a ordenação, 
posteriormente dentro do array.

```sh
else if (m!!.key!!.get(z)!!.compareTo(m!!.key!!.get(y).toString()) < 0) //compara ordem lexicográfical
        {
            m!!.left!![y] = z
        }
        else
        {
            m!!.right!![y] = z
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
| Leitura e pesquisa | [https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/compare-to.html][https://docs.oracle.com/javase/8/docs/api/java/lang/String.html][https://pt.wikipedia.org/wiki/%C3%81rvore_rubro-negra][https://stackoverflow.com/questions/40430297/kotlin-idiomatic-way-to-remove-duplicate-strings-from-array] [https://www.ime.usp.br/~pf/estruturas-de-dados/aulas/st-redblack.html]|
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
