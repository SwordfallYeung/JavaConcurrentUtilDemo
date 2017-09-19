package com.util.base.a_interface;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author y15079
 * @create 2017-09-19 19:39
 * @desc
 * Spliterator（splitable iterator可分割迭代器）接口是Java为了并行遍历数据源中的元素而设计的迭代器，这个可以类比最早Java提供的顺序遍历迭代器Iterator，但一个是顺序遍历，一个是并行遍历
 *
 * 从最早Java提供顺序遍历迭代器Iterator时，那个时候还是单核时代，但现在多核时代下，顺序遍历已经不能满足需求了...如何把多个任务分配到不同核上并行执行，才是能最大发挥多核的能力，所以Spliterator应运而生啦
 *
 * 所以想要看Spliterator的实现，可以直接去看JDK对于集合框架的实现，很多实现类你可以在Spliterators中找到的，也可以直接去你对应集合的stream方法中找到，
 * 比如ArrayList点进去的是Collection的默认实现，只需要提供一个Spliterator的实现，然后用StreamSupport就可以构造一个Stream了，相当方便
 *
 * 第一个方法tryAdvance就是顺序处理每个元素，类似Iterator，如果还有元素要处理，则返回true，否则返回false
 * 第二个方法trySplit，这就是为Spliterator专门设计的方法，区分与普通的Iterator，该方法会把当前元素划分一部分出去创建一个新的Spliterator作为返回，两个Spliterator变会并行执行，如果元素个数小到无法划分则返回null
 * 第三个方法estimateSize，该方法用于估算还剩下多少个元素需要遍历
 * 第四个方法characteristics，其实就是表示该Spliterator有哪些特性，用于可以更好控制和优化Spliterator的使用，具体属性你可以随便百度到，这里就不再赘言
 **/
public class SpliteratorExample {

	public static class NumCounter{
		private int num;
		private int sum;
		//是否当前是个完整的数字
		private boolean isWholeNum;

		public NumCounter(int num, int sum, boolean isWholeNum) {
			this.num = num;
			this.sum = sum;
			this.isWholeNum = isWholeNum;
		}

		public NumCounter accumulate(Character c){
			System.out.println(Thread.currentThread().getName());
			if (Character.isDigit(c)){
				return isWholeNum?new NumCounter(Integer.parseInt(""+c),sum,false):new NumCounter(Integer.parseInt(""+num+c),sum,false);
			}else {
				return new NumCounter(0,sum+num,true);
			}
		}

		public NumCounter combine(NumCounter numCounter){
			return new NumCounter(0,this.getSum()+numCounter.getSum(),numCounter.isWholeNum);
		}

		public int getSum() {
			return sum;
		}
	}

	//方法1： 该方法使用的是string，string在不同子线程间传递时候，采用了substring方法，效率不高。
	public static class NumCounterSpliterator implements Spliterator<Character>{
		private String str;
		private int currentChar=0;
		private boolean canSplit=true;

		public NumCounterSpliterator( int currentChar,String str, boolean canSplit) {
			this.str = str;
			this.currentChar = currentChar;
			this.canSplit = canSplit;
		}

		@Override
		public void forEachRemaining(Consumer<? super Character> action) {
			do{
				System.out.println("hello world");
			}while (tryAdvance(action));
		}

		@Override
		public boolean tryAdvance(Consumer<? super Character> action) {
			if (str.equals("")){
				return false;
			}
			action.accept(str.charAt(currentChar++));
			return currentChar<str.length();
		}

		@Override
		public Spliterator<Character> trySplit() {
			int i=currentChar;
			for (;canSplit&&i<str.length();++i){
				//第一个不是数字的pos，进行分割
				if (!Character.isDigit(str.charAt(i))){
					String str1=str;
					this.str=str1.substring(currentChar,i);
					canSplit=false;
					if (i+1<str1.length()){
						return new NumCounterSpliterator(0,str1.substring(i+1,str1.length()),true);
					}else {
						return null;
					}
				}
			}
			canSplit=false;
			return null;
		}

		@Override
		public long estimateSize() {
			return str.length()-currentChar;
		}

		@Override
		public int characteristics() {
			return ORDERED | SIZED | SUBSIZED | NONNULL | IMMUTABLE;
		}
	}

	private static int countNum(Stream<Character> stream){
		NumCounter numCounter=stream.reduce(new NumCounter(0,0,false),NumCounter::accumulate,NumCounter::combine);
		return numCounter.getSum();
	}

	public static void SpliteratorStringTest1(){
		String arr="12%3 21sdas s34d dfsdz45   R3 jo34 sjkf8 3$1P 213ikflsd fdg55 kfd";
		Stream<Character> stream= IntStream.range(0,arr.length()).mapToObj(arr::charAt);
		System.out.println("ordered total: "+countNum(stream));

		Spliterator<Character> spliterator=new NumCounterSpliterator(0,arr,true);
		//传入true表示是并行流
		Stream<Character> parallelStream= StreamSupport.stream(spliterator,true);
		System.out.println("parallel total: " + countNum(parallelStream));
	}



    //方法二：改为char数组
	public static class NumCounterSpliterator2 implements Spliterator<Character>{

		private char[] str;
		private int currentChar = 0;
		private int end = Integer.MAX_VALUE;
		private boolean canSplit = true;

		public NumCounterSpliterator2(int currentChar,int end,char[] str,boolean canSplit) {
			this.str = str;
			this.currentChar = currentChar;
			this.canSplit = canSplit;
			this.end = end;
		}

		@Override
		public boolean tryAdvance(Consumer<? super Character> action) {
			action.accept( str[currentChar++] );
			return currentChar < end;
		}

		@Override
		public Spliterator<Character> trySplit() {
			int i = currentChar;
			for(;canSplit && i < end; ++i){
				if(!Character.isDigit(str[i])){
					int splitBeforeEnd = end;
					end = i ;
					canSplit = false;
					if(i + 1 < splitBeforeEnd){
						return new NumCounterSpliterator2(i+1,splitBeforeEnd,str,true);
					}else{
						return null;
					}
				}
			}

			canSplit = false;
			return null;
		}

		@Override
		public long estimateSize() {
			return end - currentChar;
		}

		@Override
		public int characteristics() {
			return ORDERED | SIZED | SUBSIZED | NONNULL | IMMUTABLE;
		}
	}

	public static void  SpliteratorStringTest2(){
		String arr = "12%3 21sdas s34d dfsdz45   R3 jo34 sjkf8 3$1P 213ikflsd fdg55 kfd";

		Spliterator<Character> spliterator = new NumCounterSpliterator2(0,arr.length(),arr.toCharArray(),true);
		// 传入true表示是并行流
		Stream<Character> parallelStream = StreamSupport.stream(spliterator, true);
		System.out.println("parallel total: " + countNum(parallelStream));
	}

	public static void main(String[] args) {
//		SpliteratorStringTest1();
		SpliteratorStringTest2();
	}

}
