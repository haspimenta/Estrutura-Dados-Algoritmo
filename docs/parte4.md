# Estrutura de Dados e Algoritmo

Seguindo o enunciado trabalho encontra-se dividido em 4 partes que correspondem às divisões:
na disciplina:
1. algoritmos de ordenação e análise da complexidade computacional dos algoritmos;
2. estruturas de dados simples: conjuntos dinâmicos, hash-tables e árvores de pesquisa binária;
3. grafos e algoritmos de: pesquisa simples; de geração de árvores de envergadura
mínima e de pesquisa de caminhos mais curtos;
4. introdução à concorrência e paralelismo na algoritmia.

#Concorrência e paralelismo

    - Concorrência, paralelismo, processos, threads, programação síncrona e assíncrona, são assuntos que permeiam 
    o dia a dia dos desenvolvedores. O denominado "processo" pode ser visto como um container de recursos utilizados 
    por uma ou mais tarefas. Processos são isolados entre si (inclusive, através de mecanismos de proteção a nível 
    de hardware), não compartilham memória, possuem níveis de operação e quais chamadas de sistemas podem executar.
    Os processos podem ter uma série de threads associadas e as threads de um processo são conhecidas como threads 
    de usuário, por executarem no modo-usuário e não no modo-kernel. Uma thread é uma “linha” de execução dentro de 
    um processo. Cada thread tem o seu próprio estado de processador e a sua própria pilha, mas compartilha a memória 
    atribuída ao processo com as outras threads “irmãs” (filhas do mesmo processo).
    
    “Concurrency is about dealing with lots of things at once. Parallelism is about doing lots of things at once.”
    Rob Pike – linguagem Go 
    
    Concorrência é sobre lidar com várias coisas ao mesmo tempo e paralelismo é sobre fazer várias coisas ao mesmo 
    tempo. Concorrência é um conceito mais a nível de software e paralelismo mais a nível de hardware. Concorrência é 
    sobre a execução sequencial e disputada de um conjunto de tarefas independentes. Paralelismo é sobre a execução 
    paralela de tarefas, ou seja, mais de uma por vez (de forma simultânea), a depender da quantidade de núcleos 
    (cores) do processador.
                                                                                                    (treinaweb.com.br)
                                                    
- A linguagem de programação Kotlin suporta a programação com threads multi-núcleo o que
  permite acelerar em muitos casos os algoritmos que operam sobre imagens. Abaixo segue um modelo de código com thread
  para entendermos o processo, a thread é declada e podemos dizer que cada thread tem um contrato com a função start()
  que é para inicia-lá e com run() que executa a chamada das operações ou ações da classe em questão.
```sh
        public void calculaTotalRecebido(){
          new Thread() {
            @Override
            public void run() {
              //Recebe aproximadamente 70mil registros. 
              List<HistoricoRecebimento> recebidos = getListRecebimentos();
              Integer soma = 0;
              for(HistoricoRecebimento h1: recebidos){
                soma = soma + recebidos.getValorRecebido();}
              Integer porcentagemImposto = getReajusteAtualFromWebService();
              soma = soma + ((porcentagemImposto/100)*soma);
              retornaParaWebServiceValorTotal(soma);}
          }.start();
```
        
 - A máquina virtual de Java (JVM)1 é responsável pela gestão de linhas concorrentes de
   execução designadas por threads. Na linguagem de programação Java as classes que representam estas thread são Thread
   e Runnable. A classe Thread declara vários construtores destinados á inicialização destes objetos. 
   Alguns destes construtores exigem objetos do tipo Runnable como argumento.
 
 
