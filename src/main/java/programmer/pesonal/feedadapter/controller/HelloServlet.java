package programmer.pesonal.feedadapter.controller;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "HelloServlet", urlPatterns = {"graphql/*"}, loadOnStartup = 1)
public class HelloServlet {
//        extends GraphQLHttpServlet {

//    @Override
//    protected GraphQLConfiguration getConfiguration() {
//        return GraphQLConfiguration.with(createSchema()).build();
//    }
//
//    private GraphQLSchema createSchema() {
//        String schema = "type Query{hello: String}";
//
//        SchemaParser schemaParser = new SchemaParser();
//        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);
//
//        RuntimeWiring runtimeWiring = newRuntimeWiring()
//                .type("Query", builder -> builder.dataFetcher("hello", new StaticDataFetcher("world")))
//                .build();
//
//        SchemaGenerator schemaGenerator = new SchemaGenerator();
//        return schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
//    }

}