
import BucketSort.BucketSort
import java.util.*
import kotlin.Array

class Insertion {
    var bucketSort = BucketSort()

    companion object {
        lateinit var input: IntArray

        @JvmStatic
        fun main(args: Array<String>) {
            println("EDA - Bucket-Sort")
            val input = intArrayOf(80, 50, 30, 10, 90, 60, 0, 70, 40, 20, 50)
            println("Entrada de dados desordenados")
            println(Arrays.toString(input))

            //insertionSort(input)

            println("Saida dos dados ordenado")
            println(Arrays.toString(BucketSort.bucketSort(input)))
        }

        //verifica o tamanho da entrada
        private fun insertionSort(input: IntArray) {
            for (i in 0 until input.size) {
                val aux = input[i]
                BucketSort.bucketSort(input)
            }
        }
    }
}
