/*Hugo Alexandre Silva
nº 18544
ESTIG - IPBEJA
Prof: Jasnau Caeiro
18544@stu.ipbeja.pt
 */
import java.io.FileNotFoundException
import java.util.*

object MainRubroNegro
{
    @Throws(FileNotFoundException::class)
    @JvmStatic
    fun main(args: Array<String>)
    {
        println("ARVORE RED BLACK - EDA - Estrutura de dados e algoritmo\n")

       // val text = "uma árvore rubro-negra é um tipo especial de árvore"
        val text = "Uma árvore rubro-negra é um tipo de árvore binária de busca balanceada, uma estrutura de dados usada em ciência da computação, tipicamente para implementar vetores associativos. A estrutura original foi inventada em 1972, por Rudolf Bayer[carece de fontes] que a chamou de Árvores Binárias B Simétricas"
        val words = text.split("\\s+".toRegex()).toTypedArray()
        val n = words.size

        //Só para testar ordem lexicográfica das palavras
        Arrays.sort(words);

        System.out.println("Texto em ordem lexicográfica\n" + Arrays.toString(words));
        val bs = ArvoreRedBlack(n)

        println("\nARVORE RED BLACK\n")

        //Inserir palavra a palavra na árvore
        for (i in words.indices)
        {
            bs.insert(words[i])
        }
        println("AFTER")
        System.out.println(bs)
    }
}