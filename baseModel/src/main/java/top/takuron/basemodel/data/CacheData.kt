package top.takuron.basemodel.data

import java.util.*

data class SingleCacheData<T>(val value: T, val time: Long)

class CacheData<T>(
    private val maxCacheSize: Int = 1,
    private val cacheTime: Long = 1000 * 60 * 60
) {
    private val cacheList = LinkedList<SingleCacheData<T>>()
    val cacheSize get() = cacheList.size

    fun add(item: T):Boolean {
        if (cacheSize < maxCacheSize) {
            synchronized(cacheList) {
                val cacheItem = SingleCacheData<T>(item, System.currentTimeMillis())
                cacheList.addLast(cacheItem)
                return true
            }
        }
        return false
    }

    fun poll(): T? {
        val currentTime = System.currentTimeMillis()
        if (cacheSize <= 0) return null

        var item = cacheList.removeFirst()
        while (cacheSize > 0 && (currentTime - item.time > cacheTime)) {
            item = cacheList.removeFirst()
        }
        if (currentTime - item.time < cacheTime) return item.value
        return null
    }

    fun clearCache(){
        cacheList.clear()
    }

    fun isCache():Boolean{
        if(cacheSize<=0) return false

        val currentTime = System.currentTimeMillis()
        val i = cacheList.iterator()
        while(i.hasNext()){
            if(currentTime - i.next().time < cacheTime) return true
        }
        return false
    }

    fun isFull():Boolean{
        return cacheSize==maxCacheSize
    }
}