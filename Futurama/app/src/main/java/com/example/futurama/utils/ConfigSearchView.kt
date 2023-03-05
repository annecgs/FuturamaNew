package com.example.futurama.utils

import com.example.futurama.data.dto.FuturamaCharactersItem
import java.util.*

class ConfigSearchView {
    companion object {

        fun FilterListQuery(text: String?, list: List<FuturamaCharactersItem>): MutableList<FuturamaCharactersItem> {
            var newList: MutableList<FuturamaCharactersItem> = ArrayList()
            list.forEach {
                if (it.name.first!!.contains(text.toString())
                ) {
                    newList.add(it)
                }
            }
            return newList
        }


    }
}