/*
 * Copyright (C) 2014 The Calrissian Authors
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
package org.calrissian.mango.domain;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Collections.unmodifiableCollection;

/**
 * A base tuple collection providing reusable implementations for interacting with a tuple store backed by
 * a hash map with sets in the value representing a multimap.
 */
public class BaseTupleStore implements TupleStore {

    private Multimap<String, Tuple> tuples = ArrayListMultimap.create();

    public void put(Tuple tuple) {
        checkNotNull(tuple);
        checkNotNull(tuple.getKey());

        tuples.put(tuple.getKey(), tuple);
    }

    public void putAll(Iterable<Tuple> tuples) {
        checkNotNull(tuples);
        for (Tuple tuple : tuples)
            put(tuple);
    }


    /**
     * Returns all the getTuples set on the current entity
     */
    public Collection<Tuple> getTuples() {
        return unmodifiableCollection(tuples.values());
    }

    /**
     * A get operation for multi-valued keys
     */
    public Collection<Tuple> getAll(String key) {
        checkNotNull(key);
        return tuples.get(key);
    }

    /**
     * A get operation for single-valued keys
     */
    public <T> Tuple<T> get(String key) {
        return tuples.containsKey(key) ? tuples.get(key).iterator().next() : null;
    }

    @Override
    public Set<String> keys() {
        return tuples.keySet();
    }

    @Override
    public boolean containsKey(String key) {
        return tuples.containsKey(key);
    }

    @Override
    public <T> Tuple<T> remove(Tuple<T> t) {
        checkNotNull(t);
        checkNotNull(t.getKey());

        if (tuples.containsKey(t.getKey())) {
            Collection<Tuple> tupelSet = tuples.get(t.getKey());
            if(tupelSet.remove(t))
                return t;
        }
        return null;
    }

    @Override
    public <T> Tuple<T> remove(String key) {
        checkNotNull(key);
        if (tuples.containsKey(key)) {
            Collection<Tuple> tupleSet = tuples.get(key);
            Tuple t = tupleSet.size() > 0 ? tupleSet.iterator().next() : null;
            if(t != null && tuples.get(key).remove(t))
                return t;
        }

        return null;
    }

    @Override
    public Collection<Tuple> removeAll(String key){
        checkNotNull(key);
        return tuples.removeAll(key);
    }


    @Override
    public Collection<Tuple> removeAll(Collection<Tuple> tuples) {
        checkNotNull(tuples);
        Collection<Tuple> removedTuples = new ArrayList<>();
        for (Tuple tuple : tuples)
            removedTuples.add(remove(tuple));

        return removedTuples;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseTupleStore)) return false;

        BaseTupleStore that = (BaseTupleStore) o;

        if (!tuples.equals(that.tuples)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return tuples.hashCode();
    }

    /**
     * Returns the size of the current tuple store. Since it's backed by a MultiMap, size() is a
     * constant-time operation.
     * @return
     */
    public int size() {
        return tuples.size();
    }
}
