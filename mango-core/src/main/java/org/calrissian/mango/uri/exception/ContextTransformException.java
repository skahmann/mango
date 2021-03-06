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
package org.calrissian.mango.uri.exception;

/*
* @deprecated
*/
@Deprecated
public class ContextTransformException extends Exception {
    private static final long serialVersionUID = 1L;

    public ContextTransformException() {
    }

    public ContextTransformException(String s) {
        super(s);
    }

    public ContextTransformException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ContextTransformException(Throwable throwable) {
        super(throwable);
    }
}
