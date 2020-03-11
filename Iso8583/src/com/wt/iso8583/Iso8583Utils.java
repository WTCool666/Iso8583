package com.wt.iso8583;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import com.wt.iso8583.bean.EncodeType;
import com.wt.iso8583.bean.Iso8583Entity;
import com.wt.iso8583.bean.LengthType;
import com.wt.iso8583.utils.BitArray;
import com.wt.iso8583.utils.ByteUtils;
import com.wt.iso8583.utils.Converts;

public class Iso8583Utils {
	
	//得到i个0
	private String getZero(int i) {
		if (i == 0) {
			return "";
		}
		return String.format("%" + i + "s", "0").replace(" ", "0");
	}
	
	/**
	 * 补位
	 * 
	 * @param output
	 */
	private void addZero(ByteArrayOutputStream output, int num) {
		for (int i = 0; i < num; i++) {
			output.write(0);
		}
	}
	
	/**
	 * 转化8583报文
	 * 
	 * @return 2字节长度+ 11字节TPDU + 报文体(指令码 + 位图 + 域)
	 * @throws IOException
	 */
	public byte[] packageIso8583Datas(Map<Integer, String> content,boolean isUseExtendedBitMap) throws IOException {
		int bitMapBitCount=64;
		if (isUseExtendedBitMap) {
			bitMapBitCount=128;
		}
		// 封装 BitArray类  1.解析位图，得到域编号 2.设置位图，设置域编号
		BitArray bitmap = new BitArray(bitMapBitCount);
		//定义报文体 ByteArrayOutputStream，用于装载  文体(指令码 + 位图 + 域) 数据
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		//定义域数据 ByteArrayOutputStream 用于装载域数据（如果域长度类型为不定长，则域数据为：域长度+具体的域数据）
		ByteArrayOutputStream data = new ByteArrayOutputStream(); 
		for (Map.Entry<Integer, String> entry : content.entrySet()) {
			//1、先写入指令码 域数据
			if (entry.getKey() == 0) { 
				output.write(ByteUtils.getByteByNoSpli(entry.getValue()));
				continue;
			}
			int len = entry.getValue().length();// 获取内容长度
			Iso8583Entity entity = DomainInfoForm.iso.get(entry.getKey().intValue() - 1); // 获取报文内容
			//2、计算位图数据
			bitmap.set(entry.getKey().intValue() - 1, true); 
			String value = entry.getValue();
			//3、计算域数据长度
			if (entity.getLengthType() == LengthType.FIX_LEN) { // 如果长度是固定的
				len = entity.getLength();
				if (entity.getEncodeType() == EncodeType.L_BCD) {
					int zero = len - entry.getValue().length();
					value = entry.getValue() + getZero(zero);
				} else if (entity.getEncodeType() == EncodeType.R_BCD) {
					int zero = len - entry.getValue().length();
					value = getZero(zero) + entry.getValue();
				}
			} else if (entity.getLengthType() == LengthType.LLVAR_LEN) {//3.1、如果为不定长则，先写入域数据长度，0-99为1个字节域长度
				if (entity.getEncodeType()==EncodeType.BINARY) {
					data.write(Converts.strToBcdByNum(len/2 + "", 1));
				}else{
					data.write(Converts.strToBcdByNum(len + "", 1));
				}
			} else if (entity.getLengthType() == LengthType.LLLVAR_LEN) {//3.1、如果为不定长则，先写入域数据长度，0-999为2个字节域长度
				// data.write(ByteUtils.intToByte(len, 2));
				if (entity.getEncodeType()==EncodeType.BINARY) {
					data.write(Converts.strToBcdByNum(len/2 + "", 2));
				}else{
					data.write(Converts.strToBcdByNum(len + "", 2));
				}
			}
			//4、 计算域数据
			int entryLength = value.getBytes().length;
			switch (entity.getEncodeType()) {
				case L_BCD: /* 左对齐BCD码 */
					data.write(Converts.strToBcd(value, 1));// 转换为bcd
					addZero(data, len - entryLength);
					break;
				case L_ASC: /* 左对齐ASC码 */
					data.write(value.getBytes());
					addZero(data, len - entryLength);
					break;
				case R_BCD: /* 右对齐BCD码 */
					addZero(data, len - entryLength);
					data.write(Converts.strToBcd(value, 2));// 转换为bcd
					break;
				case R_ASC: /* 右对齐ASC码 */
					addZero(data, len - entryLength);
					data.write(value.getBytes());
					break;
				case BINARY: /* 右对齐ASC码 */
					addZero(data, len - entryLength);
	//				data.write(value.getBytes());
					data.write(ByteUtils.getByteByNoSpli(value));
					break;
				default:
					break;
			}
	
		}
		//5、写入位图数据
		output.write(bitmap.toByteArray());
		//6、写入拼接好的域数据(不定长则会包含域长度+具体域数据)
		output.write(data.toByteArray());
		//7、计算总包的长度
		int resultLen = output.toByteArray().length + 11;
		byte[] lengthRes = { (byte) ((resultLen >> 8) & 0xFF),
				(byte) ((resultLen) & 0xFF) };
		//定义装载 总包的 ByteArrayOutputStream 2字节长度+ 11字节TPDU + 报文体(指令码 + 位图 + 域) =
		ByteArrayOutputStream result = new ByteArrayOutputStream();	
		//8、写入长度
		result.write(lengthRes);
		byte[] tpdu = { 0x60, 0x00, 0x00, 0x00, 0x03, 0x60, 0x31, 0x00, 0x11,
				0x43, 0x00 };
		//9、写入tpdu
		result.write(tpdu);
		//10、写入域和位图数据
		result.write(output.toByteArray());
		System.out.println("封装的报文:\n"
				+ ByteUtils.getHexStr(result.toByteArray()));
		return result.toByteArray();
	}
	
