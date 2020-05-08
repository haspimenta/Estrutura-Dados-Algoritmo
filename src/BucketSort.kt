import java.util.*
import javax.print.attribute.IntegerSyntax
import kotlin.collections.ArrayList

class BucketSort {
    object BucketSort {
        @JvmStatic
        fun bucketSort(toSort: IntArray, nBuckets: Int): Array<ArrayList<*>?> {

            //iniciar buckets
            var buckets: Array<ArrayList<*>?> = arrayOfNulls(nBuckets)
            for (i in buckets.indices) {
                buckets[i] = ArrayList<Int>()
            }

            //encontrar o valor máximo do array para ordenar
            var max = 0
            for (i in toSort.indices) {
                if (toSort[i] > max) {
                    max = toSort[i]
                }
            }

            //encontrar divisor
            //var divisor = Math.ceil((max + 1.0) / nBuckets).toInt()

            //distribuir itens pelos buckets
            for (i in toSort.indices) {
                var nBucketDest = Math.floor(toSort[i] / (max + 1.0) * nBuckets).toInt()
                buckets[nBucketDest]!!.add(toSort[i])
            }

            //Aplicar insertion sort (ordenar bucket por bucket)
            for (i in buckets.indices) {
                InsertionSort(buckets[i] as ArrayList<Int>)
            }
            return buckets
        }

        private fun InsertionSort(bucket: ArrayList<Int>) {
            for (n in bucket.indices) {
                var j = n
                var temp = bucket[n].toDouble()
                while (j > 0 && bucket[j - 1] > temp.toInt()) {
                    bucket[j] = bucket[j - 1]
                    j--
                }
                bucket[j] = temp.toInt()
            }
        }

        fun PrintBuckets(buckets: Array<ArrayList<*>>){
            println("Bucket Ordenado")
            for (i in buckets.indices) {
                println("Bucket" + i + ": " + Arrays.toString(buckets[i].toTypedArray()))
                //System.out.println("bucket " + i + ": " + buckets[i].size());
            }
        }
    }
}