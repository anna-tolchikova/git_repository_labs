package by.bsuir.forlabs.subjects.composers;

import by.bsuir.forlabs.subjects.ClientRequest;
import by.bsuir.forlabs.subjects.Specification;

public class ComposedRequestSpecification implements Composed{

    private ClientRequest clientRequest;
    private Specification specification;

    public ComposedRequestSpecification() {

    }

    public ClientRequest getClientRequest() {
        return clientRequest;
    }

    public void setClientRequest(ClientRequest clientRequest) {
        this.clientRequest = clientRequest;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }
}
