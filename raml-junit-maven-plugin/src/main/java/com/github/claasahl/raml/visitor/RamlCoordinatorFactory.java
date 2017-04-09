package com.github.claasahl.raml.visitor;

import java.nio.file.Path;

import org.raml.model.Action;
import org.raml.model.Raml;
import org.raml.model.Resource;
import org.raml.model.Response;

public class RamlCoordinatorFactory {

	private final ActionCoordinator actionCoordinator = new ActionCoordinator();
	private final RamlCoordinator ramlCoordinator = new RamlCoordinator();
	private final ResourceCoordinator resourceCoordinator = new ResourceCoordinator();
	private final ResponseCoordinator responseCoordinator = new ResponseCoordinator();

	public ActionCoordinator createActionCoordinator() {
		return actionCoordinator;
	}

	public void visitAction(Action action, ActionVisitor visitor) {
		actionCoordinator.visitAction(action, visitor);
	}

	public RamlCoordinator createRamlCoordinator() {
		return ramlCoordinator;
	}

	public void visitRaml(Raml raml, Path ramlPath, RamlVisitor visitor) {
		ramlCoordinator.visitRaml(raml, ramlPath, visitor);
	}

	public ResourceCoordinator createResourceCoordinator() {
		return resourceCoordinator;
	}

	public void visitResource(Resource resource, ResourceVisitor visitor) {
		resourceCoordinator.visitResource(resource, visitor);
	}

	public ResponseCoordinator createResponseCoordinator() {
		return responseCoordinator;
	}

	public void visitResponse(Response response, ResponseVisitor visitor) {
		responseCoordinator.visitResponse(response, visitor);
	}

}
