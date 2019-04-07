package com.example.retrofitexamplekotlin

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.retrofitexamplekotlin.adapter.AnswersAdapter
import com.example.retrofitexamplekotlin.data.model.Item
import com.example.retrofitexamplekotlin.data.model.SOAnswerResponse
import com.example.retrofitexamplekotlin.data.remote.ApiUtils
import com.example.retrofitexamplekotlin.data.remote.SOService

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var service : SOService? = null
    var recyclerView : RecyclerView? = null
    var adapter : AnswersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        service = ApiUtils.getSOService()
        recyclerView = findViewById<RecyclerView>(R.id.rv_answers)
        adapter = AnswersAdapter(
            this,
            ArrayList<Item>(0),
            object : AnswersAdapter.PostItemListener {
                override fun onPostClick(id: Long) {
                    Toast.makeText(this@MainActivity, "Post id is " + id, Toast.LENGTH_SHORT).show()
                }
            })
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter
        recyclerView?.setHasFixedSize(true)
        var itemDecoration : RecyclerView.ItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST)
        recyclerView?.addItemDecoration(itemDecoration)


        loadAnswers()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun loadAnswers() {
        Log.d("MainActivity", "Load Answers")
        service!!.getAnswers().enqueue(object : Callback<SOAnswerResponse> {
            override fun onResponse(call: Call<SOAnswerResponse>?, response: Response<SOAnswerResponse>?) {
                if (response!!.isSuccessful) {
                    Log.d("MainActivity", "posts loaded from API")
                    adapter?.updateAnswers(response.body().items)
                } else {
                    val statusCode : Int? = response?.code()
                }
            }

            override fun onFailure(call: Call<SOAnswerResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.d("MainActivity", "error loading from API")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
