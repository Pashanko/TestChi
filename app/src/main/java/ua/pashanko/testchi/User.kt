package ua.pashanko.testchi

data class User(
    val name: String,
    val age: Int,
    val birthDate: String,
    var isStudent: Boolean = false
)

