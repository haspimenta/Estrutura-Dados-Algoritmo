/*Hugo Alexandre Silva
nº 18544
18544@stu.ipbeja.pt
 */

nesta função o objetivo é criar o arraylist de pixels da imagem utilizada, fazendo um for para linhas e colunas,
para não estourar o tamanho do array na condição subtraimos 1, mas poderá também ser inicialuizado a contagem em 1,
em vez de 0, mas para isso devemos estar atentos a estrutura usada em todo código para não perdermos dados e obter
erro de estrutura durante o processo.
/*
 private fun GetPixelValues(path: String): ArrayList<Int> {
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

*/


Para efeteuar o insertion usei um conjunto de dados que contém mais de 2 milhões
de números inteiros aleatórios que serão criados conforme a imagem escolhida com intervalo de 1 a 2.073.600,
que corresponde a uma imagem com resolução 1920x1080.
Cada bucket irá receber os valores conforme a troca e execução de n e j para o temp sendo guardados no bucket.
/*
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
*/


A distribuição dos valores nos buckets, ocorre de uma forma concisa, ou seja, para qualquer imagem escolhida o
algoritmo deve distribuir de uma forma uniforme os valores entre os buckets.
Para isso ao calcular a divisão pretende que seja feito um "arredondamento", por isso a operação com o math.floor,
para saber em qual bucket o valor será adicionado.
Exemplo: ao dividir 10/4 temos 2,5, como trabalhamos com numeros inteiros de buckets, não podemos ter 2,5 buckets,
assim temos que colocar o valor no backet 2.
Além é claro que ao definir o numero de array, também controla a situação de não estouramos a estrutura do array,
passando o seu numero de posições.
/*
for (i in toSort.indices)
        {
            val nBucketDest = Math.floor(toSort[i] / (max + 1.0) * nBuckets).toInt()
            buckets.get(nBucketDest).add(toSort[i])
        }
*/



Referências e materiais de pesquisa:

Usando o Markdown
https://blog.da2k.com.br/2015/02/08/aprenda-markdown/
notepad++

Algoritmos de estrutura Bucket e Insertion Sort
Material disponibilizado pelo professor 
Wikipedia com pseudo-código
https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/to-int-array.html

Verificando a estrutura e pixels/histogram da imagem
https://www.dcode.fr/image-histogram?fbclid=IwAR0dYbtXDky_pHS9vulIfmqz1EJCeCNT37tWIX1d3sl1wB_hZfmeG02VwGc