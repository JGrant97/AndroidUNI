package com.example.library

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable

data class Book(
    val author: String = "",
    val title: String = "",
    val ISBN: String = "",
    val synopsis: String = "",
    val coverImage: String = "") : Serializable {

    companion object {
        fun getBooks(filename: String, context: Context): ArrayList<Book> {
            //create ArrayList of Book objects
            val bookList = ArrayList<Book>()

            try {
                //read json file
                val inputStream = context.assets.open(filename)
                val buffer = ByteArray(inputStream.available())
                inputStream.read(buffer)
                inputStream.close()

                val json = JSONObject(String(buffer, Charsets.UTF_8))
                val books = json.getJSONArray("books")

                //extract strings from the JSON objects
                //create new Book objects and add them to the List
                for (i in 0..(books.length() - 1)) {
                    bookList.add(Book(
                        books.getJSONObject(i).getString("Author"),
                        books.getJSONObject(i).getString("Title"),
                        books.getJSONObject(i).getString("ISBN"),
                        books.getJSONObject(i).getString("Synopsis"),
                        books.getJSONObject(i).getString("CoverImage")))
                }


            } catch (e: JSONException) {
                e.printStackTrace()
            }

            //return the ArrayList of Book objects
            return bookList


        }
    }


}
