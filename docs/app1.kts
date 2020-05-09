/*
hello.kt
5 jul 2017
José Jasnau Caeiro
*/
import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.CvType
import org.opencv.core.Scalar

fun main(args: Array<String>){
    println("Hello World")
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME)
    println("Welcome to OpenCV " + Core.VERSION)

// exemplo de compilaçao com OpenCV a partir de Kotlin
// val m : Mat = Mat(5, 10, CvType.CV_8UC1, Scalar(0.0))
    val m : Mat = Mat(5, 10, CvType.CV_64F, Scalar(0.0))
    println("OpenCV Mat: " + m)

    val soma = m[5,1]

    println(soma)

    val mr1 = m.row(1)
    mr1.setTo(Scalar(1.0))
    val mc5 = m.col(5)
    mc5.setTo(Scalar(5.0))
    println("OpenCV Mat data:\n" + m.dump())

    for(k in 1..5){
        val kk = k * 1.0
        println(Math.sqrt(kk))
    }
}