package com.github.claasahl.raml.visitor;

public class RamlCoordinatorFactory {

	public ActionCoordinator createActionCoordinator() {
		return new ActionCoordinator();
	}

	public RamlCoordinator createRamlCoordinator() {
		return new RamlCoordinator();
	}

	public ResourceCoordinator createResourceCoordinator() {
		return new ResourceCoordinator();
	}

	public ResponseCoordinator createResponseCoordinator() {
		return new ResponseCoordinator();
	}

}
