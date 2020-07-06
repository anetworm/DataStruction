/*
链表是使用多个节点来储存数据;
单向链表中节点中包含了储存的数据和下一个节点的内存地址(指针);
节点在java中可以通过类来定义:
class LNode{
    int data; 储存的数据
    LNode next; 下一个节点的地址
}
C++中可以使用结构体定义(也可以使用类)
struct LNode{
    int data; 储存的数据
    LNode* next; 下一个节点的指针
}
链表访问指定位置的数据需要对链表进行定义,所以开销较大,不适合随机存储;
但是插入或删除只需要对指定元素的next地址进行改动,不需要对所有数据进行操作,开销较小;
所以链表适合需要顺序访问和频繁增减元素的场合;
相对于单向链表,双向链表可以根据后继节点来访问到前驱节点,访问自由度更高;
class LNode{
    int data;
    LNode prior; 前驱节点
    LNode next; 后继节点
}
struct LNode{
    int data;
    LNode* prior;
    LNode* next;
}
**/
public class LinkedTable implements Table{
    private class LNode{            //使用双向链表储存数据
        public int data;
        public LNode prior;
        public LNode next;
        public LNode(){

        }
        public LNode(int data){         //构造方法,新建一个带有指定数据的节点
            this.data=data;
        }
        public LNode(int data, LNode prior){         //新建一个节点并连接在指定节点之后
            this(data);
            prior.next=this;
            this.prior=prior;
        }
    }
    private final LNode head=new LNode();             //头节点
    private LNode rear=head;            //尾节点,初始化为头节点
    private int length=0;         //链表的长度,初始为0

    public int backLength() {       // 获得链表的长度
        return length;
    }
    public Table addElement(int data){           //在链表末尾增加元素
        rear=new LNode(data,rear);
        ++length;
        return this;
    }
    public int removeLast(){                //提取并删除链表末尾的元素
        LNode temp=rear;
        rear=rear.prior;
        rear.next=null;
        temp.prior=null;
        --length;
        return temp.data;
    }
    public void insertElement(int data,int index){          //在第index位置插入一个元素

        if (index == length) {
            addElement(data);
            return;
        }
        LNode temp = traversalPrior(index);
        LNode dummy = temp.next;
        dummy.prior = new LNode(data, temp);
        dummy.prior.next = dummy;
        ++length;
    }
    public int remove(int index){                   //获取并删除index区域的元素
        if (index == length) {
            return removeLast();
        }
        LNode temp = traversalPrior(index);
        LNode dummy = temp.next;
        temp.next = dummy.next;
        dummy.next.prior = temp;
        dummy.next = null;
        dummy.prior = null;
        --length;
        return dummy.data;
    }

    public int get(int index) {          //获取指定位置的元素
        if (index == length) {
            return removeLast();
        }
        LNode temp = traversalPrior(index);
        return temp.next.data;

    }

    public void initializeList(){           //初始化链表
        rear=head;
        head.next=null;
        length=0;
        System.gc();
    }
    public int[] toArray(){                 //通过迭代 将链表转换为数组
        int[] arr=new int[length];
        LNode temp =head.next;
        for(int i=0;i<length;++i){
            arr[i]=temp.data;
            temp=temp.next;
        }
        return arr;
    }

    private LNode traversalPrior(int index){        //遍历链表,获得指定位置的前驱
        if(index>length){
            throw new IllegalArgumentException("The length of list is "+length+" but index is "+index);
        }else {
            LNode temp;
            if (index < length / 2) {
                temp = head;
                for (int i = 0; i < index; ++i) {
                    temp = temp.next;
                }
            } else {
                temp = rear;
                for (int i = length; i > index; --i) {
                    temp = temp.prior;
                }
            }
            return temp;
        }
    }
}
