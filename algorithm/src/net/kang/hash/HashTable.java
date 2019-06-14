package net.kang.hash;

public interface HashTable {
    void add(int value) throws Exception;
    void remove(int value);
    boolean contains(int value);
}
