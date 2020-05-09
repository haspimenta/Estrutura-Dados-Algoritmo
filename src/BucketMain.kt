/*Hugo Alexandre Silva
nº 18544
18544@stu.ipbeja.pt
 */

import org.opencv.core.Core
import org.opencv.imgcodecs.Imgcodecs
import java.util.*
import kotlin.collections.ArrayList

object BucketMain
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME)
        println("EDA - Bucket-Sort")
        val N_BUCKETS = 100 //numero de buckets
        val toSort = GetPixelValues("img/wallpapers.jpg") //obter o valor 0-255 dos pixeis (brightness)
        //val toSort = GetPixelValues("img/Colored-Grayscale.png") //obter o valor 0-255 dos pixeis (brightness)
        val buckets = BucketSort(toSort, N_BUCKETS) //ordernar os valores em buckets
        PrintBuckets(buckets) //imprimir resultado (test)
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
    private fun PrintBuckets(buckets: ArrayList<ArrayList<Int>>)
    {
        for (i in buckets.indices)
        {
            println("bucket " + i + ": " + Arrays.toString(buckets[i].toArray())); //imprive valores dentro dos buckets
            println("bucket " + i + ": " + buckets[i].size) //imprime tamanho dos buckets
        }
    }
}