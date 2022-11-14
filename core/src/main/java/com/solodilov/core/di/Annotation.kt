package com.solodilov.core.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import javax.inject.Scope
import kotlin.reflect.KClass

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FeatureScope

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)