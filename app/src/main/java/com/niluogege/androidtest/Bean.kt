package com.niluogege.androidtest

open class Bean {
    var aaa: String? = ""
    var bbb: Int? = 0
    var ccc: CCC? = null

    override fun toString(): String {
        return "Bean(aaa=$aaa, bbb=$bbb, ccc=$ccc)"
    }

    fun getAAA(): String {
        return aaa ?: "null.."
    }

    //kotlin方法默认为final的
    open fun getBBB(): Int {
        return bbb ?: 0
    }

    fun getCCC():CCC{
        return CCC()
    }


}

class CCC {
    var ddd: String? = ""
    var eee: Int? = 0

    override fun toString(): String {
        return "CCC(ddd=$ddd, eee=$eee)"
    }
}