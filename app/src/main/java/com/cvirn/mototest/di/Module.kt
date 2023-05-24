package com.cvirn.mototest.di

import com.cvirn.mototest.repo.apolloClient
import com.cvirn.mototest.viewModel.ServicesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ServicesViewModel(get()) }
}

val apolloModule = module {
    single { apolloClient }
}
