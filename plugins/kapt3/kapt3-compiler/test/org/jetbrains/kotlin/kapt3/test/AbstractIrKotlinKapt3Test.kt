/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.kapt3.test

import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.config.JVMConfigurationKeys
import org.jetbrains.kotlin.test.TargetBackend

abstract class AbstractIrClassFileToSourceStubConverterTest : AbstractClassFileToSourceStubConverterTest() {
    override fun updateConfiguration(configuration: CompilerConfiguration) {
        super.updateConfiguration(configuration)
        configuration.put(JVMConfigurationKeys.IR, true)
    }

    override fun getBackend() = TargetBackend.JVM_IR
}

abstract class AbstractIrKotlinKaptContextTest : AbstractKotlinKaptContextTest() {
    override fun updateConfiguration(configuration: CompilerConfiguration) {
        super.updateConfiguration(configuration)
        configuration.put(JVMConfigurationKeys.IR, true)
    }

    override fun getBackend() = TargetBackend.JVM_IR
}