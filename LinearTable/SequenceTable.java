/*顺序表使用一个数组来储存数据;
例如int[] a = new int[SIZE];就创建了一个长度为SIZE的顺序表;
顺序表支持随机访问,通过下标就可以获得该位置的数据;
但是插入或删除顺序表中的元素需要移动多个元素;
所以顺序表适合于需要频繁随机读取而不需要频繁增减元素的场合;
**/
public class SequenceTable implements Table{                        //C++结构体定义如下:
	private final static int MAXSIZE = 10000; //struct SequenceList{
	private int [] table = new int [MAXSIZE];  //int data[MAXSIZE];
	private int length=0;                                //int length;

	public void insertElement(int data,int subscript){//在指定下表处插入数组元素
		if(length>=MAXSIZE||subscript<0||subscript>length){//判断参数是否合法
			throw new IllegalArgumentException("Index "+subscript+" out of bounds 0 ~"+length);					
		}
		for(int i=length;i>subscript;--i){	
			table[i]=table[i-1];													//将插入下标及之后的元素都后移一位
		}
		table[subscript]=data;												//将插入下表的值进行替换
		++length;																	//长度加1
	}
	public int remove(int subscript){				//提取并删除指定下标的数组元素
		if(subscript<0||subscript>=length){
			throw new IllegalArgumentException("Subscript is illegal");
		}
		if(length==0){
			throw new IllegalArgumentException("The Array is empty");
		}
		int result = table[subscript];
		for(int i=subscript+1;i<length;++i){							//将要删除下标之后的元素前移一位
			table[i-1]=table[i];
		}
		--length;									//长度减1
		return result;
	}
	public int get(int subscript){		            				//获得该下标的值
		if(subscript<0||subscript>=length){
			throw new IllegalArgumentException("Invalid argument");
		}
		return table[subscript];
	}
	public Table addElement(int data){										//在整个数组的末尾加入一个值
		if(length>=MAXSIZE){
			throw new IllegalArgumentException("This Array is full");
		}else{
			table[length++]=data;
		}
		return this;
	}
	public int removeLast(){										//提取并删除数组末尾的值
		if(length<=0){
			throw new IllegalArgumentException("This array is empty");
		}else {
			return table[--length];
		}
	}
	public void initializeList(){			//初始化顺序表,只需要把长度设置为0即可
		length=0;
	}
	public int searchElementIndex(int data){			//搜索一个元素在顺序表中的位置
		for(int i=0;i<length;++i){
			if(table[i]==data){
				return i;
			}
		}
		System.err.println("Not find the element");
		return -1;
	}
	public int backLength(){
		return length;
	}
	public int[] toArray(){

		return java.util.Arrays.copyOf(table,length);
	}
}