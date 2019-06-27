
public class Base64 {

	//base64编码规则
    /*
     * charset to encode
     */
    private static final char[] CHARSET = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
        'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
        'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
        'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z', '0', '1', '2', '3',
        '4', '5', '6', '7', '8', '9', '+', '/'
    };

    /*
     * charset used to decode.
     */
    private static final int[] DECODE_CHARSET = new int[128];
    static {
        for (int i=0; i<64; i++) {
            DECODE_CHARSET[CHARSET[i]] = i;
        }
    }
  //请将字节数组内容转换为base64编码的字符串，算法不允许借助其他线程的编码函数，自己提供算法实现。
  	public static String encode(byte[] data) {
  		StringBuffer buf = new StringBuffer();
  		int group = data.length / 3 ;//三个一组，分多少组
  		int tail = data.length % 3 ; //尾部剩余几个
  		int b[] = new int[3];
  		int bit_6[] = new int[4];
  		//转码已分组的byte，3个转码为4个
  		for(int i=0; i<group; i++)
  		{
  			for(int j=0; j<3; j++)
  			{
  				b[j] = data[3*i+j] & 0xFF;
  			}
  			bit_6[0] = b[0] >> 2;
  			bit_6[1] = (b[0] & 0x03) << 4 | b[1] >> 4;
          	bit_6[2] = (b[1] & 0x0F) << 2 | b[2] >> 6; //+-*/%运算级刚好高于移位，而|低于移位
          	bit_6[3] = b[2] & 0x3F;
  			for(int j=0; j<4; j++)
  			{
  				buf.append(CHARSET[ bit_6[j] ]);
  			}
  		}
  		//处理并转码剩余的tail部分
  		if(tail == 1)//补4个0，转码为2个，补2个=
  		{
  			buf.append(CHARSET[ data[data.length-1]>>2 ]);
  			buf.append(CHARSET[ (data[data.length-1]&0x03)<<4 ]);
  			buf.append("==");
  		}
  		if(tail == 2)//补2个0，转码为3个，补1个=
  		{
  			buf.append(CHARSET[ data[data.length-2]>>2 ]);
  			buf.append(CHARSET[ (data[data.length-2]&0x03)<<4 | data[data.length-1]>>4 ]);
  			buf.append(CHARSET[ (data[data.length-1]&0x0F)<<2 ]);
  			buf.append("=");
  		}
  		return buf.toString();
  	}
  	
  	//请将base64编码的字符串解码出二进制数据
  	public static byte[] decode(String str) {
  		char[] chars = str.toCharArray();
          int bit_6[] = new int[4];
          int b[] = new int[3];
          int len = chars.length;
          // ignore last '='s
          if (chars[chars.length - 1] == '=') {
              len--;
          }
          if (chars[chars.length - 2] == '=') {
              len--;
          }

          int groups = len / 4;
          int tail = len % 4;

          // each group of characters (4 characters) will be converted into 3 bytes,
          // and last 2 or 3 characters will be converted into 1 or 2 byte(s).
          byte[] bytes = new byte[groups * 3 + (tail > 0 ? tail - 1 : 0)];

          int byteIdx = 0;

          // decode each group
          for (int i=0; i<groups; i++) {
              for(int j=0; j<4; j++)
              {
              	bit_6[j] = DECODE_CHARSET[chars[4*i + j]];
              }

              b[0] =  bit_6[0]         << 2 | bit_6[1] >>> 4;
              b[1] = (bit_6[1] & 0x0F) << 4 | bit_6[2] >>> 2;
              b[2] = (bit_6[2] & 0x03) << 6 | bit_6[3];

              bytes[byteIdx++] = (byte) b[0];
              bytes[byteIdx++] = (byte) b[1];
              bytes[byteIdx++] = (byte) b[2];
          }

          // decode last 2 or 3 characters
          if (tail == 2) {
              bytes[byteIdx] = (byte)( DECODE_CHARSET[chars[len - 2]] << 2 | DECODE_CHARSET[chars[len - 1]] >>> 4 );
          } else if (tail == 3) {
              bit_6[0] = DECODE_CHARSET[chars[len - 3]];
              bit_6[1]  = DECODE_CHARSET[chars[len - 2]];
              bit_6[2]  = DECODE_CHARSET[chars[len - 1]];

              b[0] =  bit_6[0]          << 2 | bit_6[1]  >>> 4;
              b[1] = (bit_6[1]  & 0x0F) << 4 | bit_6[2]  >>> 2;

              bytes[byteIdx++] = (byte) b[0];
              bytes[byteIdx]   = (byte) b[1];
          }

          return bytes;
  	}
	
	public static void main(String[] args) {
		byte[] a = { 1, 2, 3, -7, -9, 110 };
		String s = encode(a);
		System.out.println(s);
		byte[] b = decode(s);
		for(int i=0;i<b.length;i++) {
			System.out.print(b[i] + " ");
		}
		System.out.println();

	}

}
