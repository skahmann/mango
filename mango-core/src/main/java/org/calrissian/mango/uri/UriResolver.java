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
package org.calrissian.mango.uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/*
* @deprecated
*/
@Deprecated
public interface UriResolver<T> {

    String getServiceName();

    /**
     * Null should be returned if uri couldn't be resolved or item referenced doesn't exist
     *
     * @param uri
     * @param auths
     * @return
     */
    T resolveUri(URI uri, String[] auths);

    T fromStream(InputStream is) throws IOException;

    InputStream toStream(T obj) throws IOException;
}