	/**
	 * 解析8583数据包
	 * data = 域数据(指令码(0域 2字节)+位图(8字节)+其他域数据
	 * Map<Integer, String> = 其他与数据 key为域编号，value为该域编号的数据
	 * */
	public Map<Integer, String> parseIso8583Datas(byte[] data) {
		//定义域集合
		Map<Integer, String> mp = new HashMap<Integer, String>();
		//定义用于方便解析data的ByteArrayInputStream对象
		ByteArrayInputStream datas = new ByteArrayInputStream(data);
		int bitMapByteCount=8;
		//1、判断是否使用扩展位图
		if(((data[3]) & 128) !=0) {
			bitMapByteCount=16;//表示第一位是1，使用扩展位图，位图的字节数为16字节
		}
		byte[] bitmapArray = new byte[bitMapByteCount];
		//2、 先读取2字节指令码域数据
		datas.read(bitmapArray, 0, 2);
		//3、再读取位图数据
		datas.read(bitmapArray, 0, bitMapByteCount);
		int bitMapBitCount=bitMapByteCount*8;//1个byte=8bit,计算位图的bit数
		//4、封装 BitArray类  1.解析位图，得到域编号 2.设置位图，设置域编号
		BitArray bitmap = new BitArray(bitMapBitCount, bitmapArray);// 位图
		for (int i = 0; i < bitMapBitCount; i++) { // 遍历位图
			boolean bitHave = bitmap.get(i);
			if (bitHave) {
				//5、得到标准域实体信息对象，用于具体每个域解析数据
				Iso8583Entity entity = DomainInfoForm.iso.get(i);
				LengthType lentype = entity.getLengthType();
				int len = 0;
				int oldLen = 0;
				//5.1、根据   标准域长度类型   得到   具体域数据长度
				switch (lentype) {
					case FIX_LEN://如果是定长
						len = entity.getLength();
						oldLen = len;
						if ((entity.getEncodeType() == EncodeType.L_BCD)
								|| (entity.getEncodeType() == EncodeType.R_BCD) ) {
							if (((len % 2) != 0)) {
								len = len + 1;
							}
							len = len / 2;//则域数据byte大小为 域长度/2即可。
						}
						break;
					case LLVAR_LEN://如果是不定长，长度范围为：0-99，
						byte[] ll = new byte[1];//则长度为1个字节
						try {
							datas.read(ll);//读取1个字节域长度
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						len = ByteUtils.bytesToInt(ll);//进行转码
						oldLen = len;
						if (entity.getEncodeType() == EncodeType.L_BCD
								|| entity.getEncodeType() == EncodeType.R_BCD) {
							if (len % 2 != 0) {
								len = len + 1;
							}
							len = len / 2;
						}
						break;
					case LLLVAR_LEN://如果是不定长，长度范围为：0-999，
						byte[] lll = new byte[2];//则长度为2个字节
						try {
							datas.read(lll);//读取2个字节域长度
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						len = ByteUtils.bytesToInt(lll);//进行转码
						oldLen = len;
						if (entity.getEncodeType() == EncodeType.L_BCD
								|| entity.getEncodeType() == EncodeType.R_BCD) {
							if (len % 2 != 0) {
								len = len + 1;
							}
							len = len / 2;
						}
						break;
					default:
						break;
				}
				//5.2 根据 标准域编码类型 将域数据 进行解码转换
				EncodeType patternType = entity.getEncodeType();
				byte[] contentByte = new byte[len];
				//读取5.1中计算出来的字节长度大小的域数据
				datas.read(contentByte, 0, len);
				String contentStr = "";
				if (patternType == EncodeType.L_ASC) { // 左对齐ASCII
					contentStr = new String(contentByte);
				} else if (patternType == EncodeType.R_ASC) { // 右对齐ASCII
					try {
						contentStr = new String(contentByte,"GBK");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (patternType == EncodeType.L_BCD) { // 左对齐BCD
					contentStr = Converts.BCD2ASC(contentByte);
					int start=lentype==LengthType.FIX_LEN?0:(contentStr.length()==oldLen?0:contentStr.length()-oldLen-1);
					contentStr = contentStr.substring(start, oldLen); // 对BCD码进行截取
				} else if (patternType == EncodeType.R_BCD) { // 右对齐BCD
					contentStr = Converts.BCD2ASC(contentByte);
					contentStr = contentStr.substring(0, oldLen); // 对BCD码进行截取
				}else if(patternType==EncodeType.BINARY){
					contentStr=ByteUtils.getHexStr(contentByte);
				}
			
				mp.put(i + 1, contentStr);
			}
		}
		return mp;
	}

}
