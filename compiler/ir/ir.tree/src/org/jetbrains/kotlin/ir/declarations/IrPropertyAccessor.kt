/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.ir.declarations

import org.jetbrains.kotlin.descriptors.PropertyAccessorDescriptor
import org.jetbrains.kotlin.descriptors.PropertyGetterDescriptor
import org.jetbrains.kotlin.descriptors.PropertySetterDescriptor
import org.jetbrains.kotlin.ir.SourceLocation
import org.jetbrains.kotlin.ir.expressions.IrBody
import org.jetbrains.kotlin.ir.visitors.IrElementVisitor

interface IrPropertyAccessor : IrFunction {
    override val descriptor: PropertyAccessorDescriptor
    var property: IrProperty?
}

interface IrPropertyGetter : IrPropertyAccessor {
    override val descriptor: PropertyGetterDescriptor
}

interface IrPropertySetter : IrPropertyAccessor {
    override val descriptor: PropertySetterDescriptor
}

abstract class IrPropertyAccessorBase(
        sourceLocation: SourceLocation,
        kind: IrDeclarationKind,
        override val body: IrBody
) : IrFunctionBase(sourceLocation, kind), IrPropertyAccessor {
    override var property: IrProperty? = null
}

class IrPropertyGetterImpl(
        sourceLocation: SourceLocation,
        kind: IrDeclarationKind,
        override val descriptor: PropertyGetterDescriptor,
        body: IrBody
) : IrPropertyAccessorBase(sourceLocation, kind, body), IrPropertyGetter {
    override fun <R, D> accept(visitor: IrElementVisitor<R, D>, data: D): R =
            visitor.visitPropertyGetter(this, data)
}

class IrPropertySetterImpl(
        sourceLocation: SourceLocation,
        kind: IrDeclarationKind,
        override val descriptor: PropertySetterDescriptor,
        body: IrBody
) : IrPropertyAccessorBase(sourceLocation, kind, body), IrPropertySetter {
    override fun <R, D> accept(visitor: IrElementVisitor<R, D>, data: D): R =
            visitor.visitPropertySetter(this, data)
}