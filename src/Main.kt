
import BucketSort.BucketSort
import java.util.*

class Main {
    val bucketSort: BucketSort = BucketSort()
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            println("EDA - Bucket-Sort")
            val toSort = intArrayOf(80, 50, 30, 10, 90, 60, 0, 70, 40, 20, 50)
            println("Entrada de dados desordenados")
            println(Arrays.toString(toSort))

            println("Saida dos dados ordenado")

            val PrintBuckets: List<*> = listOf(BucketSort())

        }

        //verifica o tamanho da entrada
        private fun toSort(toSort: IntArray) {
            for (i in 0 until toSort.size) {
                val aux = toSort[i]
            }
            val BucketSort = BucketSort(toSort)
        }
    }
}
