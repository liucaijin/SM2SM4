package org.pzone.crypto;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import org.pzone.crypto.SM2.KeyExchange;
import org.pzone.crypto.SM2.TransportEntity;

/**
 * 响应方
 * @author liucaijin
 *
 */
public class BGenerate {
	/**
//	 * A公钥
//	 */
//	private static String publicKeyHexString="0427CECECA476D22EFCAB67BDCBA2828E906BACEB531591BDCBA987B29C9426A4525214F4F4B80597CD5BDD5D795E394287AD91D2AE71BC34B5C38C76CFA70D763";
//	
//	
//	/*
//	 * A临时公钥
//	 */
//	private static String tempPublicKeyHexString = "047DF1F479CDD7B3D11A6B1C8DF96571B7375054B0268E42807B0D86D7AF38D09330982131BDC5B2DF7603001450DD2481A1E63FEC9FC552F7F308C9AFC6A3B73A";
	
private static String publicKeyHexString="048c8f030a63fdeb9ec831ba7df2ddbf51ec5bd9ef58ca6aa1a344cc0e50e413d2b0053d01ad20720f029ba95494ecf2374eed45e473dc1651103d8bfca129eba6";
	
	
	/*
	 * A临时公钥
	 */
	private static String tempPublicKeyHexString = "0483a5f10a46f60862c8d3b867e61f3e95d3f733be35a14349c135618d8d7132c3978c049b08906a95ec05b2e18f62ca16e2d397efcfcb925de0ee01107a71af7e";
	
//	正确
//	private static String publicKeyHexString="045949d3e410965849cf4626291cbf3d19ab73472302f62b4c97ad49cdd2edea3af62a31b8f7cb85bc20c2bbaff3f2a3fae58981590a504a48d7b467574c51f8bf";
//	private static String tempPublicKeyHexString = "04dcfa34cfcdee0efb3e606002e927211b7e54eb0738a944486011f6d18ed2df8b1c519f7075a82dadd84f6977b1d0f6b931728215820850b9f994fb0e8f111282";

	
	
//	private static String ID = "31323334353637383132333435363738";
	
	public static void main(String[] args) throws UnsupportedEncodingException {

		SM2 sm02 = new SM2();

 
		String aID = "f911633c9c2fbca92570d05325dfccfb";
//		//发起方 密钥对生成 
//		SM2KeyPair aKeyPair = sm02.generateKeyPair();
//		byte[] publicKeyA = aKeyPair.getPublicKey().getEncoded();
//		String publicKeyAhexStr = Tools.BinaryToHexString(publicKeyA);
//		System.out.println("公钥a："+ publicKeyAhexStr);
//		
//		KeyExchange aKeyExchange = new KeyExchange(aID,aKeyPair);
//		String publicKeyHexStr = "048D063EECF85648BDA29334137773023D7C7EEE14A7228BCD3C044A02B55AABB2202D3A1E11BDEE41AB7833C549244E097BA1AD484051B70577DB0DCC62E7C619";
//		String tempPublicHexStr= "04D1502A6D7EC4EC242274B96C5AC1F4E4F5112C9C5DF5BC70D1C7970FDDB67F5AA2C6D6AEF3D80CAE4F4BA8A848F54D0803402A0664E6DE43A13D57481F90F87E";
		
		byte[] publicKey = Tools.hexStringToBytes(publicKeyHexString);
		byte[] tempPublicKey = Tools.hexStringToBytes(tempPublicKeyHexString);
		
		
		
		KeyExchange aKeyExchange = new KeyExchange(aID, publicKey);
		System.out.println("ZA:"+ Tools.BinaryToHexString(aKeyExchange.Z));
		//
		String bID = "5f68d7c40c844c9a9fee0fe2b7118549";
		//响应方 密钥对生成
		BigInteger DB = new BigInteger("6E8E700BBDFB6556ECF957CB2AAAAA69326F0F31C0E71044351DF36EBF0711C7", 16);
		BigInteger rB = new BigInteger("C2BEB2AF7F1241D56841F2F7F62E15898A5DFABD726961B68C880FDAF9293981", 16);
		SM2KeyPair bKeyPair = sm02.generateKeyPair(DB);
		//b的公钥
		byte[] encoded = bKeyPair.getPublicKey().getEncoded();
		System.out.println("b的公钥: "+ Tools.BinaryToHexString(encoded));
		encoded = bKeyPair.getPublicKey().getEncoded(false);
		System.out.println("b的公钥: "+ Tools.BinaryToHexString(encoded));
		
		KeyExchange bKeyExchange = new KeyExchange(bID,bKeyPair);
		System.out.println("ZB:"+ Tools.BinaryToHexString(bKeyExchange.Z));
		TransportEntity entity1 = aKeyExchange.keyExchange_1(tempPublicKey, publicKey);
		
		 byte[] keyExchange_2 = bKeyExchange.keyExchange_2(entity1,rB);
		
	}
}
