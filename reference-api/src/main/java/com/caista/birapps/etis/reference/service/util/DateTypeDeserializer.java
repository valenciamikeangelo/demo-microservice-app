/*
 * Modified by: tolentf
 * Last updated: Sep 26, 2018 11:50:44 AM
 */
package com.caista.birapps.etis.reference.service.util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class DateTypeDeserializer implements JsonDeserializer<Date> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DateTypeDeserializer.class);

	private static final String[] DATE_FORMATS = new String[] { "yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm:ss",
			"yyyy-MM-dd", "EEE MMM dd HH:mm:ss z yyyy", "HH:mm:ss", "MM/dd/yyyy HH:mm:ss aaa",
			"yyyy-MM-dd'T'HH:mm:ss.SSSSSS", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'",
			"MMM d',' yyyy H:mm:ss a", "YYYY-MM-DDTHH:mm:ss.SSSZZ" };

	@Override
	public Date deserialize(JsonElement jsonElement, Type typeOF, JsonDeserializationContext context)
			throws JsonParseException {
		for (String format : DATE_FORMATS) {
			try {
				return new SimpleDateFormat(format).parse(jsonElement.getAsString());
			} catch (java.text.ParseException e) {
				LOGGER.error(e.getMessage());
			}
		}
		throw new JsonParseException("Unparseable date: \"" + jsonElement.getAsString() + "\". Supported formats: \n"
				+ Arrays.toString(DATE_FORMATS));
	}

}

class DateTypeDeserializer2 extends TypeAdapter<Date> {
	@Override
	public void write(JsonWriter out, Date value) throws IOException {
		if (value == null)
			out.nullValue();
		else
			out.value(value.getTime() / 1000);
	}

	@Override
	public Date read(JsonReader in) throws IOException {
		if (in != null)
			return new Date(in.nextLong() * 1000);
		else
			return null;
	}
}
