package com.ncorti.kotlin.template.app

fun getNameErrorMessage(name: String): String? {
    if (name.isEmpty()) {
        return "Name cannot be empty"
    }

    return null
}

fun getUsernameErrorMessage(username: String): String? {
    if (username.isEmpty()) {
        return "Username cannot be empty"
    }

    return null
}

fun getPasswordErrorMessage(password: String): String? {
    if (password.isEmpty()) {
        return "Password cannot be empty"
    }
    return null
}

fun getEmailErrorMessage(email: String): String? {
    if (email.isEmpty()) {
        return "Email cannot be empty"
    }

    val emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex()
    if (!emailRegex.matches(email)) {
        return "Email is not valid"
    }

    return null
}

fun getReEnteredPasswordErrorMessage(password: String, reEnteredPassword: String): String? {
    if (password != reEnteredPassword) {
        return "Passwords do not match"
    }

    return null
}