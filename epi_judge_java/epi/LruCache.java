package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LruCache {

  LinkedHashMap<Integer, Integer> isbnMap;

  LruCache(final int capacity) {

    this.isbnMap = new LinkedHashMap<Integer, Integer>(capacity, 1.0f, true) {
      /**
       *
       */
      private static final long serialVersionUID = 1L;

      @Override
      protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return this.size() > capacity;
      }
    };

  }

  public Integer lookup(Integer key) {

    if (!isbnMap.containsKey(key)) {
      return null;
    }

    return isbnMap.get(key);
  }

  public void insert(Integer key, Integer value) {

    isbnMap.get(key);
    // if already exists, dont change price
    if (!isbnMap.containsKey(key)) {
      isbnMap.put(key, value);
    }

    return;
  }

  public Boolean erase(Object key) {
    return isbnMap.remove(key) != null;
  }

  @EpiUserType(ctorParams = { String.class, int.class, int.class })
  public static class Op {
    String code;
    int arg1;
    int arg2;

    public Op(String code, int arg1, int arg2) {
      this.code = code;
      this.arg1 = arg1;
      this.arg2 = arg2;
    }
  }

  @EpiTest(testDataFile = "lru_cache.tsv")
  public static void runTest(List<Op> commands) throws TestFailure {
    if (commands.isEmpty() || !commands.get(0).code.equals("LruCache")) {
      throw new RuntimeException("Expected LruCache as first command");
    }
    LruCache cache = new LruCache(commands.get(0).arg1);
    for (Op op : commands.subList(1, commands.size())) {
      int result;
      switch (op.code) {
        case "lookup":
          result = cache.lookup(op.arg1);
          if (result != op.arg2) {
            throw new TestFailure("Lookup: expected " + String.valueOf(op.arg2) + ", got " + String.valueOf(result));
          }
          break;
        case "insert":
          cache.insert(op.arg1, op.arg2);
          break;
        case "erase":
          result = cache.erase(op.arg1) ? 1 : 0;
          if (result != op.arg2) {
            throw new TestFailure("Erase: expected " + String.valueOf(op.arg2) + ", got " + String.valueOf(result));
          }
          break;
        default:
          throw new RuntimeException("Unexpected command " + op.code);
      }
    }
  }

  public static void main(String[] args) {
    System.exit(GenericTest.runFromAnnotations(args, "LruCache.java", new Object() {
    }.getClass().getEnclosingClass()).ordinal());
  }
}
