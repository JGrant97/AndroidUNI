package com.example.library

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.libary.MainActivity
import com.example.libary.R

import kotlinx.android.synthetic.main.list_view_book.view.*

class BookAdapter(private val context: MainActivity, private val books: ArrayList<Book>) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_view_book, parent, false))
    }
    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bookTitle.text = books.get(position).title
        holder.bookAuthor.text = books.get(position).author
        holder.bookISBN.text = books.get(position).ISBN
        holder.bookSynopsis.text = books.get(position).synopsis
        val resourceId = context.resources.getIdentifier("@drawable/" + books.get(position).coverImage,"drawable", context.packageName)
        holder.bookCover.setImageResource(resourceId)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, books.get(position).title, Toast.LENGTH_LONG).show()
        }
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookTitle = view.txtTitle
        val bookAuthor = view.txtAuthor
        val bookISBN = view.txtISBN
        val bookSynopsis = view.txtSynopsis
        val bookCover = view.imgCoverImage
    }
}
