package tml.lab.databaselab

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

import kotlinx.android.synthetic.main.activity_book_manager.*
import kotlinx.android.synthetic.main.content_book_manager.*
import tml.lab.databaselab.UI.BookRow

class BookManagerActivity : AppCompatActivity() {
    lateinit var driver: SqlDriver
    lateinit var db : BookLibDB
    lateinit var queries : BooklibQueries

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("DBG", "BookManagerActivity::onCreate")

        setContentView(R.layout.activity_book_manager)
        setSupportActionBar(toolbar)

        driver = AndroidSqliteDriver(
            BookLibDB.Schema, this, "BookLibDB.db")
        db = BookLibDB(driver)
        queries = db.booklibQueries

        fetchData2UI()

        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
        }
    }

    private fun fetchData2UI() {
        Log.d("DBG", "fetch Data UI...")
        lvBooks.adapter = BookListAdapter(
            queries.selectAll().executeAsList()
        ) { books ->
            Toast.makeText(this@BookManagerActivity,
                "Open book ${books.book_name}", Toast.LENGTH_SHORT).show()
        }

        lvBooks.layoutManager = LinearLayoutManager(this)
    }

    private inner class BookListAdapter(
        private val data: List<Book>,
        private val clickListener: (Book) -> Unit
    ) : RecyclerView.Adapter<BookListAdapter.ViewHolder>() {

        override fun getItemCount() = data.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                layoutInflater.inflate(R.layout.book_row, parent, false) as BookRow
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.setBook(data[position])
        }

        inner class ViewHolder(val row: BookRow) : RecyclerView.ViewHolder(row) {
            fun setBook(b: Book) {
                row.populate(b)
                row.setOnClickListener { clickListener(b) }
            }
        }
    }
}
