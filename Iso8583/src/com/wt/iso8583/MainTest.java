package com.wt.iso8583;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.wt.iso8583.utils.ByteUtils;

public class MainTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String card="00 ac 60 00 00 00 03 60 31 00 11 43 00 02 00 70 20 04 c0 20 c0 98 15 16 62 14 92 20 00 18 89 24 00 00 00 00 00 00 00 01 10 00 00 01 02 10 00 12 96 c2 d8 29 c0 0f 1b d5 ef d1 aa f8 b3 42 68 3c 32 51 17 ef 95 fe 00 ef ff 83 ec f9 af 58 68 88 55 83 ec f9 af 58 68 88 55 e1 56 01 3a 1b 5e f7 13 30 30 30 30 31 33 32 35 31 30 30 32 36 35 30 30 30 30 30 30 34 33 35 31 35 36 06 ae 97 a8 0a 02 84 04 26 00 00 00 00 00 00 00 00 14 22 00 00 34 00 00 00 00 16 82 ec 27 99 72 f1 8c 94 9b b1 7f 47 11 20 79 0c 36 44 33 39 38 43 39 32";
//		String card="009660000000036031001143000200702004C02"
//				+ "0C0981519621661610100846688700000000000000000100000230210001248725839C868C"
//				+ "AC809870E985AAE5825B9E7B779A4191B7E3A3032303030303831383236303735353435313130303032"
//				+ "313536AB6709ED74209D422600000000000000001422002908000000001649163A2561835591B3838B97"
//				+ "05524F864445324445454536";
		//解析8583数据
//		String card="00 96 60 00 00 00 03 60 31 00 11 43 00 02 00 70 20 04 C0 20 C0 98 15 19 62 16 61 61 01 00 84 66 88 70 00 00 00 00 00 00 00 00 10 00 00 23 02 10 00 12 48 72 58 39 C8 68 CA C8 09 87 0E 98 5A AE 58 25 B9 E7 B7 79 A4 19 1B 7E 3A 30 32 30 30 30 30 38 31 38 32 36 30 37 35 35 34 35 31 31 30 30 30 32 31 35 36 AB 67 09 ED 74 20 9D 42 26 00 00 00 00 00 00 00 00 14 22 00 29 08 00 00 00 00 16 49 16 3A 25 61 83 55 91 B3 83 8B 97 05 52 4F 86 44 45 32 44 45 45 45 36";
		ByteArrayOutputStream outAr=new ByteArrayOutputStream();
		outAr.write(ByteUtils.getByte(card));
		byte[] result = ByteUtils.subArray(outAr.toByteArray(), 13, -1);
		Iso8583Utils utils=new Iso8583Utils();
		System.out.println(utils.parseIso8583Datas(result));			
		
		Map<Integer,String> map=new HashMap<>();	 
		map.put(0,  "0200");
		map.put(2, "6216616101008466887");
		map.put(3, "000000");
		map.put(4, "000000000010");
		map.put(11, "000023");
		map.put(22, "021");
		map.put(25, "00");
		map.put(26, "12");
		map.put(35, "725839c868cac809870e985aae5825b9e7b779a4191b7e3a");
		map.put(41, "02000081");
		map.put(42, "826075545110002");
		map.put(49, "156");
		map.put(52, "ab6709ed74209d42");
		map.put(53, "2600000000000000");
		map.put(60, "22002908000000");
		map.put(62, "49163a2561835591b3838b9705524f86");
//		"da06fbf16d049cee3939c4f04d52342d"
		map.put(64, ByteUtils.getHexStrNoSpli("DE2DEEE6".getBytes()));
		byte[] data = null;
		try {
			data = utils.packageIso8583Datas(map, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("原始报文:\n"+card);		
	}

}
