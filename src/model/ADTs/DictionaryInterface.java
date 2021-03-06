package model.ADTs;

import model.types.Type;
import model.values.Value;

import java.util.Collection;
import java.util.HashMap;

public interface DictionaryInterface<TKey, TValue> {
    int size();
    public String toString();
    public boolean isDefined(TKey key);
    public boolean exists(TValue value);
    public boolean isEmpty();
    public void update(TKey key, TValue newValue);
    public void insert(TKey key, TValue newValue);
    public void clear();
    public TValue getValue(TKey key);
    public TValue remove(TKey key);
    public Collection<TValue> getAllValues();
    public Collection<TKey> getAllKeys();
    public void setContent(HashMap<TKey, TValue> newContent);
    public HashMap<TKey, TValue> getAllPairs();
    public DictionaryInterface<TKey, TValue> clone();
}
