import java.io.FileNotFoundException

object MainRubroNegro {
    @Throws(FileNotFoundException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val text = "uma árvore rubro-negra é um tipo especial de árvore"
        val words = text.split("\\s+".toRegex()).toTypedArray()
        val n = words.size

        //Só para testar ordem lexicográfica das palavras
//        Arrays.sort(words);
//        System.out.println(Arrays.toString(words));
        val bs = ArvoreRubroNegro(n)
        println("ARVORE RED BLACK")

        //Inserir palavra a palavra na árvore
        for (i in words.indices) {
            bs.insert(words[i])
        }
        println("AFTER")
        System.out.println(bs)
    }
}