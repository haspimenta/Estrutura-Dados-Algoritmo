
import org.omg.CORBA.Object
import java.util.*
import kotlin.collections.ArrayList
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

class BucketSort {
    object BucketSort {
        fun bucketSort(input: IntArray): IntArray {
            val ints = divBcs(input)
            val buckets: Array<MutableList<*>?> = arrayOfNulls(ints[1])

            for (i in 0 until ints[1]) {
                buckets[i] = ArrayList<Any?>()
            }

            for (i in input) {
                //buckets[divBcs(i, ints)]?.add(i as Nothing)
               buckets[divBcs(i, ints)]?.add(buckets.size, input[i] as Nothing)
            }//java.lang.IndexOutOfBoundsException : Invalid array index: 80 -- a cada valor verificado

            for (bucket in buckets) {
                val mutableList = bucket
                mutableList?.shuffled()
            }

            /*
             for (int i : input) {
            buckets[divBcs(i, start)].add(i);
            }//java.lang.IndexOutOfBoundsException : Invalid array index: 80 -- a cada valor verificado

            for (List bucket : buckets) {
            Collections.sort(bucket);
            }
           */

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

        private fun divBcs(i: Int, ints: IntArray): Int {
            return (i.toDouble() / ints[0] * (ints[1] - 1)).toInt()
        }
    }
}