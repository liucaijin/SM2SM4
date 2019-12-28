package org.pzone.crypto;

import java.math.BigInteger;

import org.pzone.crypto.SM2.KeyExchange;
import org.pzone.crypto.SM2.TransportEntity;

public class Sm4Util {
	
	public static void main(String[] args) {
		

		/* * A公钥
		 */
		String publicKeyHexString="04EE7F963AFB863D25BC67E52430A6E8195016E69C1FBD8468E108C2D2B35AFAA89BA4FE4AA9BDA07DA6AF99E6B4B734B913BE33A25EDF40A11C3E8F032B27C817";
		
		
		/*
		 * A临时公钥
		 */
		String tempPublicKeyHexString = "04F80218DA7FCC19422349DD610C26C131227A9F81D83747582F0FF307C12CE18B2E314F0AA054731E3FEB19AD48EF5F4041298A06D8052248459B9A77F531755B";
		 
		String aID = "31323334353637383132333435363738";
//		//发起方 密钥对生成 
//		SM2KeyPair aKeyPair = sm02.generateKeyPair();
//		byte[] publicKeyA = aKeyPair.getPublicKey().getEncoded();
//		String publicKeyAhexStr = Tools.BinaryToHexString(publicKeyA);
//		System.out.println("公钥a："+ publicKeyAhexStr);
//		
//		KeyExchange aKeyExchange = new KeyExchange(aID,aKeyPair);
//		String publicKeyHexStr = "048D063EECF85648BDA29334137773023D7C7EEE14A7228BCD3C044A02B55AABB2202D3A1E11BDEE41AB7833C549244E097BA1AD484051B70577DB0DCC62E7C619";
//		String tempPublicHexStr= "04D1502A6D7EC4EC242274B96C5AC1F4E4F5112C9C5DF5BC70D1C7970FDDB67F5AA2C6D6AEF3D80CAE4F4BA8A848F54D0803402A0664E6DE43A13D57481F90F87E";
		
	
		
		 
		String bID = "31323334353637383132333435363738";
		//响应方 密钥对生成
		BigInteger DB = new BigInteger("78512991"+ "7D45A9EA" +"5437A593"+ "56B82338"+ "EAADDA6C"+ "EB199088"+ "F14AE10D"+ "EFA229B5", 16);
		BigInteger rB = new BigInteger("7E071248"+ "14B30948"+ "9125EAED" +"10111316"+ "4EBF0F34"+ "58C5BD88" +"335C1F9D"+ "596243D6", 16);
		byte[] publicKey = Tools.hexStringToBytes(publicKeyHexString);
		byte[] tempPublicKey = Tools.hexStringToBytes(tempPublicKeyHexString);
		byte[] sm4Exchange = sm4Exchange(aID, bID, DB, rB, publicKey, tempPublicKey);
		System.out.println(Tools.BinaryToHexString(sm4Exchange));
		
		for(int i=0 ;i<100 ;i++){
			String privateKey = getPrivateKey(generateKeyPair());
			System.out.println(privateKey);
		}
		
		BigInteger bigInteger = new BigInteger("F5C78F3FBEF9BA5D01064CE7F1F7B062EDFC22BBB3F287197062299CC4FC780A", 16);
		String binaryToHexString = Tools.BinaryToHexString(bigInteger.toByteArray());
		System.out.println(binaryToHexString);
	}
    /**
     * 
     * @param aID 发起方id
     * @param bID 响应方id
     * @param DB 响应方长期私钥
     * @param rB 响应方临时私钥
     * @param publicKey 发起方公钥
     * @param tempPublicKey 发起方临时公钥
     * 
     * @return byte[] 协商后的通信密钥
     */
	public static byte[] sm4Exchange(String aID, String bID, BigInteger DB, BigInteger rB, byte[] publicKey,
			byte[] tempPublicKey) {
		SM2 sm02 = new SM2();
		KeyExchange aKeyExchange = new KeyExchange(aID, publicKey);
		SM2KeyPair bKeyPair = sm02.generateKeyPair(DB);
		//b的公钥
		byte[] encoded = bKeyPair.getPublicKey().getEncoded();
		System.out.println("b的公钥: "+ Tools.BinaryToHexString(encoded));
		encoded = bKeyPair.getPublicKey().getEncoded(false);
		System.out.println("b的公钥: "+ Tools.BinaryToHexString(encoded));
		
		KeyExchange bKeyExchange = new KeyExchange(bID,bKeyPair);
		System.out.println("ZB:"+ Tools.BinaryToHexString(bKeyExchange.Z));
		TransportEntity entity1 = aKeyExchange.keyExchange_1(tempPublicKey, publicKey);
		
		byte[] secert = bKeyExchange.keyExchange_2(entity1,rB);
		return secert;
	}
	
	public static SM2KeyPair generateKeyPair(){
		SM2 sm02 = new SM2();
		return sm02.generateKeyPair();
	}
	
	public static byte[] getPublicKey(SM2KeyPair keyPair){
		return keyPair.getPublicKey().getEncoded();
	}
	
	public static String getPrivateKey(SM2KeyPair keyPair){
		String hex = Tools.BinaryToHexString(keyPair.getPrivateKey().toByteArray());
		if(hex.length()==66){
			hex=hex.substring(2, hex.length());
		}
		return hex;
	}
	
}
