package com.decagon.facilitymanagementapp_group_one.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decagon.facilitymanagementapp_group_one.Modelclass
import com.decagon.facilitymanagementapp_group_one.model.DummyComments

class CommentsViewModel: ViewModel() {

    private var _comments = MutableLiveData<ArrayList<Modelclass>>()
    val comments = _comments

    fun loadComment() {
        _comments.value = DummyComments.modelclass
    }

    fun addComments(comment: Modelclass) {
        _comments.value?.add(0, comment)
    }

}