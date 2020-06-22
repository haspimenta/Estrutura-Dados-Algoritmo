import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs
import java.io.FileWriter
import java.io.IOException
import java.util.*

object ConcorrenciaParalelismo {
    private const val X_STEP = 192 //Limitação em X
    private const val Y_STEP = 108 //Limitação em Y

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME)
        println("EDA - Corrência e Paralelismo")
        //Carregar imagem usando a classe mat em biblioteca - exemplo de duas imagens para testar resultados diferentes
        val img = Imgcodecs.imread("img/153687-uhd.jpg", Imgcodecs.IMREAD_ANYCOLOR) //FULL UHD 3840*2160
        //val img = Imgcodecs.imread("img/uhd3840x2160.jpg", Imgcodecs.IMREAD_ANYCOLOR) //FULL UHD 3840*2160
        val nCols = img.width() / X_STEP //numero de colunas
        val nRows = img.height() / Y_STEP //numero de linhas
        val results = ArrayList<Result>() //instanciar lista para guardar os resultados
        var id = 0 //identificador da thread
        //Processar cada bloco da imagem
        for (col in 0 until nCols)  //varrimento das colunas
        {
            for (row in 0 until nRows)  //varrimento das linhas
            {
                //efetua a soma para dividir os blocos
                val x = X_STEP * col
                val y = Y_STEP * row
                id++ //incrementa numero de thread
                val bp = BlockProcessor(img, x, y, X_STEP, Y_STEP, id, results) //criamos uma instância de um blockProcessor
                val thread = Thread(bp) //instanciar thread
                thread.start() //inicia a thread
                try {
                    thread.join()
                } catch (ie: InterruptedException) {
                    println("Ocorreu um erro ao processar o bloco: $id")
                }
                PrintResults(results)
            }
        }
    }
    //função para imprimir resultados guardados na lista
    @Throws(IOException::class)
    private fun PrintResults(results: ArrayList<Result>) {
        val csvWriter = FileWriter("docs/ImagemRGB.csv")
        csvWriter.append("ID;M(R);M(G);M(B);DP(R);DP(G);DP(B)")
        for (i in results.indices) {
            csvWriter.append("\n")
            csvWriter.append(results[i].id.toString() + ";")
            csvWriter.append(results[i].avgR.toString() + ";")
            csvWriter.append(results[i].avgG.toString() + ";")
            csvWriter.append(results[i].avgB.toString() + ";")
            csvWriter.append(results[i].dpR.toString() + ";")
            csvWriter.append(results[i].dpG.toString() + ";")
            csvWriter.append(results[i].dpB.toString())
        }
        csvWriter.flush()
        csvWriter.close()
    }
    //Objecto para guardar resultado do processamento de UM bloco
    internal class Result(val id: Int, val avgR: Int, val avgG: Int, val avgB: Int, val dpR: Double, val dpG: Double, val dpB: Double)
    //Classe para processamento de um bloco
    internal class BlockProcessor
    (var img: Mat, var x: Int, var y: Int, var xStep: Int, var yStep: Int, var id: Int, var results: ArrayList<Result>) : Runnable {
        var time = 0.0
        var csvWriter: FileWriter? = null
        //Processamento, bloco, médias e desvios padrão
        @Throws(IOException::class)
        private fun Process() {
            var rSum = 0
            var gSum = 0
            var bSum = 0
            var nPixeis = 0
            for (col in x until x + xStep)  //varrimento dos pixeis
            {
                for (row in y until y + yStep)
                {
                    //obter valores RGB do pixel
                    val r = img[row, col][2]
                    val g = img[row, col][1]
                    val b = img[row, col][0]
                    //somar valores RGB
                    rSum += r.toInt()
                    gSum += g.toInt()
                    bSum += b.toInt()
                    //contador de pixeis processados
                    nPixeis++
                }
            }
            //Encontrar a média (soma dividida pelo total)
            val avgRSum = rSum / nPixeis
            val avgGSum = gSum / nPixeis
            val avgBSum = bSum / nPixeis
            //Encontrar desvios padrão
            var rI = 0
            var gI = 0
            var bI = 0
            for (col in x until x + xStep)  //varrimento dos pixeis
            {
                for (row in y until y + yStep) {
                    val r = img[row, col][2]
                    val g = img[row, col][1]
                    val b = img[row, col][0]

                    //Encontrar o desvio padrao
                    rI += Math.sqrt(r - avgRSum).toInt()
                    gI += Math.sqrt(g - avgGSum).toInt()
                    bI += Math.sqrt(b - avgBSum).toInt()
                }
            }
            //Último passo para encontrar desvio padrao
            val dpR = rI / nPixeis.toDouble()
            val dpG = gI / nPixeis.toDouble()
            val dpB = bI / nPixeis.toDouble()
            //adicionar à lista de resultados
            val res = Result(id, avgRSum, avgGSum, avgBSum, dpR, dpG, dpB)
            results.add(res)
        }
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
}