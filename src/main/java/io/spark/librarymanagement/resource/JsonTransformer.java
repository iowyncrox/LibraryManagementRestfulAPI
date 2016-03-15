package io.spark.librarymanagement.resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

	public static <T extends Object> T fromJson(String json, Class<T> requiredClass) {
		return new Gson().fromJson(json, requiredClass);
	}

	private final Gson gson = new GsonBuilder().serializeNulls().create();

	@Override
	public String render(Object model) {
		return this.gson.toJson(model);
	}
}