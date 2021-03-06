/*
 * MIT License
 *
 * Copyright (c) 2017 Felix Klauke
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package de.felix_klauke.exorbia.core.document

import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

/**
 * @author Felix 'SasukeKawaii' Klauke <sasukekawaii></sasukekawaii>@ungespielt.net>
 */
open class AbstractDocument<ContentType>(private var id: String, private var content: ContentType, private var expiry: Long) : IDocument<ContentType> {

    override fun id(): String {
        return id
    }

    override fun content(): ContentType {
        return content
    }

    override fun expiry(): Long {
        return expiry
    }

    @Throws(IOException::class)
    open fun writeToSerializedStream(stream: ObjectOutputStream) {
        stream.writeLong(expiry)
        stream.writeUTF(id)
        stream.writeObject(content)
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    open fun readFromSerializedStream(stream: ObjectInputStream) {
        expiry = stream.readLong()
        id = stream.readUTF()
        content = stream.readObject() as ContentType
    }
}
