package com.education.universitiesListFeature.presentation.view

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.education.domain.model.remote.University




//    @JvmStatic
//    @BindingAdapter("app:addUniversitiesRecyclerItems","app:addItems")
//    fun addUniversitiesRecyclerItems(
//        recyclerView: RecyclerView,
//        list: List<University>?,
//        items: List<String>?
//    ) {
//        println("UniversitiesBindingAdapters")
//
//        println("emanlistlist")
//        if (list != null) {
//            if (recyclerView.adapter == null) {
//                recyclerView.adapter = UniversitiesAdapter().apply { submitList(list) }
//                recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
//            } else {
//                val adapter = recyclerView.adapter as UniversitiesAdapter
//                adapter.submitList(list)
//            }
//        } else {
//            println("List is null")
//        }
//    }

//    @BindingAdapter("app:itemText")
//    @JvmStatic
//    fun addItems(recyclerView: Tex, items: List<String>?) {
//        items?.let {
//            println("List is null")
////            if (recyclerView.adapter == null) {
////                recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
////                recyclerView.adapter = MyAdapter(it)
////            }
//        }
//    }

    @BindingAdapter("app:addItems")
    fun addItems(recyclerView: RecyclerView, items: List<String>?) {
        items?.let {
            println("List is null")
//            if (recyclerView.adapter == null) {
//                recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
//                recyclerView.adapter = MyAdapter(it)
//            }
        }
    }

//    @BindingAdapter("app:addSettingsCustomSubItem")
//    @JvmStatic
//    fun addSettingsCustomSubItem(
//        viewGroup: ViewGroup,
//        settingsSubItemInterface: SettingsSubItemInterface?
//    ) {
//        settingsSubItemInterface?.let {
//            val view = settingsSubItemInterface.getView(viewGroup.context)
//            (view.parent as ViewGroup?)?.removeAllViews()
//            viewGroup.removeAllViews()
//            viewGroup.addView(view)
//        }
//    }