# Código

  - O código estrutural foi retirado do conteúdo acadêmico ministrado pelo professor Jasnau Caeiro, em seu guia de aula
  diponibilizado para os alunos.
  Complementado pelos portais web, pdf e documentação kotlin, citados e declarados nas referências de leituras e pesquisa
  no final do documento.
  Cada parte do código esta comentado para que possa ser melhorado o entendimento do seu funcionamento.
                                                                                                                                                                            
 ```sh                                                                                          
  private static final int X_STEP = 192; 
  private static final int Y_STEP = 108; 
//Carregar imagem usando a classe mat em biblioteca - exemplo de duas imagens para testar resultados diferentes
val img = Imgcodecs.imread("img/153687-uhd.jpg", Imgcodecs.IMREAD_ANYCOLOR) //FULL UHD 3840*2160
//val img = Imgcodecs.imread("img/uhd3840x2160.jpg", Imgcodecs.IMREAD_ANYCOLOR) //FULL UHD 3840*2160int nCols = img.width() / X_STEP; 
  int nRows = img.height() / Y_STEP; 
 ```  
  - Acima temos o numero de colunas determinado para a divisão (em blocos) X_Step e Y_Step - (divisão dos píxeis 
  totais da largura pela largura do limitador/bloco). O Mat Imgcodecs é uma biblioteca para trabalhar com imagnes 
  nas plataformas (JVM), e os nRows são as linhas, numero de linhas (em blocos) para no proximo passo usarmos um ciclo 
  "for" para percorres as dimensões da imagem e obtermos os valores prentendidos.
  Existe duas imagens a serem utilizadas para afim de demonstrar resultdos diferentes, basta descomentar uma e comentar a outra.
   
      
   ```sh
int id = 0; //identificador da thread
        //Processar cada bloco da imagem
        for (int col = 0; col < nCols; col++) //varrimento em toda a largura (blocos)
        {
            for (int row = 0; row < nRows; row++) //varrimento vertical (blocos)
            {
                //efetua a soma para dividir os blocos
                int x = X_STEP * col; 
                int y = Y_STEP * row; 
                id++; //incrementa numero de thread
                BlockProcessor bp = new BlockProcessor( img, x, y, X_STEP, Y_STEP, id, results ); //criamos uma instância de um blockProcessor
                Thread thread = new Thread( bp ); 
                thread.start(); //inicia a thread
                try
                {
                    thread.join(); //adiciona a thread ao processamento paralelo
                } catch (InterruptedException ie)
                {
                    System.out.println( "Ocorreu um erro ao processar o bloco: " +  id);
                }
                PrintResults(results);
            }
        }
````
  - Acima temos o x_step recebe o limitador de coluna multiplicando pelo numero de coluna, ex: 192*1 valor 
  primeira coluna - 192*2 valor segunda coluna, o y_step recebe o limitador de linha multiplicando pelo 
  numero de linhas, ex: 108*1 valor primeira linha - 108*2 valor segunda linha e assim sucessivamente até concluir
  o varrimento total da imagem conforme "for" declarado acima, que percorre a coluna e a linha efetuando os incrementos.
  O "new thread" serve para instanciar thread e enviar a instância do blockProcessor para a thread correr (função run).
  
# Especificando partes do código

  - Nesta etapa chamamos a criação do novo grafo e criação dos vértices para verificação da veracidade
  do código, assim defino a entrada das arestas ponto de origem e ponto de destino.
  Obs: essa primeira parte é base para verificar a adjacência, verificando com poucos valores
  a sequência da lista, se ela se encontra a demonstrar os valores e segmentos corretos.
  
```sh
    //Classe para processamento de um bloco
        static class BlockProcessor implements Runnable {
            Mat img;
            int x, y, xStep, yStep, id;
            double time;
            FileWriter csvWriter;
            ArrayList<Result> results;
            //Processamento do bloco, médias dos canais e desvios padrão
            private void Process() throws IOException {
                int rSum = 0;
                int gSum = 0;
                int bSum = 0;
                int nPixeis = 0;
                for (int col = this.x; col < this.x + xStep; col++)//varrimento dos pixeis
                {
                    for (int row = this.y; row < this.y + yStep; row++) 
                    {
                        //obter valores RGB do pixel
                        double r = img.get( row, col )[2];
                        double g = img.get( row, col )[1];
                        double b = img.get( row, col )[0];
                        //somar valores RGB 
                        rSum += r;
                        gSum += g;
                        bSum += b;
                        //contador de pixeis processados
                        nPixeis++;
                    }
                }
                //Encontrar a média
                int avgRSum = rSum / (nPixeis);
                int avgGSum = gSum / (nPixeis);
                int avgBSum = bSum / (nPixeis);
                //Encontrar desvios padrão
                int rI = 0;
                int gI = 0;
                int bI = 0;
                for (int col = this.x; col < this.x + xStep; col++) //varrimento dos pixeis
                {
                    for (int row = this.y; row < this.y + yStep; row++)
                    {
                        double r = img.get( row, col )[2];
                        double g = img.get( row, col )[1];
                        double b = img.get( row, col )[0];
                        //Encontrar o desvio padrao 
                        rI += Math.sqrt(r - avgRSum);
                        gI += Math.sqrt(g - avgGSum);
                        bI += Math.sqrt(b - avgBSum);
                    }
                }
                //Último passo para encontrar desvio padrao
                double dpR = rI / nPixeis;
                double dpG = gI / nPixeis;
                double dpB = bI / nPixeis;
                //adicionar à lista de resultados uma nova instância de objectos do tipo resultado
                Result res = new Result(id, avgRSum, avgGSum, avgBSum, dpR, dpG, dpB);
                this.results.add(res);
            }
``` 
  - Uma implementação da classe BlockProcessor irá fazer o chamamento e processamento do bloco, levando em consideração
  a partição/quebra anunciado pelo enunciado acadêmico, a saber, x_step e y_step. Runnable deve ser implementada por 
  qualquer classe cujas instâncias sejam executadas por um encadeamento. A classe deve definir um método sem 
  argumentos chamado run, no nosso caso a thread fará essa papel.
  O primeiro "for" irá fazer o varrimento dos pixeis (dentro dos limites do bloco), obtendo od pixels encontrados 
  para no final encontrar a média.  "int avgRSum = rSum / (nPixeis)" (soma dividida pelo total), assim obtemos a 
  média.
  O segundo "for" vamos fazer o varrimento dos pixeis, para usar na média do desvio padrão. Conteudo de conhecimento e
  formula para o calculo do desvio padrão estão nas referências. O primeiro resultado vamos encontrar o desvio padrao 
  mas apenas o dividendo, "rI += Math.sqrt(r - avgRSum)", onde o rI irá rceber o valor da média RGB de forma individual
  e eleva a raiz quadrada, a segunda parte do desvio padrão, "double dpR = rI / nPixeis;" é obter o resultado da primeira
  parte e dividir pelo numero toal de pixels existente no imagem.
  
```sh
        //construtor da classe
        public BlockProcessor(Mat img, int x, int y, int xStep, int yStep, int id, ArrayList<Result> results) throws IOException {
            this.img = img;
            this.x = x;
            this.y = y;
            this.xStep = xStep;
            this.yStep = yStep;
            this.id = id;
            this.results = results;
        }
        //função que a thread corre
        public void run()
        {
            try {
                this.Process();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

```
- A função do construtor da classe citada acima é aceita uma imagem e os limites do bloco para processar que já 
foram determinados anteriormente.
img - imagem para processar
x - começo da coordenada horizontal
y - começo da coordenada vertical
xStep - limite horizontal
yStep - limite vertical (do bloco)
Como mencionado anteriormente todo thread, necessitará um processo Runnable e a função run(), ao qual determinei que 
que chame ou faça menção a classe "Process" assim executando de forma paralela as operações e ações existente dentro
da classe.

```sh
//Objecto guardar processamento bloco
    static class Result
    {
        private int avgR, avgG, avgB;
        private double dpR, dpG, dpB;
        private int id;
        public int getId() {
            return id;
        }
        public int getAvgR() {
            return avgR;
        }
        public int getAvgG() {
            return avgG;
        }
        public int getAvgB() {
            return avgB;
        }
        public double getDpR() {
            return dpR;
        }
        public double getDpG() {
            return dpG;
        }
        public double getDpB() {
            return dpB;
        }
        public Result(int id, int avgR, int avgG, int avgB, double dpR, double dpG, double dpB)
        {
            this.id = id;
            this.avgR = avgR;
            this.avgG = avgG;
            this.avgB = avgB;
            this.dpR = dpR;
            this.dpG = dpG;
            this.dpB = dpB;
        }
    }
```
- A forma encontrada para imprimir foi a de criar um objeto para guardar o resultado do processamento de UM bloco por
todo. Sendo assim a cada bloco varrido e executado será guardado nessa classe objeto através dos operadores e "get".
Optei por essa solução depois de pesquisar pela comunidades e ver que o problema que estava tendo em relação a escrita
"Writer", "File" de forma sequencial, aguardava apenas a ultima linha, ou seja, conforme era executado, o programa 
estava escrevendo e logo depois substituindo o conteúdo no buffering. Assim essa solução encontrada no stack overflow
orientou o armazenamento dos valores da médias, desvio padrão, id de identificação do bloco.

````sh
 ArrayList<Result> results = new ArrayList<Result>(); //instanciar lista

    //função para imprimir resultados guardados na lista
       public static void PrintResults(ArrayList<Result> results) throws IOException {
           FileWriter csvWriter = new FileWriter("docs/ImagemRGB.csv");
           csvWriter.append("ID;M(R);M(G);M(B);DP(R);DP(G);DP(B)");
           for (int i=0; i < results.size(); i++) {
               csvWriter.append( "\n" );
               csvWriter.append( String.valueOf( results.get( i ).id ) + ";" );
               csvWriter.append( String.valueOf( results.get( i ).avgR ) + ";" );
               csvWriter.append( String.valueOf( results.get( i ).avgG ) + ";" );
               csvWriter.append( String.valueOf( results.get( i ).avgB ) + ";" );
               csvWriter.append( String.valueOf( results.get( i ).dpR ) + ";" );
               csvWriter.append( String.valueOf( results.get( i ).dpG ) + ";" );
               csvWriter.append( String.valueOf( results.get( i ).dpB ) );
           }
           csvWriter.flush();
           csvWriter.close();
       }
````
- Como demonstrado no código acima, o conteúdo guardado é iestanciado por uma arralist a fim de salvar todos os
resultados em formato de lista como solicitado pelo enunciado. Após guardar os valores na classe de obejeto Result, 
fiz usso da classe PrintResults para ordenar os valores e gravar no ficheiro conforme demanda de lista.
Usei o FileWrite para escrever no ficheiro, lembrando que apenas consegui trabahar com os RBG de maneira individual, 
obtendo um de forma simultanea e por vez conforme processo de trhead, ao cluir limpamos a cache e fechamos o arquivo.

````sh
       import org.opencv.core.Core;
       import org.opencv.core.Mat;
       import org.opencv.imgcodecs.Imgcodecs;
       import java.io.FileWriter;
       import java.io.IOException;
       import java.util.ArrayList;
````

- Concluido as formas de entradas, segue bibliotecas utilizadas para a execução dessa etapa de processamento e conteúdo 
da cadeira de EDA. Utilização do opencv em plataforma linux, sistema opertaivo "Ubuntu" e demais bibiliotecas de uso 
também para o Java, em questão, a maquina (JVM) e seus comportes.
- Finalizando a parte de código, foi implementado o contador "startTime" para iniciar e marcar o tempo em 
que o "process" foi iniciado e com o "endTime" e "elapsedTime", posso determinar o tempo final de processamento
e o tempo gasto por bloco e mostra-lo em tela, conforma coódigo abaixo.

````sh
     //função que a thread corre
            override fun run() {
                val startTime = System.currentTimeMillis()//inicializa a contagem do tempo de execução
                try {
                    Process()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                val endTime = System.currentTimeMillis() //finaliza o tempo de execuçao do algoritmo
                val elapsedTime = endTime - startTime //calculo do tempo tortal de execução do algoritmo
                //Imprime resultado do tempo de execução
                println("Time start: " + startTime + " ms" + "\n" + "End time: " + endTime + " ms")
                print("Total time: " + elapsedTime + " ms")
            }
        }
````


Ferramentas de uso:
  -  IntelliJ, Git, GitHub, Gnuplot, Google Drive
  -  VirtualBox - Sistema Lunix Ubuntu
  -  OpenCV 4.3.0
  -  Notepad++
  -  PhotoShop

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
| Leitura e pesquisa | [https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.concurrent/] [https://www.treinaweb.com.br/blog/concorrencia-paralelismo-processos-threads-programacao-sincrona-e-assincrona/] [http://www.dsc.ufcg.edu.br/~jacques/cursos/map/html/threads/implementacao.html] [https://www.todamateria.com.br/desvio-padrao/?fbclid=IwAR3zrKz3BLr7LoP37zCZbldUZ7CD7iIvmDBqd5Ok5MWv8AqjjdWB74EO7-U] [http://wiki.icmc.usp.br/images/e/e9/Grafos_VI.pdf] [https://cpp.dokry.com/11950/c-valores-rgb-negativos-de-pixeles-utilizando-opencv.html?fbclid=IwAR2AvZWkNRuQXg3qLWp-XSYPFiRj27-IxWNqU3puZfj_vuUNG3xJK3n6DSI] [https://codare.aurelio.net/2007/03/15/java-como-ler-editar-e-salvar-imagens-imageio/?fbclid=IwAR1OI4Dvoh7s1FoatzlwWWg22RYJdpxqX-Vh1hH0sGj9O_Mi98j-Z3Txb-k][https://www.caelum.com.br/download/caelum-java-objetos-fj11.pdf] |
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
