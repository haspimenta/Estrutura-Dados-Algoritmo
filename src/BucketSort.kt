import java.util.*

class BucketSort {
    object BucketSort {
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
            var divisor = Math.ceil((max + 1.0) / nBuckets).toInt()

            //distribuir itens pelos buckets
            for (i in toSort.indices) {
                val nBucketDest = Math.ceil(toSort[i] / divisor.toDouble()).toInt()
                buckets[nBucketDest]?.add(toSort[i] as Nothing)
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

        public fun PrintBuckets(buckets: Array<ArrayList<*>>){
            println("Bucket Ordenado")
            for (i in buckets.indices) {
                println("Bucket" + i + ": " + Arrays.toString(buckets[i].toTypedArray()))
                //System.out.println("bucket " + i + ": " + buckets[i].size());
            }
        }
    }
}


/*

import kotlin.collections.ArrayList

class BucketSort {
    object BucketSort {
        fun bucketSort(input: IntArray): IntArray {
            val ints = divBcs(input)
            val buckets: Array<MutableList<*>?> = arrayOfNulls(ints[0])

            for (i in 0 until ints[0]) {
                buckets[i] = ArrayList<Any?>()
            }

            for (i in input) {
                buckets[divBcs(i, ints)]!!.add(input[i] as Nothing)
               //buckets[divBcs(i, ints)]?.add(buckets.size, input[i] as Nothing)
            }//java.lang.IndexOutOfBoundsException : Invalid array index: 80 -- a cada valor verificado

            for (bucket in buckets) {
                val mutableList = bucket
                mutableList?.shuffled()
            }

*/
/*
             for (int i : input) {
            buckets[divBcs(i, start)].add(i);
            }//java.lang.IndexOutOfBoundsException : Invalid array index: 80 -- a cada valor verificado

            for (List bucket : buckets) {
            Collections.sort(bucket);
            }
           *//*



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
            for (i in 0 until input.size) {
                if (m < input[i]) {
                    m = input[i]
                }
            }
            return intArrayOf(m, Math.ceil(input.size.toDouble()).toInt())
        }

        private fun divBcs(i: Int, ints: IntArray): Int {
            return (i.toDouble() / ints[0] * (ints[0] - 1)).toInt()
        }
    }
}
*/
