package com.util.base.b_class;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author y15079
 * @create 2017-09-26 14:02
 * @desc
 **/
public class SpliteratorExample {

	public static class NumCounterSpliterator implements Spliterator<Character>{

		private char[] str;
		private int currentChar=0;
		private int end = Integer.MAX_VALUE;
		private boolean canSplit = true;

		public NumCounterSpliterator(int currentChar, int end,char[] str,  boolean canSplit) {
			this.str = str;
			this.currentChar = currentChar;
			this.end = end;
			this.canSplit = canSplit;
		}

		@Override
		public boolean tryAdvance(Consumer<? super Character> action) {
			action.accept(str[currentChar++]);
			return currentChar<=end;
		}

		@Override
		public Spliterator<Character> trySplit() {
			int i=currentChar;
			int currentCharOld = currentChar;
			for (;canSplit&&i<=end;++i){
				if (!Character.isDigit(str[i])){
					int splitBeforeEnd=end;
					canSplit=false;
					if (i+1 <= splitBeforeEnd){
						currentChar = i + 1;
						return new NumCounterSpliterator(currentCharOld,i,str,true);
					}else{
						return null;
					}
				}
			}
			canSplit=false;
			return null;
		}

		@Override
		public Comparator<? super Character> getComparator() {
			return null;
		}

		@Override
		public long estimateSize() {
			return end-currentChar+1/*Long.MAX_VALUE*/;
		}

		@Override
		public int characteristics() {
			return ORDERED | SIZED | SUBSIZED | NONNULL | IMMUTABLE /*|SORTED*/;
		}
	}

	private static int countNum(Stream<Character> stream){
		com.util.base.a_interface.SpliteratorExample.NumCounter numCounter = stream.reduce(new com.util.base.a_interface.SpliteratorExample.NumCounter(0, 0, false), com.util.base.a_interface.SpliteratorExample.NumCounter::accumulate, com.util.base.a_interface.SpliteratorExample.NumCounter::combine);
		return numCounter.getSum();
	}

	public static void main(String[] args) {
		String arr = "12%3 21sdas s34d dfsdz45   R3 jo34 sjkf8 3$1P 213ikflsd fdg55 kfd";
		System.out.println(arr);
		Spliterator<Character> spliterator=new NumCounterSpliterator(0,arr.length(),arr.toCharArray(),true);
		// 传入true表示是并行流
		Stream<Character> parallelStream = StreamSupport.stream(spliterator, true);
		System.out.println("parallel total: " + countNum(parallelStream));
	}


}
