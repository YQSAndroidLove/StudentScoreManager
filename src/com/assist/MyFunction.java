package com.assist;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.constant.Constant;

public class MyFunction {

	public MyFunction() {

	}

	public float toFloat(String str) {
		return Float.parseFloat(str);
	}

	public int toInt(String str) {
		return Integer.parseInt(str);
	}

	public double toDouble(String str) {
		return Double.parseDouble(str);
	}

	public Date toDate(String str) {
		try {
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			return date.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String inputFixMsg(String strTip) {
		while (true) {
			String result = JOptionPane.showInputDialog(strTip);
			if (result == null) {
				break;
			} else if (!result.isEmpty()) {
				int isOk = JOptionPane.showConfirmDialog(null, "你确定要添加吗!",
						"提示......", JOptionPane.CANCEL_OPTION);
				if (0 == isOk) {
					return result;
				}
			} else {
				JOptionPane.showMessageDialog(null, "请将表信息填写完整!", "错误......",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		return null;
	}

	public void setButtonStyle(JButton btn) {
		btn.setToolTipText("修改信息");
		btn.setBorder(null);
		btn.setFocusable(false);
		btn.setContentAreaFilled(false);
	}

	public boolean isPhoneNumberValid(String phoneNumber) {
		boolean isValid = false;

		String expression = "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";
		CharSequence inputStr = phoneNumber;

		Pattern pattern = Pattern.compile(expression);

		Matcher matcher = pattern.matcher(inputStr);

		if (matcher.matches()) {
			isValid = true;
		}

		return isValid;
	}

	public boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);

		return m.matches();
	}

	public void setLabelValue(JLabel label, int fontSize, Color color) {
		label.setFont(new Font("华文楷体", Font.BOLD, fontSize));
		label.setForeground(color);
	}

	/*
	 * Integer.parseInt(String s) Long.parseLong(String s)
	 * Float.parseFloat(String s) Double.parseDouble(String s)
	 */
	public static boolean isNumber(String str) {
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(str);
			if (!isNum.matches())
				return false;
		return true;
	}
	
	public static boolean isChineseChar(String str){
	       Pattern p=Pattern.compile("[\u4e00-\u9fa5]"); 
	       Matcher m=p.matcher(str); 
	       if(m.find()){ 
	           return true;
	       }
	       return false;
	   }
}
