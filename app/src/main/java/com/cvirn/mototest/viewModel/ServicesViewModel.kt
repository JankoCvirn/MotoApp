package com.cvirn.mototest.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.ApolloClient
import com.example.rocketreserver.MobjectsQuery
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ServicesViewModel(private val apolloClient: ApolloClient) : ViewModel() {

    private val _mObjectsList = MutableStateFlow(emptyList<MobjectsQuery.Mobject?>())
    val mObjectsList: StateFlow<List<MobjectsQuery.Mobject?>> = _mObjectsList.asStateFlow()

    private val _mObject = MutableStateFlow(emptyList<MobjectsQuery.Mobject?>())
    val mObject: StateFlow<List<MobjectsQuery.Mobject?>> = _mObject.asStateFlow()

    init {
        viewModelScope.launch {
            val mObjectsQueryResponse = apolloClient.query(MobjectsQuery()).execute()
            mObjectsQueryResponse.data?.let {
                _mObjectsList.value = it.mobjects
            }
        }
    }

    fun findService(id: String) {
        viewModelScope.launch {
            val mObjectsQueryResponse = apolloClient.query(MobjectsQuery()).execute()
            val result = mObjectsQueryResponse.data?.let {
                it.mobjects.find { it?.id == id }
            }
            val resultList = mutableListOf<MobjectsQuery.Mobject>()
            result?.let {
                resultList.add(result)
            }
            _mObject.value = resultList
        }
    }
}
