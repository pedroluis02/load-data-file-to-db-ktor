package com.github.pedroluis02.dbdataloading.domain

fun interface DataLoadingRepository {

    fun load(queryResource: String, filePath: String)
}