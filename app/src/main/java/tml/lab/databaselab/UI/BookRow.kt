package tml.lab.databaselab.UI

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import tml.lab.databaselab.Book
import tml.lab.databaselab.R

class BookRow(context:Context, attrs: AttributeSet)
    :LinearLayout(context, attrs) {
    fun populate(book: Book) {
        findViewById<TextView>(R.id.book_name).text = book.book_name
    }
}