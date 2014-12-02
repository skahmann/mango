/*
 * Copyright (C) 2013 The Calrissian Authors
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
package org.calrissian.mango.json.attribute;


import com.fasterxml.jackson.databind.module.SimpleModule;
import org.calrissian.mango.domain.Attribute;
import org.calrissian.mango.json.deser.AttributeDeserializer;
import org.calrissian.mango.json.ser.AttributeSerializer;
import org.calrissian.mango.types.TypeRegistry;

import static org.calrissian.mango.types.SimpleTypeEncoders.SIMPLE_TYPES;

@Deprecated
public class TupleModule extends SimpleModule {

    private final TypeRegistry<String> typeContext;

    public TupleModule() {
        this(SIMPLE_TYPES);
    }

    public TupleModule(TypeRegistry<String> typeContext) {
        super("TupleModule");
        this.typeContext = typeContext;
    }

    @Override
    public void setupModule(SetupContext context) {
        addSerializer(Attribute.class, new AttributeSerializer(typeContext));
        addDeserializer(Attribute.class, new AttributeDeserializer(typeContext));
        super.setupModule(context);
    }
}
