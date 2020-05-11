/*Hugo Alexandre Silva
nº 18544
ESTIG - IPBEJA
Prof: Jasnau Caeiro
18544@stu.ipbeja.pt
 */

import org.opencv.core.Core
import org.opencv.imgcodecs.Imgcodecs
import java.io.FileNotFoundException
import java.io.IOException
import java.io.PrintWriter
import java.io.UnsupportedEncodingException
import java.util.*
import kotlin.collections.ArrayList

object BucketMain
{
    private const val N_BUCKETS = 100 //numero de buckets
    private const val N_SAMPLES = 100 //numero de amostras
    private const val DEFAULT_SAMPLE_SIZE = 1000 //tamanho standard da amostra (incrimentada a cada iteração)

    @JvmStatic
    @Throws(InterruptedException::class, IOException::class)
    fun main(args: Array<String>)
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME)
        println("EDA - Bucket-Sort\n")

        //Testar algoritmo Bucket Sort com amostras de tamanho incrementado com arrayrandom
        //TestBucketSort(N_SAMPLES, DEFAULT_SAMPLE_SIZE)

        //temos duas imagens como modelo de entrada, para testar, basta comentar uma e descomentar a outra.
        val toSort = GetPixelValues("img/wallpapers.jpg") //obter o valor 0-255 dos pixeis (brightness)
        //val toSort = GetPixelValues("img/Colored-Grayscale.png") //obter o valor 0-255 dos pixeis (brightness)

        val buckets = BucketSort(toSort, N_BUCKETS) //ordernar os valores em buckets
        PrintBuckets(buckets) //imprimir resultado (test)
    }

    //criar um array random para gerar valores, testando até o numero de amostras para taxa de crescimento
    @Throws(FileNotFoundException::class, UnsupportedEncodingException::class)
    private fun TestBucketSort(nSamples: Int, nSampleSize: Int): Unit
    {
            val writer = PrintWriter("docs/TaxaCrescimento.txt", "UTF-8")

            for (i in 0 until nSamples)
            {
                val testSort = java.util.ArrayList<Int>()
                val sampleSize = nSampleSize * (i + 1)

                for (x in 0 until sampleSize)
                {
                    testSort.add(Random().nextInt(255))
                }

                val startTime = System.currentTimeMillis()
                BucketSort(testSort, nSampleSize)

                val endTime = System.currentTimeMillis()
                val elapsedTime = endTime - startTime

                println("Amostra com " + testSort.size + "  valores - tempo: " + elapsedTime + " ms")
                writer.print("${testSort.size} $elapsedTime\n")
            }
        writer.close()
    }

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

    //iniciando o algoritmo bucketsort com os devidos passos
    private fun BucketSort(toSort: ArrayList<Int>, nBuckets: Int): ArrayList<ArrayList<Int>>
    {
        //iniciar buckets
        val buckets = ArrayList<ArrayList<Int>>()
        for (i in 0 until nBuckets)
        {
            buckets.add(ArrayList<Int>())
        }

        //encontrar o valor máximo do array para ordenar
        var max = 0
        for (i in toSort.indices)
        {
            if (toSort[i] > max)
            {
                max = toSort[i]
            }
        }

        //distribuir itens pelos buckets
        for (i in toSort.indices)
        {
            val nBucketDest = Math.floor(toSort[i] / (max + 1.0) * nBuckets).toInt()
            buckets.get(nBucketDest).add(toSort[i])
        }

        //aplicar algoritmo insertion sort a cada bucket
        for (i in buckets.indices)
        {
            InsertionSort(buckets[i]) //as ArrayList<Int>)
        }
        return buckets
    }

    //aplicando o insertion a cada bucket com a sua devida ordenação
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

    //imprimindo os resultados obtidos
    @Throws(IOException::class)
    private fun PrintBuckets(buckets: ArrayList<ArrayList<Int>>): Unit
    {
            val writer = PrintWriter("docs/Histogram.txt", "UTF-8")
            for (i in buckets.indices)
            {
                //println("bucket $i: " + Arrays.toString(buckets[i].toTypedArray())) //imprime valores dentro dos buckets
                println("bucket " + i + ": " + buckets[i].size) //imprime tamanho dos buckets
                writer.print("$i ${buckets[i].size}\n") //Balde : Tamanho
            }
            writer.close()
    }
}