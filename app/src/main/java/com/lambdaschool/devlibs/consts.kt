package com.lambdaschool.devlibs

// For passing the auth token to the controllers
// this could probably get deleted
//const val AUTH_STRING_KEY = "MAGICWORDS"


//for sending devlibs to vieweditfragment
const val SEND_DEV_LIB ="moremagicwords"


//fake ol mock fake datas
// somet of these things could probably just get filled out
// more better data

const val tempMadlibCategory = "a Category"
val CATEGORIES = arrayOf<String> (
        "choose a Madlib",
        "Architecture",
        "Security",
        "Breathless tech sales"


)
val tempWordNeeds = listOf<Array<String>>(
      //"Architecture"
        arrayOf("Noun", "Verb","Noun","Verb","Adjective"),
     //"Software"
        arrayOf("Software technology (noun)",
                "\"Cool\" Approach or Feature (noun)",
                "Response (stop, detect, etc)",
                "Type of threat (noun)","Adverb","Action you don't want to do(Verb,)",
                "Action you'd like to do more of (verb)"),
    //"Hardware"
        arrayOf("Technology (Proper Noun) ","Technoglogic solution (noun)","Brand", "Acronym (serrvice)","Technology (noun)","Adverb"))



//what is an adjective... descriptive word? maybe

//templates should have a begining and end, and words needed should be one less than the # of lines in a template

val tempTemplatesToInject = listOf<List<String>>(
        //lib 1
        listOf("Noun Verb Noun Adjective Adjective Noun  ",
                " VerbNoun Verb Noun Adjective Adjective ",
                " Noun Verb Noun Adjective Adjective  ",
                " Verb noun verb ",
                " AdjectiveVerb Noun Adjective verb ",
                " AdjectiveVerb Noun "),

        //lib 2
        listOf(" we are changing how CISOs do ",
                " by incorporating ",
                " so that you can",
                " any ",
                " faster with more context. With us, you ",
                " remove wasted time doing ",
                " and increase your ability to ",
                " 10x better resulting in huge improvements to your workflow"),
        //lib 3
        listOf("We do",   //1
                " for clients like you.", //2
                " Tom, We offer a  ", //3
                " based on ", //4
                " that connects with your ", //5
                " and makes it ", //6
                " and allows you to leverage unheard of productivity!")) //7


