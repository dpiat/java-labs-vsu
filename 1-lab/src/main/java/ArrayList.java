import java.util.Iterator;

class ArrayList<T> {
    private int size ;
    private Object[] array;

    public ArrayList() {
        this.size = 0;
    }
    public ArrayList(int size) {
        this.size = size;
    }

    public void add(int index, T t){
        rangeCheckForAdd(index);
        Object[] new_array =  new Object[size+1];
        for (int i = 0; i < index; i++)
        {
            new_array[i] = array[i];
        }
        new_array[index] = t;
        for (int i = index; i < size; i++)
        {
            new_array[i + 1] = array[i];
        }
        this.array = new_array;
        this.size += 1;
    }

    public void add(T t) { // type mb is boolean
        Object[] new_array =  new Object[size+1];
        for (int i = 0; i < size; i++) {
            new_array[i] = this.array[i];
        }
        new_array[size] = t;
        this.array = new_array;
        this.size += 1;
    }

    public T get(int index) {
        return (T) array[index];
    }

    public T remove(int index) {
        Object[] array = new Object[size - 1];
        for (int i = 0; i < index; i++) {
            array[i] = this.array[i];
        }
        for (int i = index + 1; i < size; i++) {
            array[i-1] = this.array[i];
        }
        this.array = array;
        this.size -= 1;
        return (T) array[index];
    }

    public boolean remove(T t) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (this.array[i] == t) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            Object[] array = new Object[size - 1];
            for (int i = 0; i < index; i++) {
                array[i] = this.array[i];
            }
            for (int i = index + 1; i < size; i++) {
                array[i-1] = this.array[i];
            }
            this.array = array;
            this.size -= 1;
            return true;
        } else {
            return false;
        }
    }

    public T set(int index, T element) {
        T oldValue = (T) this.array[index];
        this.array[index] = element;
        return oldValue;
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            this.array[i] = null;
        }
        this.array = null;
        size = 0;
    }

    public boolean contains(T t) {
        for (int i = 0; i < size; i++) {
            if (this.array[i].equals(t)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(this.array[i].toString());
            if (i + 1 != size) {
                sb.append(',').append(' ');
            }
        }
        sb.append(']');
        return sb.toString();
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
}
