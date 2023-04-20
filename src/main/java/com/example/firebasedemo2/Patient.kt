package com.example.firebasedemo2

class Patient() {
    private var name: String? = null
    private var age = 0
    private var city: String? = null
    constructor(name: String?, age: Int, city: String?) : this() {
        this.name = name
        this.age = age
        this.city = city
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getAge(): Int {
        return age
    }

    fun setAge(age: Int) {
        this.age = age
    }

    fun getCity(): String? {
        return city
    }

    fun setCity(city: String?) {
        this.city = city
    }

}