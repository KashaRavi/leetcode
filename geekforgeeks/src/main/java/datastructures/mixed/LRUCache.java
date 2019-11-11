package datastructures.mixed;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

class LRUCache {

    private Map<Integer, Page> cacheMap;
    private int size;
    private Queue<Page> cacheQueue;
     private class Page {
         Page(int address, int data){
             this.address = address;
             this.value =data;
         }
        int value;
         int address;
    };
    private Map<Integer, Page> ram = new HashMap<Integer, Page>(){
        {
            put(100,new Page(100, 100));
            put(101,new Page(101,101));
            put(102,new Page(102,102));
            put(103,new Page(103,103));
            put(104,new Page(104,104));
            put(105,new Page(105,105));
        }
    };

    LRUCache(int size) {
        this.size =size;
        this.cacheMap =new HashMap<>(size);
        this.cacheQueue = new LinkedList<>();
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);
        Random r = new Random();
        int low = 100;
        int high = 105;
        int numAccesses =100;
        while(numAccesses>=0) {
            System.out.println(cache.getPage(r.nextInt(high - low) + low).value);
            numAccesses--;
        }
    }

    public Page getPage(int address) {
        if(cacheMap.containsKey(address)) {
            return cacheMap.get(address);
        }

        return put(address, ram.get(address));
    }

    public Page put(int address, Page value) {
        if(cacheQueue.size() == size) {
            evictLRUPage();
        }
        cacheQueue.add(value);
        cacheMap.put(value.address, value );
        return value;
    }

     private void evictLRUPage() {
         Page evictedPage = cacheQueue.remove();
         System.out.println("evicted:"+cacheMap.remove(evictedPage.address).value);
     }

 }
