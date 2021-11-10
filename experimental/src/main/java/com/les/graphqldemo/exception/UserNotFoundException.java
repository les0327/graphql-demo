package com.les.graphqldemo.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import org.springframework.graphql.execution.ErrorType;

import java.util.List;

public class UserNotFoundException extends RuntimeException implements GraphQLError {

    public UserNotFoundException(String id) {
        super("User with id " + id + " does not exist");
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return ErrorType.NOT_FOUND;
    }
}
