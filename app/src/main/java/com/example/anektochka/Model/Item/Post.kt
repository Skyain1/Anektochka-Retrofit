package com.example.anektochka.Model.Item

data class Post(
    val type : String,
    val setup: String,
    val punchline: String,
    val id : Int,
    val ViewType : Int = 2
)
