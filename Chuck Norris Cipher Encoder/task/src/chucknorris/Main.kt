package chucknorris

fun main() {
    menu()
}

fun menu() {
    println("Please input operation (encode/decode/exit):")
    when (val input = readln()) {
        "encode" -> encode()
        "decode" -> decode()
        "exit" -> println("Bye!")
        else -> {
            println("There is no '$input' operation\n")
            menu()
        }
    }
}

fun decode() {
    val list = mutableListOf<String>()
    println("Input encoded string:")
    val input = readln().trim().split(" ").chunked(2)
    var a = 0
    for (i in input) {
        if (i[0].length > 2 || i[0] !in "0".."00" || i.size != 2) {
            print("Encoded string is not valid.")
            a++
            break
        } else {
            if (i[0] == "0") {
                repeat(i[1].length) { list.add("1") }
            } else if (i[0] == "00") {
                repeat(i[1].length) { list.add("0") }
            }
        }
    }
    val out = list.joinToString("").chunked(7)
    if (a == 0 && list.size % 7 == 0 && list.size !in 0..6) {
        println("Decoded string:")
        for (i in out) {
            print(i.toInt(2).toChar())
        }
    } else if (a == 0) print("Encoded string is not valid.")
    println("\n")
    menu()
}

fun encode() {
    var first = 0
    var one = 0
    var zero = 0
    println("Input string:")
    val input = readln()
    println("Encoded string:")
    for (i in input) {
        val t = String.format("%07d", Integer.toBinaryString(i.code).toInt())
        for (j in t) {
            if (j == '0' && first == 0) {
                first = 1
                zero = 1
                print("00 0")
            } else if (j == '0' && zero == 0) {
                zero = 1
                one = 0
                print(" 00 0")
            } else if (j == '0' && zero != 0) {
                one = 0
                print("0")
            } else if (j == '1' && first == 0) {
                first = 1
                one = 1
                print("0 0")
            } else if (j == '1' && one == 0) {
                one = 1
                zero = 0
                print(" 0 0")
            } else if (j == '1' && one != 0) {
                zero = 0
                print("0")
            }
        }
    }
    println("\n")
    menu()
}
