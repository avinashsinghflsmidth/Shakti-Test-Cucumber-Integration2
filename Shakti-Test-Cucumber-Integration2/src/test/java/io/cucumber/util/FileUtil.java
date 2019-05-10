package io.cucumber.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FileUtil {
	public static String sep = "#";
	
	public static boolean writeOutput(List<String> report, String fileName) {
		
		FileWriter writer;
		BufferedWriter buffer = null;
		try {
			writer = new FileWriter(fileName);
			buffer = new BufferedWriter(writer);
			buffer.write(getHeader());
			int sno = 0;
			for(String s: report) {
				buffer.newLine();
				buffer.write((++sno)+sep+s);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		System.out.println("Success");
		return true;
	}
	
public static boolean writeOutputWithSignals(List<String> report, String fileName) {
		
		FileWriter writer;
		BufferedWriter buffer = null;
		try {
			writer = new FileWriter(fileName);
			buffer = new BufferedWriter(writer);
			buffer.write(getHeaderForSignals());
			int sno = 0;
			for(String s: report) {
				buffer.newLine();
				buffer.write((++sno)+sep+s);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		System.out.println("Success");
		return true;
	}

public static boolean writeOutputWithCalc(List<String> report, String fileName) {
	
	FileWriter writer;
	BufferedWriter buffer = null;
	try {
		writer = new FileWriter(fileName);
		buffer = new BufferedWriter(writer);
		buffer.write(getHeaderForCalculations());
		int sno = 0;
		for(String s: report) {
			buffer.newLine();
			buffer.write((++sno)+sep+s);
		}
	} catch (IOException e) {
		e.printStackTrace();
		return false;
	} finally {
		try {
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	System.out.println("Success");
	return true;
}
	
	public static String getHeader() {
		List<String> columnList = new ArrayList<String>();
		columnList.add("S.No");
		columnList.add("LineName");
		columnList.add("DepartmentName");
		columnList.add("DepartmentNumber");
		columnList.add("EquipmentName");
		columnList.add("EquipmentNumber");
		columnList.add("EquipmentDescription");
		columnList.add("SubEquipmentName");
		columnList.add("SubEquipmentNumber");
		columnList.add("Tag");
		columnList.add("PointType");
		columnList.add("CalculationType");
		columnList.add("OutputSignalName");
		columnList.add("DurationMins");
		columnList.add("MinRecords");
		columnList.add("Unit");
		
		String header = String.join(sep, columnList);
		System.out.println(header);
		return header;
	}
	
	public static String getHeaderForSignals() {
		List<String> columnList = new ArrayList<String>();
		columnList.add("S.No");
		columnList.add("LineName");
		columnList.add("DepartmentName");
		columnList.add("DepartmentNumber");
		columnList.add("EquipmentName");
		columnList.add("EquipmentNumber");
		columnList.add("EquipmentDescription");
		columnList.add("SubEquipmentName");
		columnList.add("SubEquipmentNumber");
		columnList.add("OutputSignalId");
		columnList.add("PointType");
		columnList.add("Unit");
		String header = String.join(sep, columnList);
		System.out.println(header);
		return header;
	}
	
	public static String getHeaderForCalculations() {
		List<String> columnList = new ArrayList<String>();
		columnList.add("S.No");
		columnList.add("OutputSignalId");
		columnList.add("Tag");
		columnList.add("CalculationType");
		columnList.add("OutputSignalName");
		columnList.add("DurationMins");
		columnList.add("MinRecords");
		String header = String.join(sep, columnList);
		System.out.println(header);
		return header;
	}
	
	public static Properties loadProperties(String fileName) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(fileName)) {
            try {
				props.load(resourceStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
        } catch (IOException e1) {
			e1.printStackTrace();
		}
		return props;
	}
	
	
}
