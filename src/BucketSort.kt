import kotlin.collections.ArrayList
import kotlin.collections.*

class BucketSort {
    object BucketSort {
        fun bucketSort(input: IntArray): IntArray {
            val bucketsort = divBcs(input)
            val buckets: Array<MutableList<*>?> = arrayOfNulls(bucketsort[1])
            for (i in 0 until bucketsort[1]) {
                buckets[i] = ArrayList<Any?>()
            }
            for (i in input) {
                buckets[divBcs(i, bucketsort)]//?.add(i as Nothing)
            } //java.lang.IndexOutOfBoundsException : Invalid array index: 80 -- a cada valor verificado

            for (bucket in buckets) {
                intArrayOf().sort()
            }

            var posicao = 0
            for (n in buckets.indices) {
                for (v in buckets[n]!!) {
                    input[posicao++] = v as Int
                }
            }
            return input
        }

        private fun divBcs(input: IntArray): IntArray {
            var m = input[0]
            for (i in 1 until input.size) {
                if (m < input[i]) {
                    m = input[i]
                }
            }
            return intArrayOf(m, Math.sqrt(input.size.toDouble()).toInt())
        }

        private fun divBcs(i: Int, bucketsort: IntArray): Int {
            return (i.toDouble() / bucketsort[0] * (bucketsort[1] - 1)).toInt()
        }
    }
}