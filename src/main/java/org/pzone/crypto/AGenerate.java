package org.pzone.crypto;

import java.math.BigInteger;

import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.pzone.crypto.SM2.KeyExchange;
import org.pzone.crypto.SM2.TransportEntity;

public class AGenerate {
	
	 
	
	/**
	 * A公钥
	 */
	private static String publicKeyHexString="041F2849823DF181E626DC337E36CDB96B070D771C758F34261AE44D80DAA247D6DCAA97C08B73D1324ADBEE989CCF2DC6EA302DEB6F83ABDD187FE78EF946C6B0";
	
	/**
	 * A私钥
	 */
	private static String privateKey="103428560600883256948018725354567947903075292221654557272687922281699837280160";
	
	/**
	 * A临时私钥
	 */
	private static String tempprivateKey="115372189923938708585551359265246919000254144070163626477376038335239744177380";
	
	/*
	 * A临时公钥
	 */
	private static String tempPublicKeyHexString = "04972902843FCB12A9B4277A6617547C2FA4C7D97DDC6FF7462AD138C5E5229D37570680CEFE9BF21094CAF57FE31AD8ED4B52D44FBA415E0A6EE45DA9E73A8266";
	
	private static String ID = "31323334353637383132333435363738";
	
	/**
	b的公钥: 04BC582173EA38DAC8BB70C1F0557301BC7B30BB32A22B8FD588C1E571111CA77E603386603C8D571D530C7BAE132630AD37D26D19A01E492F4713737630CEAFF4
1234567812345678
b的临时公钥：04ED99F42CC8334E833D54800EF882C516EA2D6BF36B4111705F7FC3889EB23F797B3315E687DC91844BFC83EEA26BDCD2138C8187CF327952C434F9E0AE885C49
Z: 00F183BAB74F857B438E2A4B9FBC646F15B4E2DBD8E9BBE1D828876E5267D00C0200CD431B735C1C6B7C74FFC3F4755422B82B8F75B1FACA2640942B76B0FCCC2677A7CCBD92FD40EDC2A1208A81324987318B3A62018B248BE7CB899BF701F60994C0F95824917DAC7AAF7899FD032695195397170FFE93D428F4C5343B3351764B
协商得B密钥:D2254C739F1689193411A86FFB838519

	 * @param args
	 */
	private static String b_publicKeyHexString = "04BC582173EA38DAC8BB70C1F0557301BC7B30BB32A22B8FD588C1E571111CA77E603386603C8D571D530C7BAE132630AD37D26D19A01E492F4713737630CEAFF4";
	private static String b_tempPublicKeyHexString = "04ED99F42CC8334E833D54800EF882C516EA2D6BF36B4111705F7FC3889EB23F797B3315E687DC91844BFC83EEA26BDCD2138C8187CF327952C434F9E0AE885C49";
	
	
	public static void main(String[] args) {
		byte[] publicKey = Tools.hexStringToBytes(publicKeyHexString);
		BigInteger bigInteger = new BigInteger(privateKey);
		System.out.println(bigInteger.toString());
		SM2 sm2 = new SM2();
		ECPoint RA = sm2.curve.decodePoint(publicKey).normalize();
		;
		KeyExchange aKeyExchange = new KeyExchange(ID, new SM2KeyPair(RA, bigInteger));
		
		//响应方的数据
		byte[] publicKey_b = Tools.hexStringToBytes(b_publicKeyHexString);
		ECPoint RB = sm2.curve.decodePoint(publicKey_b).normalize();
		
		TransportEntity entity2 = new TransportEntity(Tools.hexStringToBytes(b_tempPublicKeyHexString), null,SM2.ZA(ID, RB), RB);
		//临时公钥
		ECPoint temp_A = sm2.curve.decodePoint(Tools.hexStringToBytes(tempPublicKeyHexString)).normalize();
		aKeyExchange.setRA(temp_A);
		aKeyExchange.setrA(new BigInteger(tempprivateKey));
		TransportEntity entity3 = aKeyExchange.keyExchange_3(entity2);
	}
}
