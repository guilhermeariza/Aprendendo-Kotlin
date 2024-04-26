package org.example

import MyClassJ
import TrainingJava
import java.io.BufferedOutputStream
import java.io.FileOutputStream

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

/*
* COMANDOS PARA RAPIDEZ
*
* Ctrl + B -> Achar implementação/origem/declaração
* Ctrl + Enter -> Adicionar linha abaixo, tudo à direita do cursor vai pra linha debaixo e cursor se mantém
* Shift + Enter -> Adicionar linha abaixo e mover cursor junto
* Ctrl - E -> Passear entre os arquivos
* Alt + 0/1/2/3/4/5/6/7/8/9 -> atalhos para sidebar esquerda
*
* */



fun main() {

//    listExamples()
//    castexample()
//    nullsafetyExampleComparingKotlinAndJava()
//    collectionFPExample()
//    println(collectionFPExampleModoKotlinFilter())
//    println(collectionFPExampleModoKotlinMap())
//    collectionFPExampleModoKotlinFilterMap()
//    println(collectionFPExampleModoKotlinFilterMap())
//    println(exampleFlatMapWithoutMap())
    useExample()
}

fun listExamples(){
    val listS = listOf("x", "y") //Imutável
    val list1 = listOf(1, 2, 3) // Imutável
    //list1.add(4) n pode isso
    val list2 = mutableListOf(1, 2, 3) //Mutável
    list2.add(4)
    val list3 = arrayListOf(1, 2, 3) // Mutável
    list3.add(4)

    val list: List<Int> = arrayListOf(1, 2, 3)
    //list.add(4) n pode pq list nao usa add
    //Tipo na compilação e tipo interno são diferentes

    val arrayList = ArrayList<Int>() //Kotlin -> idêntico ao do java mas é nativo, fácil de trasnferir para outra linguagem
    arrayList.add(1)
    arrayList.add(3)
    arrayList.add(2)

    val arrayListJava = java.util.ArrayList<Int>() //Java -> não usar
    arrayListJava.add(1)
    arrayListJava.add(3)
    arrayListJava.add(2)

    /*Usar majoritariamente o list1, list2 e list3*/

    //operador no kotlin é syntax sugar
    list1 + listOf(4,5) //Use este
    list1.plus(listOf(4,5)) //Não usa esse, mas é a mesma coisa

    val list6 = list1.toList() //virou List<Int>
    val list7 = list3.toList() //virou List<Int>

    val list8 = mutableListOf(5).toList() //virou List<Int>

    println(list::class)
    println(list1::class)
    println(list2::class)
    println(list3::class)
    println(arrayList::class)
    println(arrayListJava::class)
    println(list6::class)
    println(list7::class)
    println(list8::class)

    (list6 as MutableList<Int>).add(3) //Internamente é List e em compilação é ArrayList, então é permitido burlar a regra do add usando o 'as' para fazer o casting
    println(list6)
    println(list6::class)

    // (list8 as MutableList<Int>).add(3) isso dá erro e não compila

    castexample()
}

fun castexample(){
    val l = mutableListOf(1)
    val l2 = l as List<Int>

    val n = 5L

    //val ni: Int = (n as Int) + 2 n roda
    //Diferença entre coersão(parse) e casting
    //Casting é ver uma coisa de outra forma
    //Coersão(Parse) é transformar uma coisa em outra

    val ni: Int = n.toInt() + 2
}

fun nullsafetyExample() {
    // val -> Imutável
    // var -> Mutável

    val x = 1
    // x = 2 n pode pois não é mutável

    var y = 1
    y = 2 //mutável

}

fun nullsafetyExampleComparingKotlinAndJava() {
    add(4, 5)

    val tj = TrainingJava()

    tj.addPrimitive(4 ,5)

    tj.add(4,5)
    tj.add2(4,5)

    //val mc = MyClass(1,null)
    val mc: MyClass? = buildMyClass()
    val mcj = MyClassJ(1,null)

    add(mc!!.i1, mc.i2)

    if(mc != null){
        add(mc.i1, mc.i2)
    }

    val j1 = mcj.i1

    tj.add(mc?.i1, mc?.i2)

    /*
    Vai somar coisa nula doido?
    Kotlin -> erro na chamada
    add(mc.i1, mc.i2)
    tj.addPrimitive(mc.i1, mc.i2)
    tj.add2(mc.i1, mc.i2)

    Java -> erro em execução
    add(mcj.i1, mcj.i2)
    tj.addPrimitive(mcj.i1, mcj.i2)
    tj.add2(mcj.i1, mcj.i2)
    */

    /*
    Elvis ?:   -> quando nulo, atribui tal valor, no caso 0 -> MyClassNullable
    add(mc.i1 ?: 0, mc.i2?:0)
    tj.addPrimitive(mc.i1 ?: 0, mc.i2?:0)
    tj.add2(mc.i1 ?: 0, mc.i2?:0)
    */
}

private fun buildMyClass() = null //MyClass(3, 4)

//class MyClassNullable(val i1: Int?, val i2: Int?) //Essa linha é EXATAMENTE a mesma coisa da MyClassJ do java

class MyClass(val i1: Int, val i2: Int)

fun add(n1: Int, n2: Int): Int{
    return n1 + n2
}

fun collectionFPExample(){
    val l = listOf("adfadsf", "adsjfasdf", "a", "dsfs", "xxx" )

    val l2 = mutableListOf<String>()

    for(item in l){
        if(item.length > 3){
            l2.add(item)
        }
    }
    println(l2)
}

fun collectionFPExampleModoKotlinFilter(): List<String>{
    val l = listOf("adfadsf", "adsjfasdf", "a", "dsfs", "xxx" )
    return l.filter { item ->
        item.length < 3
    }
}
//QUANDO USAR FOREACH E QUANDO USAR MAP

//Use o Map para transformar cada elemento da lista em outro elemento único
//Use o ForEach quando o intuito for PERCORRER uma lista

fun collectionFPExampleModoKotlinMap(): List<Int>{
    val l = listOf("adfadsf", "adsjfasdf", "a", "dsfs", "xxx" )
    return l.map { item ->
        item.length
    }
}
fun collectionFPExampleModoKotlinFilterMap(){
    val l = listOf("adfadsf", "adsjfasdf", "a", "dsfs", "xxx" )
    val l2 = l.filter { item ->
        item.length > 3
    }.map {
        it.length
    }
    println(l2)
}

fun exampleFlatMapWithoutMap(): List<Int> {
    val listOfLists = listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9))
    return listOfLists.flatMap { it }
}

//map, filter, find, any, all, none, flatMap

/*
* find =  achar elemento na lista com alguma caracteristica
* any = algum item da lista bate com algum criterio
* all = todos itens da listad bate c alg criteriro
* none = inverso do all
* flatMap = transforma uma lista de listas em apenas uma lista, por isso o "Flat" no nome
* max = elemento de maior valor
* min = elemento de menor valor
*/

fun useExample(){
    BufferedOutputStream(FileOutputStream("C:\\Users\\MURALIS\\Downloads\\saida.txt")).use{ out ->
        out.write("aooo\n".toByteArray())
    }
    //dando certo ou dando pau, o use sempre vai fechar oq for Cloeseable, tipo o BufferedOutPutStream q tem o .close()
}
