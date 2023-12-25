package com.example.languagekurs

class Notes(var noteId: String? = null, var openT: String? = null,
            var closeT: String? = null, var descT: String? = null) {
    companion object Factory {
        fun create(): Notes = Notes()
    }
}