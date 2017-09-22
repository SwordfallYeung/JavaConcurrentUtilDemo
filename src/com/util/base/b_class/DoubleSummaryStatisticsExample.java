package com.util.base.b_class;


import java.util.ArrayList;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author y15079
 * @create 2017-09-22 17:04
 * @desc
 **/
public class DoubleSummaryStatisticsExample {
	static class Company{
		double revenue;

		public Company(double revenue) {
			this.revenue = revenue;
		}

		public double getRevenue() {
			return revenue;
		}
	}

	static List<Company> companyList;

	public static void setup(){
		companyList=new ArrayList<>();
		companyList.add(new Company(100.12));
		companyList.add(new Company(142.65));
		companyList.add(new Company(12.1));
		companyList.add(new Company(184.90));
	}

	public static void double_summary_stats_with_stream(){
		DoubleSummaryStatistics stats=companyList.stream().mapToDouble((x)->x.getRevenue()).summaryStatistics();

		//average
		System.out.println(stats.getAverage());

		//count
		System.out.println(stats.getCount());

		//max
		System.out.println(stats.getMax());

		//min
		System.out.println(stats.getMin());

		//sum
		System.out.println(stats.getSum());
	}

	public static void double_summary_stats_stream_reduction_target(){
		DoubleSummaryStatistics stats=companyList.stream().collect(Collectors.summarizingDouble(Company::getRevenue));

		//average
		System.out.println(stats.getAverage());

		//count
		System.out.println(stats.getCount());

		//max
		System.out.println(stats.getMax());

		//min
		System.out.println(stats.getMin());

		//sum
		System.out.println(stats.getSum());
	}

	public static void main(String[] args) {
		setup();
//		double_summary_stats_with_stream();
		double_summary_stats_stream_reduction_target();
	}
}
