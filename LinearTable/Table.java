public interface Table {
    int backLength();
    int[] toArray();
    void initializeList();
    int removeLast();
    int remove(int subscript);
    Table addElement(int data);
    int get(int subscript);
    void insertElement(int data,int subscript);

}
