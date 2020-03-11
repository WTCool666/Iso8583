package com.wt.iso8583;

import java.util.ArrayList;
import java.util.List;
import com.wt.iso8583.bean.EncodeType;
import com.wt.iso8583.bean.Iso8583Entity;
import com.wt.iso8583.bean.LengthType;

public class DomainInfoForm {

	//标准的域信息对象
	public static List<Iso8583Entity> iso = new ArrayList<Iso8583Entity>();
	static { // 初始化iso数组
		iso.add(new Iso8583Entity(8, EncodeType.L_BCD, LengthType.FIX_LEN)); // 1
		iso.add(new Iso8583Entity(22, EncodeType.L_BCD, LengthType.LLVAR_LEN)); // 2
		iso.add(new Iso8583Entity(6, EncodeType.L_BCD, LengthType.FIX_LEN)); // 3
		iso.add(new Iso8583Entity(12, EncodeType.R_BCD, LengthType.FIX_LEN)); // 4
		iso.add(new Iso8583Entity(12, EncodeType.L_BCD, LengthType.FIX_LEN)); // 5
		iso.add(new Iso8583Entity(12, EncodeType.L_BCD, LengthType.FIX_LEN)); // 6
		iso.add(new Iso8583Entity(10, EncodeType.R_BCD, LengthType.FIX_LEN));// 7
		iso.add(new Iso8583Entity(8, EncodeType.R_BCD, LengthType.FIX_LEN)); // 8
		iso.add(new Iso8583Entity(8, EncodeType.R_BCD, LengthType.FIX_LEN)); // 9
		iso.add(new Iso8583Entity(8, EncodeType.R_BCD, LengthType.FIX_LEN)); // 10
		iso.add(new Iso8583Entity(6, EncodeType.L_BCD, LengthType.FIX_LEN)); // 11
		iso.add(new Iso8583Entity(6, EncodeType.L_BCD, LengthType.FIX_LEN)); // 12
		iso.add(new Iso8583Entity(4, EncodeType.L_BCD, LengthType.FIX_LEN)); // 13
		iso.add(new Iso8583Entity(4, EncodeType.L_BCD, LengthType.FIX_LEN)); // 14
		iso.add(new Iso8583Entity(4, EncodeType.L_BCD, LengthType.FIX_LEN)); // 15
		iso.add(new Iso8583Entity(4, EncodeType.R_BCD, LengthType.FIX_LEN)); // 16
		iso.add(new Iso8583Entity(4, EncodeType.R_BCD, LengthType.FIX_LEN)); // 17
		iso.add(new Iso8583Entity(4, EncodeType.R_BCD, LengthType.FIX_LEN)); // 18
		iso.add(new Iso8583Entity(3, EncodeType.R_BCD, LengthType.FIX_LEN)); // 19
		iso.add(new Iso8583Entity(3, EncodeType.R_BCD, LengthType.FIX_LEN)); // 20
		iso.add(new Iso8583Entity(3, EncodeType.R_BCD, LengthType.FIX_LEN)); // 21
		iso.add(new Iso8583Entity(3, EncodeType.L_BCD, LengthType.FIX_LEN)); // 22
		iso.add(new Iso8583Entity(3, EncodeType.R_BCD, LengthType.FIX_LEN)); // 23
		iso.add(new Iso8583Entity(3, EncodeType.R_BCD, LengthType.FIX_LEN)); // 24
		iso.add(new Iso8583Entity(2, EncodeType.L_BCD, LengthType.FIX_LEN)); // 25
		iso.add(new Iso8583Entity(2, EncodeType.L_BCD, LengthType.FIX_LEN)); // 26
		iso.add(new Iso8583Entity(1, EncodeType.L_BCD, LengthType.FIX_LEN)); // 27
		iso.add(new Iso8583Entity(6, EncodeType.L_BCD, LengthType.FIX_LEN)); // 28
		iso.add(new Iso8583Entity(8, EncodeType.L_BCD, LengthType.FIX_LEN)); // 29
		iso.add(new Iso8583Entity(8, EncodeType.L_BCD, LengthType.FIX_LEN)); // 30
		iso.add(new Iso8583Entity(8, EncodeType.L_BCD, LengthType.FIX_LEN)); // 31
		iso.add(new Iso8583Entity(11, EncodeType.L_BCD, LengthType.LLVAR_LEN)); // 32
		iso.add(new Iso8583Entity(11, EncodeType.L_BCD, LengthType.LLVAR_LEN)); // 33
		iso.add(new Iso8583Entity(28, EncodeType.L_BCD, LengthType.LLLVAR_LEN)); // 34
		iso.add(new Iso8583Entity(37, EncodeType.L_BCD, LengthType.LLVAR_LEN)); // 35
		iso.add(new Iso8583Entity(104, EncodeType.L_BCD, LengthType.LLLVAR_LEN)); // 36
		iso.add(new Iso8583Entity(12, EncodeType.L_ASC, LengthType.FIX_LEN)); // 37
		iso.add(new Iso8583Entity(6, EncodeType.L_ASC, LengthType.FIX_LEN)); // 38
		iso.add(new Iso8583Entity(2, EncodeType.L_ASC, LengthType.FIX_LEN)); // 39
		iso.add(new Iso8583Entity(3, EncodeType.L_BCD, LengthType.FIX_LEN)); // 40
		iso.add(new Iso8583Entity(8, EncodeType.R_ASC, LengthType.FIX_LEN)); // 41
		iso.add(new Iso8583Entity(15, EncodeType.R_ASC, LengthType.FIX_LEN)); // 42
		iso.add(new Iso8583Entity(40, EncodeType.L_BCD, LengthType.FIX_LEN)); // 43
		iso.add(new Iso8583Entity(25, EncodeType.L_ASC, LengthType.LLVAR_LEN)); // 44
		iso.add(new Iso8583Entity(76, EncodeType.L_BCD, LengthType.LLVAR_LEN)); // 45
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLLVAR_LEN)); // 46
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLLVAR_LEN)); // 47
		iso.add(new Iso8583Entity(999, EncodeType.L_BCD, LengthType.LLLVAR_LEN)); // 48
		iso.add(new Iso8583Entity(3, EncodeType.R_ASC, LengthType.FIX_LEN)); // 49
		iso.add(new Iso8583Entity(3, EncodeType.R_BCD, LengthType.FIX_LEN)); // 50
		iso.add(new Iso8583Entity(3, EncodeType.R_BCD, LengthType.FIX_LEN)); // 51
		iso.add(new Iso8583Entity(8, EncodeType.BINARY, LengthType.FIX_LEN)); // 52
		iso.add(new Iso8583Entity(16, EncodeType.L_BCD, LengthType.FIX_LEN)); // 53
		iso.add(new Iso8583Entity(120, EncodeType.R_ASC, LengthType.LLLVAR_LEN)); // 54
		iso.add(new Iso8583Entity(999, EncodeType.BINARY, LengthType.LLLVAR_LEN)); // 55
		iso.add(new Iso8583Entity(999, EncodeType.R_ASC, LengthType.LLLVAR_LEN)); // 56
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLLVAR_LEN)); // 57
		iso.add(new Iso8583Entity(999, EncodeType.R_ASC, LengthType.LLLVAR_LEN)); // 58
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLLVAR_LEN)); // 59
		iso.add(new Iso8583Entity(15, EncodeType.L_BCD, LengthType.LLLVAR_LEN)); // 60
		iso.add(new Iso8583Entity(999, EncodeType.L_BCD, LengthType.LLLVAR_LEN)); //61		
		iso.add(new Iso8583Entity(11, EncodeType.BINARY, LengthType.LLLVAR_LEN)); // 62
		iso.add(new Iso8583Entity(11, EncodeType.R_ASC, LengthType.LLLVAR_LEN)); // 63
		iso.add(new Iso8583Entity(8, EncodeType.BINARY, LengthType.FIX_LEN)); // 64
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.FIX_LEN));//65
		iso.add(new Iso8583Entity(1, EncodeType.R_ASC, LengthType.FIX_LEN));//66
		iso.add(new Iso8583Entity(999, EncodeType.R_ASC, LengthType.FIX_LEN));//67
		iso.add(new Iso8583Entity(999, EncodeType.R_ASC, LengthType.FIX_LEN));//68
		iso.add(new Iso8583Entity(999, EncodeType.R_ASC, LengthType.FIX_LEN));//69
		iso.add(new Iso8583Entity(3, EncodeType.R_ASC, LengthType.FIX_LEN));//70
		iso.add(new Iso8583Entity(999, EncodeType.R_ASC, LengthType.FIX_LEN));//71
		iso.add(new Iso8583Entity(999, EncodeType.R_ASC, LengthType.FIX_LEN));//72
		iso.add(new Iso8583Entity(6, EncodeType.R_ASC, LengthType.FIX_LEN));//73
		iso.add(new Iso8583Entity(10, EncodeType.R_ASC, LengthType.FIX_LEN));//74
		iso.add(new Iso8583Entity(10, EncodeType.R_ASC, LengthType.FIX_LEN));//75
		iso.add(new Iso8583Entity(10, EncodeType.R_ASC, LengthType.FIX_LEN));//76
		iso.add(new Iso8583Entity(10, EncodeType.R_ASC, LengthType.FIX_LEN));//77
		iso.add(new Iso8583Entity(10, EncodeType.R_ASC, LengthType.FIX_LEN));//78
		iso.add(new Iso8583Entity(10, EncodeType.R_ASC, LengthType.FIX_LEN));//79
		iso.add(new Iso8583Entity(10, EncodeType.R_ASC, LengthType.FIX_LEN));//80
		iso.add(new Iso8583Entity(10, EncodeType.R_ASC, LengthType.FIX_LEN));//81
		iso.add(new Iso8583Entity(12, EncodeType.R_ASC, LengthType.FIX_LEN));//82
		iso.add(new Iso8583Entity(12, EncodeType.R_ASC, LengthType.FIX_LEN));//83
		iso.add(new Iso8583Entity(12, EncodeType.R_ASC, LengthType.FIX_LEN));//84
		iso.add(new Iso8583Entity(12, EncodeType.R_ASC, LengthType.FIX_LEN));//85
		iso.add(new Iso8583Entity(16, EncodeType.R_ASC, LengthType.FIX_LEN));//86
		iso.add(new Iso8583Entity(16, EncodeType.R_ASC, LengthType.FIX_LEN));//87
		iso.add(new Iso8583Entity(16, EncodeType.R_ASC, LengthType.FIX_LEN));//88
		iso.add(new Iso8583Entity(16, EncodeType.R_ASC, LengthType.FIX_LEN));//89
		iso.add(new Iso8583Entity(42, EncodeType.R_ASC, LengthType.FIX_LEN));//90
		iso.add(new Iso8583Entity(1, EncodeType.R_BCD, LengthType.FIX_LEN));//91
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.FIX_LEN));//92
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.FIX_LEN));//93
		iso.add(new Iso8583Entity(7, EncodeType.R_BCD, LengthType.FIX_LEN));//94
		iso.add(new Iso8583Entity(42, EncodeType.R_BCD, LengthType.FIX_LEN));//95
		iso.add(new Iso8583Entity(8, EncodeType.R_BCD, LengthType.FIX_LEN));//96
		iso.add(new Iso8583Entity(16, EncodeType.R_BCD, LengthType.FIX_LEN));//97
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.FIX_LEN));//98
		iso.add(new Iso8583Entity(11, EncodeType.R_ASC, LengthType.LLVAR_LEN));//99
		iso.add(new Iso8583Entity(11, EncodeType.R_ASC, LengthType.LLVAR_LEN));//100
		iso.add(new Iso8583Entity(17, EncodeType.R_BCD, LengthType.LLVAR_LEN));//101
		iso.add(new Iso8583Entity(28, EncodeType.R_BCD, LengthType.LLVAR_LEN));//102
		iso.add(new Iso8583Entity(28, EncodeType.R_BCD, LengthType.LLVAR_LEN));//103
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//104
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//105
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//106
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//107
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//108
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//109
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//110
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//111
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//112
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//113
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//114
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//115
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//116
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//117
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//118
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//119
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//120
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//121
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//122
		iso.add(new Iso8583Entity(8, EncodeType.R_BCD, LengthType.LLVAR_LEN));//123
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//124
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//125
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//126
		iso.add(new Iso8583Entity(999, EncodeType.R_BCD, LengthType.LLVAR_LEN));//127
		iso.add(new Iso8583Entity(8, EncodeType.R_BCD, LengthType.FIX_LEN));//129
	}
	
}
