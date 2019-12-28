package org.pzone.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

import javax.xml.bind.DatatypeConverter;

public class Tools {
	public final static String charset = "UTF-8";

	/**
	 * 将int型的i低len字节转换成len个字�?
	 * 
	 * @param i
	 * @param len
	 * @return
	 */
	public static String itoa(int i, int len) {
		byte[] buf = new byte[len];
		for (int j = 0; j < len; j++) {
			buf[len - j - 1] = (byte) (i >> 8 * j);
		}

		try {
			return new String(buf, charset);
		} catch (Exception e) {
			return new String(buf);
		}
	}

	/**
	 * 将int型的i转换成byte数组，高字节在前
	 * 
	 * @param i
	 * @param len
	 * @return
	 */
	public static byte[] itob(int i, int len) {
		byte[] buf = new byte[len];
		for (int j = 0; j < len; j++) {
			buf[len - j - 1] = (byte) (i >> 8 * j);
		}
		return buf;
	}

	/**
	 * 将int型的i转换成byte数组，低字节在前
	 * 
	 * @param i
	 * @param len
	 * @return
	 */
	public static byte[] itoReverseb(int i, int len) {
		byte[] buf = new byte[len];
		for (int j = 0; j < len; j++) {
			buf[j] = (byte) (i >> 8 * j);
		}
		return buf;
	}

	/**
	 * 将int型的i低len字节转换成len个字�?并反转字符的位置
	 * 
	 * @param i
	 * @param len
	 * @return
	 */
	public static String itoReverseA(int i, int len) {
		byte[] buf = new byte[len];
		for (int j = 0; j < len; j++) {
			buf[j] = (byte) (i >> 8 * j);
		}

		try {
			return new String(buf, charset);
		} catch (Exception e) {
			return new String(buf);
		}
	}

	/**
	 * 将long型的i低len字节转换成len个字�?
	 * 
	 * @param i
	 * @param len
	 * @return
	 */
	public static String itoa(long i, int len) {
		byte[] buf = new byte[len];
		for (int j = 0; j < len; j++) {
			buf[len - j - 1] = (byte) (i >> 8 * j);
		}

		try {
			return new String(buf, charset);
		} catch (Exception e) {
			return new String(buf);
		}
	}

