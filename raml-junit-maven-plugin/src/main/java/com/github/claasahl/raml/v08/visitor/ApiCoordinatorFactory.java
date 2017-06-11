package com.github.claasahl.raml.v08.visitor;

import org.raml.v2.api.model.v08.api.Api;
import org.raml.v2.api.model.v08.bodies.Response;
import org.raml.v2.api.model.v08.methods.Method;
import org.raml.v2.api.model.v08.resources.Resource;

public class ApiCoordinatorFactory {

	private final ApiCoordinator apiCoordinator = new ApiCoordinator();
	private final MethodCoordinator methodCoordinator = new MethodCoordinator();
	private final ResourceCoordinator resourceCoordinator = new ResourceCoordinator();
	private final ResponseCoordinator responseCoordinator = new ResponseCoordinator();

	public ApiCoordinator createApiCoordinator() {
		return apiCoordinator;
	}

	public void visitApi(Api visitee, ApiVisitor visitor) {
		apiCoordinator.coordinate(visitee, visitor);
	}

	public MethodCoordinator createMethodCoordinator() {
		return methodCoordinator;
	}

	public void visitMethod(Method visitee, MethodVisitor visitor) {
		methodCoordinator.coordinate(visitee, visitor);
	}

	public ResourceCoordinator createResourceCoordinator() {
		return resourceCoordinator;
	}

	public void visitResource(Resource visitee, ResourceVisitor visitor) {
		resourceCoordinator.coordinate(visitee, visitor);
	}

	public ResponseCoordinator createResponseCoordinator() {
		return responseCoordinator;
	}

	public void visitResponse(Response visitee, ResponseVisitor visitor) {
		responseCoordinator.coordinate(visitee, visitor);
	}

}
