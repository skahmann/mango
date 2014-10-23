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
package org.calrissian.mango.domain.ip;


import com.google.common.net.InetAddresses;

import java.net.Inet4Address;
import java.net.InetAddress;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.net.InetAddresses.fromInteger;
import static com.google.common.primitives.Ints.fromByteArray;
import static com.google.common.primitives.UnsignedBytes.lexicographicalComparator;

/**
 * A Domain object that represents an IPv4 network address.  This is functionally a wrapper for {@link Inet4Address}
 * that is comparable to allow its use in other data objects such as {@link java.util.TreeMap} or
 * {@link com.google.common.collect.Range}
 */
public class IPv4 extends IP<Inet4Address> implements Comparable<IPv4> {

    /**
     * Generates a new IPv4 instance from the provided address.
     */
    public static IPv4 fromString(String addr) {
        checkNotNull(addr);

        try {
            InetAddress parsed = InetAddresses.forString(addr);

            if (parsed instanceof Inet4Address)
                return new IPv4((Inet4Address) parsed);

        } catch (Exception ignored) { }

        throw new IllegalArgumentException("Invalid IPv4 representation: " + addr);
    }

    public IPv4(Inet4Address address) {
        super(address);
    }

    /**
     * @deprecated use {@code IPv4.fromString()}
     */
    @Deprecated
    public IPv4(String ip) {
        super(fromString(ip).getAddress());
    }

    /**
     * @deprecated
     */
    @Deprecated
    public IPv4(long ip) {
        super(fromInteger((int) ip));
    }

    /**
     * @deprecated
     */
    @Deprecated
    public long getValue() {
        return fromByteArray(toByteArray()) & 0xFFFFFFFFL;
    }

    @Override
    public int compareTo(IPv4 o) {
        if (o == null)
            return 1;

        return lexicographicalComparator().compare(toByteArray(), o.toByteArray());
    }
}
