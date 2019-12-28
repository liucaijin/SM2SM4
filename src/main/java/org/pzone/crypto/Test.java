package org.pzone.crypto;

import java.math.BigInteger;

import org.bouncycastle.math.ec.ECPoint;

public class Test {
	private static final String IDA = "1234567812345678";
	private static final String IDA2 = "31323334353637383132333435363738";

	public static void main(String[] args) {
//		System.out.println(Integer.toHexString(10));
		
		byte[] idaBytes = IDA.getBytes();
		byte[] idaBytes2 = Tools.hexStringToBytes(IDA2);
		System.out.println(Tools.BinaryToHexString(idaBytes));
		System.out.println(Tools.BinaryToHexString(idaBytes2));
		
		int entlenA = 128;
		System.out.println(entlenA);
		byte[] ENTLA = new byte[] { (byte) ((entlenA & 0xFF00)>>8), (byte) (entlenA & 0x00FF) };
		System.out.println(Tools.BinaryToHexString(ENTLA));
		System.out.println();
		SM2 sm2 = new SM2();
		
		BigInteger dA = new BigInteger("81EB26E9"+ "41BB5AF1"+ "6DF11649"+ "5F906952" +"72AE2CD6"+ "3D6C4AE1"+ "678418BE"+ "48230029", 16);
		ECPoint PA = sm2.G.multiply(dA).normalize();
		System.out.println("PA:"+ Tools.BinaryToHexString(PA.getEncoded(false)));
		byte[] XA = PA.getXCoord().toBigInteger().toByteArray();
		byte[] YA = PA.getYCoord().toBigInteger().toByteArray();
		System.out.println("XA:"+ Tools.BinaryToHexString(XA));
		System.out.println("YA:"+ Tools.BinaryToHexString(YA));
		
		BigInteger DB = new BigInteger("78512991"+ "7D45A9EA" +"5437A593"+ "56B82338"+ "EAADDA6C"+ "EB199088"+ "F14AE10D"+ "EFA229B5", 16);
		ECPoint PB = sm2.G.multiply(DB).normalize();
		System.out.println("PB:"+Tools.BinaryToHexString(PB.getEncoded(false)));
		byte[] XB = PB.getXCoord().toBigInteger().toByteArray();
		byte[] YB = PB.getYCoord().toBigInteger().toByteArray();
		System.out.println("XB:"+ Tools.BinaryToHexString(XB));
		System.out.println("YB:"+ Tools.BinaryToHexString(YB));
		
		BigInteger rA = new BigInteger("D4DE1547"+ "4DB74D06" +"491C440D"+ "305E0124"+ "00990F3E"+ "390C7E87"+ "153C12DB"+ "2EA60BB3", 16);
		ECPoint PrA = sm2.G.multiply(rA).normalize();
		System.out.println("PrA:"+Tools.BinaryToHexString(PrA.getEncoded(false)));
		byte[] x1 = PrA.getXCoord().toBigInteger().toByteArray();
		System.out.println("x1:"+x1.length);
		byte[] y1 = PrA.getYCoord().toBigInteger().toByteArray();
		System.out.println("y1:"+y1.length);
		System.out.println("x1:"+ Tools.BinaryToHexString(x1));
		System.out.println("y1:"+ Tools.BinaryToHexString(y1));
		
		BigInteger rB = new BigInteger("7E071248"+ "14B30948"+ "9125EAED" +"10111316"+ "4EBF0F34"+ "58C5BD88" +"335C1F9D"+ "596243D6", 16);
		ECPoint PrB = sm2.G.multiply(rB).normalize();
		byte[] x2 = PrB.getXCoord().getEncoded();
		System.out.println("PrB:"+Tools.BinaryToHexString(PrB.getEncoded(false)));
		System.out.println(x2.length);
		byte[] y2 = PrB.getYCoord().toBigInteger().toByteArray();
		System.out.println(y2.length);
		System.out.println("x2:"+ Tools.BinaryToHexString(x2));
		System.out.println("y2:"+ Tools.BinaryToHexString(y2));
	}
}
