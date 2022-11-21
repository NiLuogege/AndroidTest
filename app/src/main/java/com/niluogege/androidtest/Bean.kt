package com.niluogege.androidtest

open class Bean {
    var aaa : String? = ""
    var bbb : Int? = 0
    var ccc : CCC?=null

    override fun toString(): String {
        return "Bean(aaa=$aaa, bbb=$bbb, ccc=$ccc)"
    }


}

class CCC {
    var ddd : String? = ""
    var eee : Int? = 0

    override fun toString(): String {
        return "CCC(ddd=$ddd, eee=$eee)"
    }
}