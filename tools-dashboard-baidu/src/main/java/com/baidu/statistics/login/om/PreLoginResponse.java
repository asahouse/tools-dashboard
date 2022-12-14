package com.baidu.statistics.login.om;

import com.baidu.statistics.login.core.RealDataLoginResponse;
import lombok.Data;

/**
 * DataApi的登录服务不触发验证码策略，当用户调用统计的预登录返回needAuthCode为true时，请联系管理员。 
 * @author nianqin,BenjaminKC
 */
@Data
public class PreLoginResponse extends RealDataLoginResponse {
	/**
	 * 是否需要验证码
	 */
	private Boolean needAuthCode;
	/**
	 * 验证码详细内容 needAuthCode为true时有
	 */
	private AuthCode authCode;
	
}
