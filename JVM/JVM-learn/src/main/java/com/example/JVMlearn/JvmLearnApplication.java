package com.example.JVMlearn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.JVMlearn.basic.dal.mapper")
public class JvmLearnApplication {

	PersonBuilder personBuilder;

	/**
	 * -Xmx2688M -Xms2688M -Xmn960M
	 * -XX:MaxMetaspaceSize=512M -XX:MetaspaceSize=512M
	 * -XX:+UseConcMarkSweepGC -XX:+UseCMSInitiatingOccupancyOnly
	 * -XX:CMSInitiatingOccupancyFraction=70
	 * -XX:+ExplicitGCInvokesConcurrentAndUnloadsClasses
	 * -XX:+CMSClassUnloadingEnabled
	 * -XX:+ParallelRefProcEnabled
	 * -XX:+CMSScavengeBeforeRemark
	 * -XX:ErrorFile=./log/jvm/hs_err_pid%p.log
	 * -Xloggc:./log/jvm/gc.log
	 * -XX:HeapDumpPath=./log/jvm
	 * -XX:+PrintGCDetails
	 * -XX:+PrintGCDateStamps
	 * -verbose:class
	 * -XX:+HeapDumpOnOutOfMemoryError
	 * -XX:+PrintClassHistogramBeforeFullGC
	 * -XX:+PrintClassHistogramAfterFullGC
	 * -XX:+PrintCommandLineFlags
	 * -XX:+PrintGCApplicationConcurrentTime
	 * -XX:+PrintGCApplicationStoppedTime
	 * -XX:+PrintTenuringDistribution
	 * -XX:+PrintHeapAtGC
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(JvmLearnApplication.class, args);
	}
}
