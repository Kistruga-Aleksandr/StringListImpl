import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private static final int STARTING_SIZE = 10;

    private final Integer[] integers;
    private int capacity;

    public IntegerListImpl() {
        integers = new Integer[STARTING_SIZE];
        capacity = 0;
    }

    public IntegerListImpl(Integer a) {
        if (a < 0) {
            throw new IllegalArgumentException("Разменр массива не должен быть отрицательным");
        }
        integers = new Integer[a];
        capacity = 0;
    }


    @Override
    public Integer add(Integer item) {
        validateSize();
        validateItem(item);
        integers[capacity++] = item;
        return item;
    }

    @Override
    public Integer add(Integer index, Integer item) {
        validateSize();
        validateItem(item);
        validateIndex(index);
        if (index == capacity) {
            integers[capacity++] = item;
            return item;
        }
        System.arraycopy(integers, index, integers, index + 1, capacity - index);
        integers[index] = item;
        capacity++;
        return item;
    }

    @Override
    public Integer set(Integer index, Integer item) {
        validateIndex(index);
        validateItem(item);
        integers[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException();
        }
         item = integers[index];
        if (index != capacity) {
            System.arraycopy(integers, index + 1, integers, index, capacity - index);
        }
        capacity--;
        return item;

    }

    @Override
    public Integer remove(Integer index) {
        validateIndex(index);
        Integer item = integers[index];
        if (index != capacity) {
            System.arraycopy(integers, index + 1, integers, index, capacity - index);
        }
        capacity--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
       Integer[] copyIntegers = toArray();
        sort(copyIntegers);
        return binarySaerch(copyIntegers, item);
    }

    @Override
    public Integer indexOf(Integer item) {
        for (int i = 0; i < capacity; i++) {
            if (integers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer lastIndexOf(Integer item) {
        for (int i = capacity - 1; i >= 0; i--) {
            if (integers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(Integer index) {
        validateIndex(index);
        return integers[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public Integer size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return capacity == 0;
    }

    @Override
    public void clear() {
        capacity = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(integers, capacity);
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void validateSize() {
        if (capacity == integers.length) {
            throw new IntegersIsFullException();
        }
    }

    private void validateIndex(Integer index) {
        if (index < 0 || index > capacity) {
            throw new InvalidIndexException();
        }
    }

    private void sort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Integer temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private boolean binarySaerch(Integer[] arr, Integer item) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
}
