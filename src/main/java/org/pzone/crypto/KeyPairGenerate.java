package org.pzone.crypto;

import java.math.BigInteger;

import org.bouncycastle.math.ec.ECPoint;

public class KeyPairGenerate {
	
	public static void main(String[] args) {
		SM2 sm02 = new SM2();
		SM2KeyPair generateKeyPair = sm02.generateKeyPair();
		BigInteger privateKey = generateKeyPair.getPrivateKey();
		System.out.println("打印私钥: " + privateKey.toString());
		ECPoint publicKey = generateKeyPair.getPublicKey();
		System.out.println("打印公钥: " + Tools.BinaryToHexString(publicKey.getEncoded()));
	}

}
