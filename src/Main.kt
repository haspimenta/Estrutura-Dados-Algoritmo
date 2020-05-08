
import BucketSort.BucketSort.bucketSort
import java.util.*

class Main {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            println("EDA - Bucket-Sort")
            var toSort = intArrayOf(80, 50, 30, 10, 90, 60, 0, 70, 40, 20, 50)
            println("Entrada de dados desordenados")
            println(Arrays.toString(toSort))
            input(toSort)
        }

        //verifica o tamanho da entrada
        fun input(toSort: IntArray) {
            for (i in 0 until toSort.size) {
                toSort[i]
            }
            val nBuckets = 10
            bucketSort(toSort, nBuckets)
        }
    }
}