	public static byte[] itob(long i, int len) {
		byte[] buf = new byte[len];
		try {
			for (int j = 0; j < len; j++) {
				buf[len - j - 1] = (byte) (i >> 8 * j);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buf;
	}

	public static byte[] itoReverseB(long i, int len) {
		byte[] buf = new byte[len];
		for (int j = 0; j < len; j++) {
			buf[j] = (byte) (i >> 8 * j);
		}
		return buf;
	}

	/**
	 * 将long型的i低len字节转换成len个字�?并反转字符的位置
	 * 
	 * @param i
	 * @param len
	 * @return
	 */
	public static String itoReverseA(long i, int len) {
		byte[] buf = new byte[len];
		for (int j = 0; j < len; j++) {
			buf[j] = (byte) (i >> 8 * j);
		}

		try {
			return new String(buf, charset);
		} catch (Exception e) {
			return new String(buf);
		}
	}

	/**
	 * 将byte[]转换成十六进制字符串
	 * 
	 * @param src
	 *            byte[] data
	 * @return hex string
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString().toUpperCase();
	}

	public static String BinaryToHexString(byte[] bytes) {
		String hexStr = "0123456789ABCDEF";
		String result = "";
		String hex = "";
		for (int i = 0; i < bytes.length; i++) {
			// 字节�?�?
			hex = String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4));
			// 字节�?�?
			hex += String.valueOf(hexStr.charAt(bytes[i] & 0x0F));
			result += hex;
		}
		return result;
	}

	/**
	 * 将byte[]转换成十六进制字符串
	 * 
	 * @param src
	 * @param beginIndex
	 * @param length
	 * @return
	 */
	public static String bytesToHexString(byte[] src, int beginIndex, int length) {
		byte[] dsc = new byte[length];
		System.arraycopy(src, beginIndex, dsc, 0, length);
		return bytesToHexString(dsc);
	}

	/**
	 * 将十六进制字符串转换成byte[]
	 * 
	 * @param hexString
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	/**
	 * Convert char to byte
	 * 
	 * @param c
	 *            char
	 * @return byte
	 */
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/**
	 * �?byte数组src从beginIndex�?��，连�?位取出来转换成long�?
	 * 
	 * @param src
	 * @param beginIndex
	 * @return
	 */
	public static long byteTolong(byte[] src, int beginIndex) {
		long l = 0;
		l |= ((long) src[beginIndex + 7] & 0xff);
		l |= (((long) src[beginIndex + 6] & 0xff) << 8);
		l |= (((long) src[beginIndex + 5] & 0xff) << 16);
		l |= (((long) src[beginIndex + 4] & 0xff) << 24);
		l |= (((long) src[beginIndex + 3] & 0xff) << 32);
		l |= (((long) src[beginIndex + 2] & 0xff) << 40);
		l |= (((long) src[beginIndex + 1] & 0xff) << 48);
		l |= (((long) src[beginIndex + 0] & 0xff) << 56);
		return l;
	}

	// 获取uuid
	public static String getUUID() {

		String key = UUID.randomUUID().toString();
		// 去掉�?�?replaceAll 性能不好
		String[] keys = key.split("-");
		StringBuffer sb = new StringBuffer();
		for (String k : keys) {
			sb.append(k);
		}
		return sb.toString();
	}

	// //用户名封装成byte[]
	// public static byte[] StringToBytes(String name ,int len) {
	//
	//
	// StringBuilder stringBuilder = new StringBuilder(name);
	// int n = stringBuilder.toString().getBytes().length;
	// for (; n < len; n++) {
	// stringBuilder.append(" ");
	// }
	// return stringBuilder.toString().getBytes();
	//
	// }

	/**
	 * 将服务器IP等封装成字节数组
	 * 
	 * @param server
	 * @return
	 */
	public static byte[] serverToBytes(String server) {

		String[] my = server.split("\\.");
		byte[] buf = new byte[4];

		buf[0] = (byte) Integer.parseInt(my[0]);
		buf[1] = (byte) Integer.parseInt(my[1]);
		buf[2] = (byte) Integer.parseInt(my[2]);
		buf[3] = (byte) Integer.parseInt(my[3]);

		return buf;

	}

	/**
	 * 将字符串name转换成len个字节长度的byte，name的字节长度不足len字节的话，右边补空格。name的长度超过len字节的话�?
	 * 支取前面len字节
	 * 
	 * @param name
	 * @param len
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] StringToBytes(String name, int len) {
		ByteBuffer buf = ByteBuffer.allocate(len);
		byte[] b = null;
		try {
			b = name.getBytes(charset);
		} catch (Exception e) {

			b = name.getBytes();
		}
		if (b.length <= len) {
			buf.put(b);

			for (int i = b.length; i < len; i++) {
				buf.put((byte) 0x20);
			}
		} else {
			buf.put(b, 0, len);
		}

		byte[] out = new byte[len];
		buf.flip();
		buf.get(out);
		return out;
	}

	/**
	 * byte数组转换成字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String bytesToString(byte[] b) {
		String s = "";
		try {
			s = new String(b, charset).trim();
		} catch (UnsupportedEncodingException e) {
			s = new String(b).trim();
		}
		return s;
	}

	public static byte[] StringToByte(String str) {
		byte[] b = null;
		try {
			b = str.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			b = str.getBytes();
		}
		return b;
	}

	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	public static String getCRC32(byte[] b) {
		try {
			CRC32 crc32 = new CRC32();
			crc32.update(b);
			String str = Long.toHexString(crc32.getValue()).toUpperCase();
			return getCRCString(str);
		} catch (Exception e) {
			return "";
		}

	}

	public static String getCRCString(String str) {
		int len = str.length();
		if (len == 1) {
			str = "0000000" + str;
		}
		if (len == 2) {
			str = "000000" + str;
		}
		if (len == 3) {
			str = "00000" + str;
		}
		if (len == 4) {
			str = "0000" + str;
		}
		if (len == 5) {
			str = "000" + str;
		}
		if (len == 6) {
			str = "00" + str;
		}
		if (len == 7) {
			str = "0" + str;
		}
		return str;
	}

	@SuppressWarnings("resource")
	public static String getFileCRCCode(File file) throws Exception {

		FileInputStream fileinputstream = new FileInputStream(file);
		CRC32 crc32 = new CRC32();
		for (CheckedInputStream checkedinputstream = new CheckedInputStream(fileinputstream, crc32); checkedinputstream
				.read() != -1;) {
		}
		return Long.toHexString(crc32.getValue());

	}

	public static boolean isWindows() {
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			return true;
		}
		return false;
	}

	/**
	 * 无符号短整型高字节在前
	 * 
	 * @param data
	 * @param start
	 * @return
	 */
	public static int byte2Int2(byte[] data, int start) {
		return (data[start] << 8 & 0x0000FF00) | (data[start + 1] & 0x000000FF);

	}

	/**
	 * 有符号短整型高字节在前
	 * 
	 * @param data
	 * @param start
	 * @return
	 */
	public static int signedByte2Int2(byte[] data, int start) {
		return (short) (data[start] << 8 & 0xFF00) | (data[start + 1] & 0x00FF);

	}

	/**
	 * 有符号四个字节整型高字节在前
	 * 
	 * @param data
	 * @param start
	 * @return
	 */
	public static int bytes2Int21(byte[] data, int start) {
		return (data[start] << 24 & 0xFF000000) | (data[start + 1] << 16 & 0x00FF0000)
				| (data[start + 2] << 8 & 0x0000FF00) | (data[start + 3] & 0x000000FF);
	}

	/**
	 * 无符号四个字节整型高字节在前
	 * 
	 * @param data
	 * @param start
	 * @return
	 */
	public static long getUnsignedIntt(byte[] data, int start) {
		return ((data[start] << 24 & 0xFF000000) | (data[start + 1] << 16 & 0x00FF0000)
				| (data[start + 2] << 8 & 0x0000FF00) | (data[start + 3] & 0x000000FF)) & 0x0FFFFFFFFl;
	}
	
	/**
	 * 无符号单字节
	 * 
	 * @param data
	 * @param start
	 * @return
	 */
	public static int byte2Int1(byte[] data, int start) {
		return (data[start] & 0x000000FF);
	}

	/**
	 * 有符号单字节
	 * 
	 * @param data
	 * @param start
	 * @return
	 */
	public static int signedByte2Int1(byte[] data, int start) {
		return (byte) (data[start] & 0xFF);
	}

	public static void main(String[] args) throws Exception {
		// File file = new File("F:/临时文件夹/固件/外网/x6c2df1communication10014.bin");
		// String crc = Tools.getFileCRCCode(file);
		// System.out.println(crc);
		/*
		 * CRC32 crc32 = new CRC32(); crc32.update("hello-world".getBytes());
		 * System.out.println(crc32.getValue());
		 */
		// System.out.println(toWeek(255));
		// byte[] a = itoReverseb(-2888, 4);
		// System.out.println(bytes2Int2(a, 0));

		// byte[] b = itoReverseb(-8, 2);
		// System.out.println(byte2Int2(b, 0));
		// System.out.println(bytesToHexString(a));
		

		byte[] a = DatatypeConverter.parseHexBinary("FFf2ffff");
		System.out.println(getUnsignedIntt(a, 0));

	}

	/**
	 * 高字节在后
	 * 
	 * @param data
	 * @param start
	 * @return
	 */
	public static int bytes2Int2(byte[] data, int start) {
		return (data[start + 3] << 24 & 0xFF000000) | (data[start + 2] << 16 & 0x00FF0000)
				| (data[start + 1] << 8 & 0x0000FF00) | (data[start] & 0x000000FF);
	}


	// 有符号整型
	// public static int byteArrayToInt(byte[] b, int index) {
	// return b[index + 3] & 0xFF |
	// (b[index + 2] & 0xFF) << 8 |
	// (b[index + 1] & 0xFF) << 16 |
	// (b[index] & 0xFF) << 24;
	// }
	//
	public static int byteArrayToShort(byte[] b, int index) {
		return b[index + 1] & 0xF | (b[index + 0] & 0xF) << 4;
	}

	// 从byte数组的index处的连续4个字节获得一个float
	public static float getFloat(byte[] arr, int index) {
		return Float.intBitsToFloat(bytes2Int21(arr, index));
	}

	public static String bytes2IpString(byte[] buf, int startPosition) {
		String localStaticIP = "";
		int fourth = buf[startPosition + 0] & 0xff;
		int third = buf[startPosition + 1] & 0xff;
		int second = buf[startPosition + 2] & 0xff;
		int first = buf[startPosition + 3] & 0xff;
		localStaticIP = fourth + "." + third + "." + second + "." + first;
		return localStaticIP;
	}

	/**
	 * int 转换8�?二进�?
	 * 
	 * @author 王富�?2014-9-13
	 * @param b
	 * @return
	 */
	public static String getEigthBitsStringFromByte(int b) {
		// b=b+100000000(2^8=256) then only get the lower 8 digit
		b |= 256; // mark the 9th digit as 1 to make sure the string has
					// at least 8 digits
		String str = Integer.toBinaryString(b);
		int len = str.length();
		return str.substring(len - 8, len);

	}

	/**
	 * 返回星期 字符�?
	 * 
	 * @author 王富�?2014-9-13
	 * @param week
	 * @return
	 */
	public static String toWeek(Integer week) {
		String str = Tools.getEigthBitsStringFromByte(week);
		String top1 = str.substring(0, 1);
		String body = str.substring(1, str.length());
		StringBuffer sb = new StringBuffer();
		if ("0".equals(top1)) {
			return "0";
		} else {
			// StringBuffer stringBuffer = new StringBuffer(body);
			String[] w = { "SUN", "SAT", "FRI", "THU", "WED", "TUE", "MON" };
			for (int i = 0; i < 7; i++) {
				String s = body.substring(i, i + 1);
				if ("1".equals(s)) {
					sb.append(w[i] + ",");
				}
			}
			String s = sb.toString();
			return s.substring(0, s.lastIndexOf(","));
		}
	}

	/**
	 * 
	 */
	private static Random randomNum = new Random();

	private static int getLength(int v) {
		if (v < 0) {
			throw new RuntimeException("value error:" + v);
		}

		if (v == 0) {
			return 1;
		}

		return (int) (Math.log10(v)) + 1;
	}

	/**
	 * 获取固定长度的随机数�?
	 * 
	 * @param len
	 *            随机数长�?
	 */
	public static String getRandomNum(int len) {
		int max = (int) Math.pow(10, len);
		int v = randomNum.nextInt(max);
		int l = getLength(v);
		int append = len - l;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < append; i++) {
			str.append("0");
		}

		str.append(v);
		return str.toString();
	}

	public static int getWeek(Calendar c) {
		int week = c.get(Calendar.DAY_OF_WEEK);
		if (week == 1) {
			return 7;
		}

		return week - 1;
	}

	public static boolean containsWeek(int setting, int week) {
		// if((setting & 0x80) == 0){
		// return false;
		// }

		int v = setting >> (week - 1);
		return (v & 1) != 0;
	}

	/**
	 * �?��时间点与当天0点的时间�?单位:�?
	 */
	public static int getTimeOffsetOneDay(int hour, int minute, int second) {
		return hour * 3600 + minute * 60 + second;
	}

	public static int getTimeOffsetOneDay(Calendar c) {
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);

		return getTimeOffsetOneDay(hour, minute, second);
	}

