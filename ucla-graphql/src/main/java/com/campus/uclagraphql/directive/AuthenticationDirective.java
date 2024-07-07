package com.campus.uclagraphql.directive;

import com.campus.uclagraphql.exception.UnauthorizedException;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationDirective implements SchemaDirectiveWiring {

    @Override
    public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> environment) {
        GraphQLFieldDefinition fieldDefinition = environment.getFieldDefinition();
        GraphQLObjectType parentType = (GraphQLObjectType) environment.getFieldsContainer();

        // Get the original DataFetcher.
        DataFetcher<?> originalDataFetcher = environment.getCodeRegistry().getDataFetcher(parentType, fieldDefinition);

        // Create a new DataFetcher that performs an authentication check.
        // X-User ID Head check
        DataFetcher<?> authDataFetcher = (DataFetchingEnvironment dataFetchingEnvironment) -> {
            String userId = dataFetchingEnvironment.getGraphQlContext().get("X-USER-ID");
            if (userId == null || userId.trim().isEmpty()) {
                throw new UnauthorizedException("Unauthorized: Missing X-USER-ID header");
            }
            return originalDataFetcher.get(dataFetchingEnvironment);
        };

        // Register the modified DataFetcher.
        environment.getCodeRegistry().dataFetcher(parentType, fieldDefinition, authDataFetcher);

        return fieldDefinition;
    }

}
