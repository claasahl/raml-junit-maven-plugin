package com.github.claasahl.raml.junit.internal;

import java.net.URL;
import java.util.List;

import com.github.claasahl.raml.junit.api.RamlUrlSupplier;
import com.github.claasahl.raml.junit.internal.v00.EmptyRamlUrlSupplier;

public class Suppliers {
	
	private static final String RAML_URL_SUPPLIER = "raml.junit.raml_url_supplier";
	private static RamlUrlSupplier ramlUrlSupplier;
	
	public static List<URL> getRamlUrls() {
		if(ramlUrlSupplier == null) {
			ramlUrlSupplier = Utils.createFactory(RAML_URL_SUPPLIER, EmptyRamlUrlSupplier::new);
		}
		return ramlUrlSupplier.getRamlUrls();
	}

}
