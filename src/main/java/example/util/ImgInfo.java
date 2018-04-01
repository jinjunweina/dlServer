package example.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * ��c����ͨ��(java��client��c/c++��server������һ���ṹ)
 * 
 * @author kingfish
 * @version 1.0
 */
public class ImgInfo {
	private byte[] buf = new byte[1024]; // Ϊ˵�����⣬������С���¼��п�������
//	public String name = "";
	private int id = 101;
	private char[] imgAddress = new char[256];
	private char[] detectionResult = new char[100];
//	public float salary = 0;

	/**
	 * ��intתΪ���ֽ���ǰ�����ֽ��ں��byte����
	 */
	private static byte[] tolh(int n) {
		byte[] b = new byte[4];
		b[0] = (byte) (n & 0xff);
		b[1] = (byte) (n >> 8 & 0xff);
		b[2] = (byte) (n >> 16 & 0xff);
		b[3] = (byte) (n >> 24 & 0xff);
		return b;
	}
	/**
	 * ��byte����ת����String
	 */
	private static String toStr(byte[] valArr,int maxLen) {
		int index = 0;
		while(index < valArr.length && index < maxLen) {
			if(valArr[index] == 0) {
				break;
			}
			index++;
		}
		byte[] temp = new byte[index];
		System.arraycopy(valArr, 0, temp, 0, index);
		return new String(temp);
	}
	
	/**
	 * �����ֽ���ǰתΪint�����ֽ��ں��byte����
	 */
	private static int vtolh(byte[] bArr) {
		int n = 0;
		for(int i=0;i<bArr.length&&i<4;i++){
			int left = i*8;
			n+= (bArr[i] << left);
		}
		return n;
	}

	/**
	 * ��floatתΪ���ֽ���ǰ�����ֽ��ں��byte����
	 */
	private static byte[] tolh(float f) {
		return tolh(Float.floatToRawIntBits(f));
	}
	// charתbyte
	private byte[] cTob (char[] chars) {
		Charset cs = Charset.forName ("UTF-8");
		CharBuffer cb = CharBuffer.allocate (chars.length);
		cb.put (chars);
		cb.flip ();
		ByteBuffer bb = cs.encode (cb);

		return bb.array();
	}
	// byteתchar
	private char[] bToc (byte[] bytes) {
		Charset cs = Charset.forName ("UTF-8");
		ByteBuffer bb = ByteBuffer.allocate (bytes.length);
		bb.put (bytes);
		bb.flip ();
		CharBuffer cb = cs.decode (bb);

		return cb.array();
	}
	public static ImgInfo getEmployee(byte[] bArr) {
		char[] src = new char[256];
		char[] result = new char[100];
		int id = 0;
		
		byte[] temp = new byte[20];
//		name = toStr(bArr,20);
		
		System.arraycopy(bArr, 0, temp, 0, 4);
		id = vtolh(temp);
		
		return new ImgInfo(id, src, result);
	}
	/**
	 * ���첢ת��
	 */
//	public ImgInfo(String name, int id, float salary) {
//		this.name = name;
//		this.id = id;
//		this.salary = salary;
//
//		byte[] temp = name.getBytes();
//		System.arraycopy(temp, 0, buf, 0, temp.length);
//
//		temp = tolh(id);
//		System.arraycopy(temp, 0, buf, 20, temp.length);
//
//		temp = tolh(salary);
//		System.arraycopy(temp, 0, buf, 24, temp.length);
//	}
	/**
	 * ���첢ת��
	 */
	public ImgInfo(int id, char[] imgAddress, char[] detectionResult) {
		this.id = id;
		this.imgAddress = imgAddress;
		this.detectionResult = detectionResult;

		byte[] temp = tolh(id);
		System.arraycopy(temp, 0, buf, 0, temp.length);

		temp = cTob(imgAddress);
		System.arraycopy(temp, 0, buf, 4, temp.length);

		temp = cTob(detectionResult);
		System.arraycopy(temp, 0, buf, 1004, temp.length);
	}
	
	/**
	 * ����Ҫ���͵�����
	 */
	public byte[] getbuf() {
		return buf;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getBuf() {
		return buf;
	}

	public void setBuf(byte[] buf) {
		this.buf = buf;
	}

	public char[] getImgAddress() {
		return imgAddress;
	}

	public void setImgAddress(char[] imgAddress) {
		this.imgAddress = imgAddress;
	}

	public char[] getDetectionResult() {
		return detectionResult;
	}

	public void setDetectionResult(char[] detectionResult) {
		this.detectionResult = detectionResult;
	}
} // end