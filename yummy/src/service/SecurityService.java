package service;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import utils.VerifyCodeUtil;
import vo.VerifyImageVO;

public class SecurityService {

	public String md5Encrypt(String plainText) {
		byte[] secretBytes = null;
        try {
              // 生成一个MD5加密计算摘要  
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update(plainText.getBytes());
            //获得加密后的数据
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        //将加密后的数据转换为16进制数字
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        while (md5code.length() < 32) {
            md5code = "0" + md5code;
        }
        return md5code;
	}
	
	public Boolean md5Verify(String plainText,String md5) {
		return md5Encrypt(plainText).equals(md5);
	}
	
	public String createRandomString(int length) {
		StringBuffer sb = new StringBuffer();
		String string = "abcdefghijklmnopqrstuvwxyz0123456789";  
	    int len = string.length();
	    for (int i = 0; i < length; i++) {
	        sb.append(string.charAt((int) Math.round(Math.random() * (len-1))));
	    }
	    return sb.toString();
	}
	
	public  VerifyImageVO getVerifyImage(String absoluteContextPath) {
		File file=null;
		String code=null;
		try {
			code = VerifyCodeUtil.generateVerifyCode(4);
			file=new File(absoluteContextPath+"/image/verifyImages/"+code+".jpg");
			System.out.println(file.getAbsolutePath());
			VerifyCodeUtil.outputImage(200, 80, file, code);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		VerifyImageVO verifyImage=new VerifyImageVO();
		verifyImage.setCode(code);
		verifyImage.setImageUrl("/image/verifyImages/"+code+".jpg");
		return verifyImage;
	}
}
