package by.bsuir.forlabs.subjects.composers;

import by.bsuir.forlabs.subjects.ClientRequest;
import by.bsuir.forlabs.subjects.Specification;
import by.bsuir.forlabs.subjects.Status;

public class ComposedRequestSpecificationStatus  implements Composed {

    private ClientRequest clientRequest;
    private Specification specification;
    private Status status;

    public ComposedRequestSpecificationStatus() {

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
