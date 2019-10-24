package com.lambdaschool.devlibs

// For passing the auth token to the controllers
// this could probably get deleted
//const val AUTH_STRING_KEY = "MAGICWORDS"



//fake ol mock fake datas
// somet of these things could probably just get filled out
// more better data

const val tempMadlibCategory = "a Category"
val CATEGORIES = arrayOf<String> (
        "choose a category",
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

//templates should have a begining and end, and words needed should be one less than the # of lines in a template

val tempTemplatesToInject = listOf<List<String>>(
        //cat 1
        listOf("Noun Verb Noun Adjective Adjective Noun  ",
                " VerbNoun Verb Noun Adjective Adjective ",
                " Noun Verb Noun Adjective Adjective  ",
                " Verb noun verb ",
                " AdjectiveVerb Noun Adjective verb ",
                " AdjectiveVerb Noun "),

        //cat 1
        listOf("Noun Verb Noun Adjective Adjective Noun ",
                " Noun Verb Noun Adjective Noun Adjective Noun ",
                " VerbNoun Verb  Noun Adjective Noun ",
                " VerbNoun Verb Noun Noun Adjective Noun ",
                " Verb noun verb ",
                " Verb adjective ",
                " adjective verb Noun ",
                " AdjectiveVerb Noun Adjective Adjective"),
        //cat 1
        listOf("Noun Verb Noun Adjective Adjective Noun Adjective Noun "
                ," Verb ",
                " VerbNoun Verb NounNoun Adjective Noun ",
                " VerbNoun Verb Noun Adjective Noun Adjective Noun ",
                " VerbNoun Verb Noun  Adjective Noun Adjective Noun ",
                " Noun verb ",
                " AdjectiveVerb Noun Adjective Adjective"))

