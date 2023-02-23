package com.example.myapplication

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class DisplayViewModel : ViewModel()
{
    val words = mutableListOf<hangManWord>()
    var viewIndex=0
    var state=0
    var success=false
    private var pressedButtons = mutableListOf<String>()

    init {
        words+=hangManWord("APPLE","fruit(apple)")
        words+=hangManWord("SCISSOR","tool(scissor)")
        words+=hangManWord("PANDA","animal(panda)")
        words+=hangManWord("NOODLE","food(noodle)")
    }

    fun gethint():String
    {
        return words[viewIndex].hint
    }

    fun getword():String
    {
        var word:String=words[viewIndex].word
        var tmpword=""
        for (i in word.indices)
        {
            if (isPressed(word[i].toString()))
                tmpword+=(word[i]+" ")
            else tmpword+="_ "
        }
        return tmpword
    }

    fun renew()
    {
        success=false;
        state=0;
        var newIndex= Random.nextInt(words.size)
        while (newIndex==viewIndex) {
            newIndex= Random.nextInt( words.size)
        }
        viewIndex=newIndex
        pressedButtons.clear()
    }


    fun isPressed(s:String):Boolean
    {
        if (s in pressedButtons)
            return true
        return false
    }

    fun press(s:String)
    {
        if (!isPressed(s))
            pressedButtons.add(s)
        var word:String=words[viewIndex].word
        if (s[0] !in word)
        {
            state++
            return
        }
        success=true
        for (i in word.indices)
        {
            if (!isPressed(word[i].toString()))
            {
                success=false
                break
            }
        }
    }


}