package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseDemo {
	static Configuration cfg = null;
	
	static {
		Configuration HBASE_CONFIG = new Configuration();  
		HBASE_CONFIG.set("hbase.zookeeper.quorum", "10.64.241.46");
		HBASE_CONFIG.set("hbase.zookeeper.property.clientPort", "2181");
		cfg = HBaseConfiguration.create(HBASE_CONFIG); 		
	}
	
	//显示所有数据，通过HTable Scan来获取已有表的信息
	 public static void scan(String tablename) throws Exception{
		byte[] family = Bytes.toBytes("personalinfo");
        byte[] qualifier = Bytes.toBytes("age");
        
		HTable table = new HTable(cfg,tablename);
		Scan s = new Scan();
		ResultScanner rs = table.getScanner(s);
		StringBuffer sb = new StringBuffer();
		
		for(Result r:rs){
			sb.append(new String(r.getValue(family, qualifier)));
			System.out.println("Scan: "+sb.toString());
			sb.delete(0, sb.length());
		}
		table.close();
	}

	 public static void main(String[] args) {
		 String tablename="user";
		 try {
			HbaseDemo.scan(tablename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
}