	public static String getH(byte b0, byte b1) {
		return (char) b0 + "" + (char) b1;
	}

	public static int getL(byte b2, byte b3) {
		return (b2 << 8 & 0x0000FF00) | (b3 & 0x000000FF);
	}

	public static int getL1(byte b2, byte b3) {
		return (b2 << 8 & 0x0000FF00) | (b3 & 0x000000FF);
	}

	public static String getP(byte b4, byte b5) {

		return (char) b4 + "" + (char) b5;
	}

	/**
	 * @功能: BCD码转为10进制串(阿拉伯数据)
	 * @参数: BCD码
	 * @结果: 10进制串
	 */
	public static String bcd2Str(byte[] bytes) {
		StringBuffer temp = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
			temp.append((byte) (bytes[i] & 0x0f));
		}
		return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp.toString().substring(1) : temp.toString();
	}

	/**
	 * @功能: 10进制串转为BCD码
	 * @参数: 10进制串
	 * @结果: BCD码
	 */
	public static byte[] str2Bcd(String asc) {
		int len = asc.length();
		int mod = len % 2;
		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}
		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}
		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;
		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}
			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}
			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}

	public static String byteToChar(byte[] bs) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bs) {
			sb.append((char) b);
		}
		return sb.toString().trim();
	}

	/**
	 * 0x21 0x23 0x24 返回212324
	 * 
	 * @param bs
	 * @return
	 */
	public static String byteToHex(byte[] bs) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bs) {
			byte b1 = (byte) (b >> 4 & 0x0f);
			byte b2 = (byte) (b & 0x0f);
			sb.append(Integer.toHexString(b1) + Integer.toHexString(b2));
		}
		return sb.toString().toUpperCase();
	}

	public static String getVersion(byte[] bs) {
		StringBuilder sb = null;
		for (byte b : bs) {
			int a = b & 0x00ff;
			if (sb == null) {
				sb = new StringBuilder();
				sb.append(String.valueOf(a));
			} else {
				sb.append(".").append(a);
			}
		}
		return sb.toString().trim();
	}

	/**
	 * 将arg1，arg2合并
	 * 
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static byte[] concat(byte[] arg1, byte[] arg2) {
		byte[] result = Arrays.copyOf(arg1, arg1.length + arg2.length);
		System.arraycopy(arg2, 0, result, arg1.length, arg2.length);
		return result;
	}

	public static byte[] float2byte(float f) {

		// 把float转换为byte[]
		int fbit = Float.floatToIntBits(f);

		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			b[i] = (byte) (fbit >> (24 - i * 8));
		}

		// 翻转数组
		int len = b.length;
		// 建立一个与源数组元素类型相同的数组
		byte[] dest = new byte[len];
		// 为了防止修改源数组，将源数组拷贝一份副本
		System.arraycopy(b, 0, dest, 0, len);
		byte temp;
		// 将顺位第i个与倒数第i个交换
		for (int i = 0; i < len / 2; ++i) {
			temp = dest[i];
			dest[i] = dest[len - i - 1];
			dest[len - i - 1] = temp;
		}

		return dest;

	}

	/**
	 * 字节转换为浮点
	 * 
	 * @param b
	 *            字节（至少4个字节）
	 * @param index
	 *            开始位置
	 * @return
	 */
	public static float byte2float(byte[] b, int index) {
		int l;
		l = b[index + 0];
		l &= 0xff;
		l |= ((long) b[index + 1] << 8);
		l &= 0xffff;
		l |= ((long) b[index + 2] << 16);
		l &= 0xffffff;
		l |= ((long) b[index + 3] << 24);
		return Float.intBitsToFloat(l);
	}

}
