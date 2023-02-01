package com.example.mygames

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Games.newInstance] factory method to
 * create an instance of this fragment.
 */
class Games : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerView: RecyclerView
    lateinit var adapter: GameAdapter
    private lateinit var emptyAdapter: EmptyPageAdapter
    private lateinit var searchView: android.widget.SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Games.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Games().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val emptyMessage: ArrayList<String> = ArrayList<String>()
        emptyMessage.add("No game has been searched")
        searchView = view.findViewById(R.id.search_bar)

        recyclerView = view.findViewById(R.id.gameRecyc)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        adapter = context?.let { GameAdapter(it, tempList) }!!
        recyclerView.adapter = adapter
        emptyAdapter = context?.let { EmptyPageAdapter(it, emptyMessage) }!!
        initListeners(adapter)
    }

    private fun initListeners(adapter: GameAdapter) {

        searchView.setOnQueryTextListener(object: android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                tempList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                recyclerView.adapter = adapter
                if (searchText.length >= 3){
                    gameList.forEach {
                        if (it.title.toLowerCase().contains(searchText)){
                            tempList.add(it)
                        }
                    }
                    if (tempList.isEmpty()){
                        recyclerView.adapter = emptyAdapter
                    }

                    adapter.notifyDataSetChanged()
                }else{
                    tempList.clear()
                    tempList.addAll(gameList)
                    adapter.notifyDataSetChanged()
                }
                return false
            }
        })
    }
}