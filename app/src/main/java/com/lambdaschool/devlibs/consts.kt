package com.lambdaschool.devlibs

// For passing the auth token to the controllers
// this could probably get deleted
//const val AUTH_STRING_KEY = "MAGICWORDS"



//fake ol mock fake datas
// somet of these things could probably just get filled out
// more better data

const val tempMadlibCategory = "a Category"
val CATEGORIES = arrayOf<String> (
        "category 1",
        "category 2",
        "category 3"


)
val tempWordNeeds = listOf<Array<String>>(
      //cat 1
        arrayOf("Noun", "Verb","Noun","Verb","Adjective"),
     //cat 2
        arrayOf("Adjective", "Noun", "Verb","Verb","Noun","Adjective", "Noun"),
    //cat 3
        arrayOf("Verb","Adjective","Noun", "Verb","Noun","Noun"))


//what is an adjective... descriptive word? maybe

val tempTemplatesToInject = listOf<List<String>>(
        //cat 1
        listOf("Noun Verb Noun Adjective Adjective Noun Adjective Noun ",
                " VerbNoun Verb Noun Adjective Adjective Noun Adjective Noun ",
                " Noun Verb Noun Adjective Adjective Noun Adjective Noun ",
                " Verb ",
                " AdjectiveVerb Noun Adjective Adjective"),

        //cat 1
        listOf("Noun Verb Noun Adjective Adjective Noun Adjective Noun ",
                " Noun Verb Noun Adjective Adjective Noun Adjective Noun ",
                " VerbNoun Verb Noun Adjective Adjective Noun Adjective Noun ",
                " VerbNoun Verb Noun Adjective Adjective Noun Adjective Noun ",
                " Verb",
                " Verb",
                " Noun"),
        //cat 1
        listOf("Noun Verb Noun Adjective Adjective Noun Adjective Noun "
                ," Verb ",
                " VerbNoun Verb Noun Adjective Adjective Noun Adjective Noun ",
                " VerbNoun Verb Noun Adjective Adjective Noun Adjective Noun ",
                " VerbNoun Verb Noun Adjective Adjective Noun Adjective Noun ",
                " Noun"))

