package com.github.claasahl.raml.junit.internal.common;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.claasahl.raml.junit.api.common.RamlUrlSupplier;
import com.github.claasahl.raml.junit.internal.Utils;

/**
 * The class {@link CommonRamlUrlSupplier}.
 * <p/>
 * This is an implementation of the interface {@link RamlUrlSupplier}.
 * 
 * @author Claas Ahlrichs
 *
 */
public class CommonRamlUrlSupplier implements RamlUrlSupplier {
	
	@Override
	public List<URL> getRamlUrls() {
		try {
			return Files.find(Paths.get("src/main/raml"), Integer.MAX_VALUE, this::isRamlDocument)
					.map(p -> p.toUri()).map(u -> {
						try {
							return u.toURL();
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						}
					}).filter(u -> u != null).collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	private boolean isRamlDocument(Path ramlPath, BasicFileAttributes attributes) {
		try {
			URL ramlUrl = ramlPath.toUri().toURL();
			return Utils.isRamlUrl(ramlUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
