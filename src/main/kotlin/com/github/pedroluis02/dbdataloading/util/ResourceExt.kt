package com.github.pedroluis02.dbdataloading.util

fun getResourceAsText(path: String): String? =
    object {}.javaClass.classLoader.getResource(path)?.readText()