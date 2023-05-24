package com.cvirn.mototest.repo

import com.apollographql.apollo3.ApolloClient

val apolloClient = ApolloClient.Builder().serverUrl("https://motostrangers.com/backend").build()
