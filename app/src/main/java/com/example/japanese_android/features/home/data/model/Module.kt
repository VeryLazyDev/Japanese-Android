package com.example.japanese_android.features.home.data.model

import androidx.compose.ui.graphics.Color

data class Module(val title: String, val desc: String, val icon: String, val color: Color)

val HomePageModules = listOf(
    Module(
        "READING", """
                         Improve your reading
                         comprehension with texts
                         tailored for your level
                         """, "読", Color(0xFFA5F3FC)
    ),
    Module(
        "KANJI", """
                         Learn, review and master 
                         kanji meanings,
                         readings, and usage
                         """, "字", Color(0xFFF5D0FE)
    ),
    Module(
        "GRAMMAR", """
                         Learn grammar patterns 
                         with practical examples
                         """, "文", Color(0xFF99F6E4)
    ),
    Module(
        "LISTENING", """
                        Develop listening skills
                        for your upcoming test
                         """, "聞", Color(0xFFFBCFE8)
    ),Module(
        "VOCABULARY", """
                        Build your word bank
                        with spaced repetition
                         """, "言", Color(0xFFFEF08A)
    ),

    )