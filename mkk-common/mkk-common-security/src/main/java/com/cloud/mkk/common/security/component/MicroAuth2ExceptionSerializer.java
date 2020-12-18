package com.cloud.mkk.common.security.component;

import com.cloud.mkk.common.security.exception.MicroAuth2Exception;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.cloud.mkk.common.core.constant.CommonConstants;
import lombok.SneakyThrows;

/**
 * OAuth2 异常格式化
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
public class MicroAuth2ExceptionSerializer extends StdSerializer<MicroAuth2Exception> {

	public MicroAuth2ExceptionSerializer() {
		super(MicroAuth2Exception.class);
	}

	@Override
	@SneakyThrows
	public void serialize(MicroAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
		gen.writeStartObject();
		gen.writeObjectField("code", CommonConstants.FAIL);
		gen.writeStringField("msg", value.getMessage());
		gen.writeStringField("data", value.getErrorCode());
		gen.writeEndObject();
	}

}
